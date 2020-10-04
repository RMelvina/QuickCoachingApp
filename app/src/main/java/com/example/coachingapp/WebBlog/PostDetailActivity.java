package com.example.coachingapp.WebBlog;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.coachingapp.Login;
import com.example.coachingapp.Models.ModelComments;
import com.example.coachingapp.Models.ModelPost;
import com.example.coachingapp.R;
import com.example.coachingapp.WebBlog.Adapters.AdapterComment;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class PostDetailActivity extends AppCompatActivity {

    String myUid, myEmail, myName, myDp,
            blogId, pLikes, hisDp, hisName;

    String current_user_id;


    ImageView uPictureIv, pImageIv;
    TextView uNameTv, pTimeTv, pTitleTv, pDescriptionTv, pLikeTv, pCommentsTv;
    Button likeBtn,  shareBtn;
    ImageButton moreBtn;
    LinearLayout profileLayout;
    ProgressDialog progressDialog;

    FirebaseAuth firebaseAuth;
    FirebaseDatabase database;
    DatabaseReference myRef, blogsRef;
    FirebaseUser user;

    EditText commentEt;
    ImageButton sentBtn,commentBtn;
    ImageView cAvatarIv;

    RecyclerView recyclerView;
    List<ModelComments> commentsList;
    List<ModelPost> postList;
    AdapterComment adapterComment;

    int commentCount = 0;
    ActionBar actionBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail);

        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        myRef = FirebaseDatabase.getInstance().getReference();
        database = FirebaseDatabase.getInstance();
        //current_user_id = firebaseAuth.getCurrentUser().getUid();
        //blogsRef = FirebaseDatabase.getInstance().getReference("Blog");

        Intent intent = getIntent();
        blogId = intent.getStringExtra("blogId");

        actionBar = getSupportActionBar();
        actionBar.setTitle("View Blog");
        //actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.actionBarColor)));
//
//        Query query = myRef.child("users").child(user.getUid());
//
//        query.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                for(DataSnapshot ds: dataSnapshot.getChildren()){
//                    myName =  ""+ ds.child("username").getValue();
//                    String n = ""+ ds.child("username").getValue();
//
//
//                    modelComments.setuName(n);
//
//                }
//            }
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });


       // commentsList = new ArrayList<>();
      //  uPictureIv = findViewById(R.id.uPictureIv);
        uNameTv = findViewById(R.id.uNameTv);
        pTimeTv = findViewById(R.id.uTimeTv);
        pTitleTv = findViewById(R.id.pTitleTv);
        pDescriptionTv = findViewById(R.id. pDescriptionTv);
        pLikeTv = findViewById(R.id. pLikesIv);
        moreBtn = findViewById(R.id. moreBtn);
       // likeBtn = findViewById(R.id. likeBtn);
        pImageIv = findViewById(R.id.pImageIv);
        //pCommentsTv = findViewById(R.id.pCommentsTv);

        //shareBtn = findViewById(R.id. shareBtn);
        sentBtn = findViewById(R.id.sendBtn);
        commentEt = findViewById(R.id.commentEt);
        cAvatarIv = findViewById(R.id.cAvatarIv);
        recyclerView = findViewById(R.id.recyclerview);




        loadPostinfo();
        loadUserInfo();
        checkUserStatus();
        loadComments();

        Intent in = getIntent();
        final String displayBlogDetails = ""+in.getStringExtra("blogId");

        if (displayBlogDetails.equals("pId")){


            loadPostinfo();

        }



        sentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postComment();
            }
        });


        moreBtn.setVisibility(View.GONE);

    }


    private void loadComments() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());

        recyclerView.setLayoutManager(linearLayoutManager);

        commentsList = new ArrayList<>();


        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Blog").child(blogId).child("Comment");
                //.child(user.getUid()).child("Blog").child(blogId).child("Comment");
        //FirebaseDatabase.getInstance().getReference("users").child(user.getUid()).child("Blog");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                commentsList.clear();
                for (DataSnapshot ds: dataSnapshot.getChildren()){
                    ModelComments modelComments = ds.getValue(ModelComments.class);

                    commentsList.add(modelComments);



                    adapterComment = new AdapterComment(getApplicationContext(), commentsList, myUid, blogId);
                    recyclerView.setAdapter(adapterComment);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }



    private void postComment() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Adding Comments...");

        //get data from comment
        String comment = commentEt.getText().toString().trim();
        if (TextUtils.isEmpty(comment)){
            Toast.makeText(PostDetailActivity.this, "Please write some thing", Toast.LENGTH_SHORT).show();
            return;
        }
        String timeStamp = String.valueOf(System.currentTimeMillis());


      //  DatabaseReference ref = FirebaseDatabase.getInstance().getReference("users").child(user.getUid()).child("Blog").child("Comment");
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Blog").child(blogId).child("Comment");

       // DatabaseReference ref = FirebaseDatabase.getInstance().getReference("users").child(user.getUid()).child(blogId).child("Comment");

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("cid", timeStamp);
        hashMap.put("comment", comment);
        hashMap.put("timestamp", timeStamp);
        hashMap.put("uid", myUid);
        hashMap.put("uEmail", myEmail);
        hashMap.put("uDp", myDp);
       hashMap.put("uName",myName );


        ref.child(timeStamp).setValue(hashMap)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        progressDialog.dismiss();
                        Toast.makeText(PostDetailActivity.this, "Comment Added...", Toast.LENGTH_SHORT).show();
                        commentEt.setText("");
                       // addCommentCount();
                        //updateCommentCount();

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressDialog.dismiss();
                        Toast.makeText(PostDetailActivity.this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
                        commentEt.setText("");


                    }
                });



    }

//    private void addCommentCount() {
//
//        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("users").child(user.getUid()).child("Blog").child(blogId);
//
//        String countComment = String.valueOf(commentCount);
//        HashMap<String, Object> commentC = new HashMap<>();
//
//        commentC.put(countComment, 0);
//        ref.child("pComments").setValue(commentC);
//
//
//    }

//
    private void loadUserInfo() {

//

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("users").child(user.getUid());
        ref.orderByChild("uid").equalTo(myUid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds: dataSnapshot.getChildren()){
                    myName = ""+ds.child("username").getValue();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

//

    }


    private void loadPostinfo() {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Blog");
        Query query =  ref.orderByChild("pId").equalTo(blogId);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds: dataSnapshot.getChildren())
                {
                    String pTitle = ""+ds.child("pTitle").getValue();
                    String pDescr = ""+ds.child("pDescr").getValue();
                    //String pLikes = ""+ds.child("pLikes").getValue();
                    String pTimeStamp = ""+ds.child("pTime").getValue();
                    String pImage = ""+ds.child("pImage").getValue();
                   // hisDp =  ""+ds.child("pImage").getValue();
                    String uid = ""+ds.child("uid").getValue();
                    String uEmail = ""+ds.child("pEmail").getValue();
                   String  hisName = ""+ds.child("uName").getValue();
                  //  String commentCount = ""+ds.child("pComment").getValue();

                    Calendar calendar = Calendar.getInstance(Locale.getDefault());
                    calendar.setTimeInMillis(Long.parseLong(pTimeStamp));
                    String pTime = DateFormat.format("dd/MM/yyyy hh:mm aa", calendar).toString();



                    uNameTv.setText(hisName);
                    pTitleTv.setText(pTitle);
                    pDescriptionTv.setText(pDescr);

                   // pLikestv
                    pTimeTv.setText(pTime);
                  // pCommentsTv.setText(commentCount + "Comment");


                    if (pImage.isEmpty()){
                        pImageIv.setVisibility(View.GONE);

                    }else {

                        pImageIv.setVisibility(View.VISIBLE);
                        try{

                            Picasso.get().load(pImage).into(pImageIv);

                        }catch (Exception e){



                        }
                    }

//                    try {
//                        Picasso.get().load(hisDp).placeholder(R.drawable.ic_menu_camera).into(uPictureIv);
//
//                    }catch (Exception e){
//                        Picasso.get().load(R.drawable.ic_menu_camera).into(uPictureIv);
//
//                    }




                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }




    private void checkUserStatus(){
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if(user!=null){
            myEmail = user.getEmail();
            myUid = user.getUid();


        }else {
            startActivity(new Intent(this, Login.class));
            finish();

        }
    }

}
