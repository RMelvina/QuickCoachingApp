package com.example.coachingapp.WebBlog;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.coachingapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class UpdateBlog extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    FirebaseDatabase database;
    DatabaseReference myRef;
    DatabaseReference mDatabaseUsers;
    FirebaseUser user;
    StorageReference storage;
    DatabaseReference  blogsRef, userRef;
    Uri uri = null;

    EditText edittitleEt, editdescriptionEt, uUserNameEt;
    ImageView editimageIv;
    Button updateBtn;
    String current_user_id;
    Uri image_rui = null;
    //ProgressBar
    ProgressDialog progressDialog;
    ActionBar actionBar;

    private static final int CAMERA_REQUEST_CODE = 100;
    private static final int STORAGE_REQUEST_CODE = 200;


    private static final int IMAGE_PICK_CAMERA_CODE = 300;
    private static final int IMAGE_PICK_GALLERY_CODE = 400;



    //permission array
    String[]  cameraPermissions;
    String[]  storagePermissions;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_blog);

        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        myRef = FirebaseDatabase.getInstance().getReference();
        // myRef = database.getInstance().getReference("Blog");
        database = FirebaseDatabase.getInstance();
        current_user_id = firebaseAuth.getCurrentUser().getUid();
        userRef = FirebaseDatabase.getInstance().getReference().child("users");
        blogsRef= FirebaseDatabase.getInstance().getReference().child("Blog");




        edittitleEt = findViewById(R.id.editTitleEt);
        editdescriptionEt = findViewById(R.id.editDescriptionEt);
        editimageIv = findViewById(R.id.editImageIv);
        updateBtn = findViewById(R.id.updateBtn);
        //uUserNameEt = findViewById(R.id.pUsernameEt);

        cameraPermissions = new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        storagePermissions = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};

        // get data through from previos intent
        Intent intent = getIntent();
        final String isUpadtedKey = ""+intent.getStringExtra("key");
        final String editBlogId = ""+intent.getStringExtra("editBlogId");

        if (isUpadtedKey.equals("editCurrentBlog")) {
            loadPostData(editBlogId);
        }
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
                   String editTitle = ""+ds.child("pTitle").getValue();
                    String editDesc = ""+ds.child("pDescr").getValue();
                    String editImage = ""+ds.child("pImage").getValue();
                    String editUsername = ""+ds.child("uName").getValue();

                    edittitleEt.setText(editTitle);
                    editdescriptionEt.setText(editDesc);
                    // uUserNameEt.setVisibility(View.GONE);
                    uUserNameEt.setText(editUsername);
                    uUserNameEt.setEnabled(false);

                    if (!editImage.equals("noImage")){
                        try {

                            Picasso.get().load(editImage).into(editimageIv);

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

}
