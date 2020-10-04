package com.example.coachingapp.WebBlog;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.coachingapp.Login;
import com.example.coachingapp.R;
import com.example.coachingapp.WebBlog.Fragments.HomeFragment;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class Blog extends AppCompatActivity {

    EditText titleEt, descriptionEt, uUserNameEt;
    ImageView imageIv;
    Button uploadBtn;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase database;
    DatabaseReference myRef;
    DatabaseReference mDatabaseUsers;
    FirebaseUser user;
    StorageReference storage;
    DatabaseReference  blogsRef, userRef;
    Uri uri = null;

    String editTitle, editDesc,editImage,editUsername;

    //permission constants
    private static final int CAMERA_REQUEST_CODE = 100;
    private static final int STORAGE_REQUEST_CODE = 200;


    private static final int IMAGE_PICK_CAMERA_CODE = 300;
    private static final int IMAGE_PICK_GALLERY_CODE = 400;


    String name, uid, dp, email, username;
    String current_user_id;
    Uri image_rui = null;
    //ProgressBar
    ProgressDialog progressDialog;
    ActionBar actionBar;


//    int countComment = 0;
//
//    String commentCount = Integer.parseInt(countComment);


    //permission array
    String[]  cameraPermissions;
    String[]  storagePermissions;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog);

//        firebaseAuth = FirebaseAuth.getInstance();
//        user = firebaseAuth.getCurrentUser();
//          myRef = FirebaseDatabase.getInstance().getReference();
//
//        database = FirebaseDatabase.getInstance();
//        current_user_id = firebaseAuth.getCurrentUser().getUid();


        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        myRef = FirebaseDatabase.getInstance().getReference();
        database = FirebaseDatabase.getInstance();





        actionBar = getSupportActionBar();
        checkUserStatus();


        Query query = myRef.child("users").child(user.getUid());

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    name =  ""+ ds.child("username").getValue();


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        titleEt = findViewById(R.id.pTitleEt);
        descriptionEt = findViewById(R.id.pDescriptionEt);
        imageIv = findViewById(R.id.pImageIv);
        uploadBtn = findViewById(R.id.pUploadBtn);
//        uUserNameEt = findViewById(R.id.pUsernameEt);





        // inti permission
        cameraPermissions = new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        storagePermissions = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};


        // get data through from previos intent
        Intent intent = getIntent();
        final String isUpadtedKey = ""+intent.getStringExtra("key");
        final String editBlogId = ""+intent.getStringExtra("editBlogId");

        // Validate update

        if (isUpadtedKey.equals("editCurrentBlog")){



            loadPostData(editBlogId);
            actionBar.setTitle("Update Blog");
            uploadBtn.setText("UPDATE");


        }else {
            actionBar.setTitle("Add Blog");
            uploadBtn.setText("PUBLISH");

        }
        progressDialog = new ProgressDialog(this);
        //get some info of current userto incule in post


        //get image from camera gallery
        imageIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //show image pick
              showImagePickerDialog();
            }
        });

        uploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = titleEt.getText().toString().trim();
                String desc = descriptionEt.getText().toString().trim();


                if (TextUtils.isEmpty(title)){
                    Toast.makeText(Blog.this, "Please enter your title", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(desc)){
                    Toast.makeText(Blog.this, "Please enter  description ", Toast.LENGTH_SHORT).show();
                    return;
                }



                if (isUpadtedKey.equals("editCurrentBlog")){
                    actionBar.setTitle("Update");
                    beginUpadte(title, desc,editBlogId );
                }else {

                    actionBar.setTitle("Add New Post");
                    uploadData(title, desc);


                }


//
//                if (image_rui == null){
//                    uploadData(title, desc, "noImage");
//
//                }else {
//
//                    uploadData(title, desc, String.valueOf(image_rui));
//
//                }

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        checkUserStatus();

    }

    @Override
    protected void onResume() {
        super.onResume();
        checkUserStatus();
    }





    private void beginUpadte(String title, String desc, String editBlogsId) {
        progressDialog.setMessage("Updating Blog");
        progressDialog.show();

        if (!editImage.equals("noImage")){
            updateWithImage(title, desc, editBlogsId);


        }else if(imageIv.getDrawable() != null) {

            updateWithNowImage(title, desc, editBlogsId);


        }else {
            upadteWithoutImage(title, desc, editBlogsId);

        }


    }

    private void upadteWithoutImage(String title, String desc, String editBlogsId) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("uid", uid);
       // hashMap.put("uName",username);
        hashMap.put("uEmail", email);
        hashMap.put("uDp", dp);
        hashMap.put("pTitle",title);
        hashMap.put("pDescr", desc);
        hashMap.put("pImage", "noImage");

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Blog");
        ref.child(editBlogsId)
                .updateChildren(hashMap)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        progressDialog.dismiss();
                        Toast.makeText(Blog.this, "Update complete", Toast.LENGTH_SHORT).show();


                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressDialog.dismiss();
                        Toast.makeText(Blog.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });


    }

    private void updateWithImage(final String title, final String desc, final String editBlogsId) {
        StorageReference mPictureRef = FirebaseStorage.getInstance().getReferenceFromUrl(editImage);
        mPictureRef.delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        //image deleted, uploed new image
                        final String timeStamp = String.valueOf(System.currentTimeMillis());
                        String filePathAndName = "Blog/" + "blog_" + timeStamp;

                        Bitmap bitmap = ((BitmapDrawable)imageIv.getDrawable()).getBitmap();
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
                        byte[] data = baos.toByteArray();

                        StorageReference ref = FirebaseStorage.getInstance().getReference().child(filePathAndName);
                        ref.putBytes(data)
                                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                    @Override
                                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                                        Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();

                                        while (!uriTask.isSuccessful());
                                            String downloadeUri = uriTask.getResult().toString();
                                            if (uriTask.isSuccessful()){

                                                //uri is received and uploaded to firebase database
                                                HashMap<String, Object> hashMap = new HashMap<>();
                                                hashMap.put("uid", uid);
                                               // hashMap.put("uName",username);
                                                hashMap.put("uEmail", email);
                                                hashMap.put("uDp", dp);
                                                hashMap.put("pTitle",title);
                                                hashMap.put("pDescr", desc);
                                                hashMap.put("pImage", downloadeUri);

                                                DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Blog");
                                                ref.child(editBlogsId)
                                                        .updateChildren(hashMap)
                                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                            @Override
                                                            public void onSuccess(Void aVoid) {
                                                                progressDialog.dismiss();
                                                                Toast.makeText(Blog.this, "Update complete", Toast.LENGTH_SHORT).show();


                                                            }
                                                        })
                                                        .addOnFailureListener(new OnFailureListener() {
                                                            @Override
                                                            public void onFailure(@NonNull Exception e) {
                                                                progressDialog.dismiss();
                                                                Toast.makeText(Blog.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();

                                                            }
                                                        });


                                            }



                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        //Image not uploaded
                                        progressDialog.dismiss();
                                        Toast.makeText(Blog.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();

                                    }
                                });

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressDialog.dismiss();
                        Toast.makeText(Blog.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });
    }

    private void updateWithNowImage(final String title, final String desc, final String editBlogsId) {

        final String timeStamp = String.valueOf(System.currentTimeMillis());
        String filePathAndName = "Blog/" + "blog_" + timeStamp;

        Bitmap bitmap = ((BitmapDrawable)imageIv.getDrawable()).getBitmap();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] data = baos.toByteArray();

        StorageReference ref = FirebaseStorage.getInstance().getReference().child(filePathAndName);
        ref.putBytes(data)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                        while (!uriTask.isSuccessful());
                            String downloadeUri = uriTask.getResult().toString();
                            if (uriTask.isSuccessful()){

                                HashMap<String, Object> hashMap = new HashMap<>();
                                hashMap.put("uid", uid);
                               // hashMap.put("uName",username);
                                hashMap.put("uEmail", email);
                                hashMap.put("uDp", dp);
                                hashMap.put("pTitle",title);
                                hashMap.put("pDescr", desc);
                                hashMap.put("pImage", downloadeUri);

                                DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Blog");
                                ref.child(editBlogsId)
                                        .updateChildren(hashMap)
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                progressDialog.dismiss();
                                                Toast.makeText(Blog.this, "Update complete", Toast.LENGTH_SHORT).show();


                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                progressDialog.dismiss();
                                                Toast.makeText(Blog.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();

                                            }
                                        });


                            }



                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressDialog.dismiss();
                        Toast.makeText(Blog.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });





    }


    private void loadPostData(String editBlogId) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Blog");
        //get details
        Query fquery = reference.orderByChild("pId").equalTo(editBlogId);

        fquery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds: dataSnapshot.getChildren())
                {
                    editTitle = ""+ds.child("pTitle").getValue();
                    editDesc = ""+ds.child("pDescr").getValue();
                    editImage = ""+ds.child("pImage").getValue();
                    editUsername = ""+ds.child("uName").getValue();

                    titleEt.setText(editTitle);
                    descriptionEt.setText(editDesc);
                   // uUserNameEt.setVisibility(View.GONE);
//                    uUserNameEt.setText(editUsername);
//                    uUserNameEt.setEnabled(false);

                    if (!editImage.equals("noImage")){
                        try {

                            Picasso.get().load(editImage).into(imageIv);


                        }catch (Exception e){
                        }
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


    private void uploadData(final String title, final String desc) {
        progressDialog.setMessage("Publishing app....");
        progressDialog.show();



        final String timeStamp = String.valueOf(System.currentTimeMillis());
        String filePathAndName = "Blog/" + "blog_" + timeStamp;
        if (user != null){
            if (imageIv.getDrawable()!= null){
                Bitmap bitmap = ((BitmapDrawable)imageIv.getDrawable()).getBitmap();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
                byte[] data = baos.toByteArray();


                StorageReference ref = FirebaseStorage.getInstance().getReference().child(filePathAndName);
                ref.putBytes(data)
                        .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                                while (!uriTask.isSuccessful());

                                String downloadUri= uriTask.getResult().toString();


                                if(uriTask.isSuccessful()){
                                    String id = user.getUid();

                                  //  myRef =  myRef.child("users").child(id).child("users info");
//
                                    HashMap<Object, String> hashMap = new HashMap<>();
                                    //Put post info
                                    hashMap.put("uid", uid);
                                    hashMap.put("uName",name);
                                    hashMap.put("uEmail", email);
                                    hashMap.put("uDp", dp);
                                    hashMap.put("pId", timeStamp);
                                    hashMap.put("pTitle",title);
                                    hashMap.put("pDescr", desc);
                                    hashMap.put("pImage", downloadUri);
                                    hashMap.put("pTime",timeStamp);
                                    //hashMap.put("pComment",commentCount );




                                    DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Blog");
                                   // issuesRef.child(current_user_id).child("Blog");
                                    //blogsRef= FirebaseDatabase.getInstance().getReference().child("Blog");
                                    // blogsRef.child(current_user_id).child(timeStamp).setValue(hashMap)

                                   ref.child(timeStamp).setValue(hashMap)

                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void aVoid) {
                                                    progressDialog.dismiss();
                                                    Toast.makeText(Blog.this, "Post published ", Toast.LENGTH_SHORT).show();
                                                    titleEt.setText("");
                                                    descriptionEt.setText("");
                                                    imageIv.setImageURI(null);
                                                    image_rui = null;


                                                }
                                            })
                                            .addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {

                                                    progressDialog.dismiss();
                                                    Toast.makeText(Blog.this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
                                                }
                                            });
                                }



                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                progressDialog.dismiss();
                                Toast.makeText(Blog.this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();

                            }
                        });

            }else {

                HashMap<Object, String> hashMap = new HashMap<>();
                //Put post info
                hashMap.put("uid", uid);
                hashMap.put("uName",name);
                hashMap.put("uEmail", email);
                hashMap.put("uDp", dp);
                hashMap.put("pId", timeStamp);
                hashMap.put("pTitle",title);
                hashMap.put("pDescr", desc);
                hashMap.put("pImage", "noImage");
                hashMap.put("pTime",timeStamp);

                String id = user.getUid();
                // myRef.child("users").child(id).child("users info");

                DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Blog");
              //  DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child(current_user_id).child("Blog");

                ref.child(timeStamp).setValue(hashMap)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                progressDialog.dismiss();
                                Toast.makeText(Blog.this, "Post published ", Toast.LENGTH_SHORT).show();

                                titleEt.setText("");
                                descriptionEt.setText("");
                                imageIv.setImageURI(null);
                                image_rui = null;

                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {

                                progressDialog.dismiss();
                                Toast.makeText(Blog.this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });

            }

        }


    }

    private void showImagePickerDialog() {
        String[] options = {"Camera", "Gallery" };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choose Image from");
        // Set options
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == 0){
                    //Camera clicked
                    if (!checkCameraPermission()){
                        requestCameraPermission();
                    }else {
                        pickFromCamera();

                    }


                }
                if (which == 1){
                    if (!checkStoragePermission()){
                        requestStoragePermission();
                    }else {
                        pickFromGallery();
                    }

                }

            }
        });

        builder.create().show();
    }

    private void pickFromGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, IMAGE_PICK_GALLERY_CODE);
    }

    private void pickFromCamera() {
        ContentValues cv = new ContentValues();
        cv.put(MediaStore.Images.Media.TITLE, "Temp Pick");
        cv.put(MediaStore.Images.Media.DESCRIPTION, "Temp Descr");

        image_rui = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, cv);


        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, image_rui);
        startActivityForResult(intent, IMAGE_PICK_CAMERA_CODE);
    }


    private boolean checkStoragePermission(){
        boolean result = ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) == (PackageManager.PERMISSION_GRANTED);
        return result;
    }



    private void requestStoragePermission(){

        ActivityCompat.requestPermissions(this, storagePermissions, STORAGE_REQUEST_CODE );

    }


    private boolean checkCameraPermission(){
        boolean result = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == (PackageManager.PERMISSION_GRANTED);


        boolean resultl = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == (PackageManager.PERMISSION_GRANTED);
        return result && resultl;
    }



    private void requestCameraPermission(){

        ActivityCompat.requestPermissions(this, cameraPermissions, CAMERA_REQUEST_CODE );

    }

//handle permission
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode){
            case CAMERA_REQUEST_CODE:{
                if (grantResults.length > 0){
                    boolean cameraAccpted = grantResults[0]== PackageManager.PERMISSION_GRANTED;
                    boolean storageAccpted = grantResults[1]== PackageManager.PERMISSION_GRANTED;
                    if (cameraAccpted && storageAccpted){
                        pickFromCamera();

                    }else{

                        Toast.makeText(this, "Camera and Storage both permission are important", Toast.LENGTH_SHORT).show();
                    }
                }else {

                }

            }
            break;
            case STORAGE_REQUEST_CODE:{

                if (grantResults.length > 0){

                    boolean storageAccpted = grantResults[0]== PackageManager.PERMISSION_GRANTED;
                    if (storageAccpted){
                        pickFromGallery();

                    }else{

                        Toast.makeText(this, " Storage  permission is important", Toast.LENGTH_SHORT).show();
                    }
                }else {

                }

            }
            break;

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (resultCode == RESULT_OK){
            if (requestCode == IMAGE_PICK_GALLERY_CODE){
                image_rui = data.getData();

                //Set to image view
                imageIv.setImageURI(image_rui);

            }
           else if (requestCode == IMAGE_PICK_CAMERA_CODE){
                imageIv.setImageURI(image_rui);

            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    private void checkUserStatus(){
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if (user!=null){
            email = user.getEmail();


            uid = user.getUid();


        }else {
            startActivity(new Intent(this, Login.class));
            finish();
        }
    }





//    @Override
//    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
//        getMenuInflater().inflate(R.menu.blog_menu, menu);
//        return super.onCreateOptionsMenu(menu);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        int id = item.getItemId();
//
//        return super.onOptionsItemSelected(item);
//    }
}
