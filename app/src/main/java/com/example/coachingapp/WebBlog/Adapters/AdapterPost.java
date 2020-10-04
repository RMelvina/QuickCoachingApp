package com.example.coachingapp.WebBlog.Adapters;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.text.format.DateFormat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.coachingapp.Models.ModelPost;
import com.example.coachingapp.R;
import com.example.coachingapp.WebBlog.Blog;
import com.example.coachingapp.WebBlog.PostDetailActivity;
import com.example.coachingapp.WebBlog.UpdateBlog;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.recyclerview.widget.RecyclerView;

//import com.example.coachingapp.PostDetailActivity;


public class AdapterPost extends RecyclerView.Adapter<AdapterPost.MyHolder> {

    Context context;
    List<ModelPost> postList;
    String myUid;


    private DatabaseReference commentCountRef;
    private DatabaseReference blogRef;

    ActionBar actionBar;




    public AdapterPost(Context context, List<ModelPost> postList) {
        this.context = context;
        this.postList = postList;
        myUid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        commentCountRef = FirebaseDatabase.getInstance().getReference().child("CoummentCount");
       blogRef = FirebaseDatabase.getInstance().getReference().child("Blog");
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {



        View view = LayoutInflater.from(context).inflate(R.layout.row_posts, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyHolder holder, final int position) {


        final String uid = postList.get(position).getUid();
        String uEmail = postList.get(position).getuEmail();
        String uName = postList.get(position).getuName();
        String uDp = postList.get(position).getuDp();
        final String pId = postList.get(position).getpId();
        String pTitle = postList.get(position).getpTitle();
        String pDescripition = postList.get(position).getpDescr();
        final String pImage = postList.get(position).getpImage();
        String pTimeStamp = postList.get(position).getpTime();
        String pComments = postList.get(position).getpComments();
        String pCommentsTv = postList.get(position).getpCommentCount();


        Calendar calendar = Calendar.getInstance(Locale.getDefault());
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm aa");
        String pTime = dateFormat.format(calendar.getTimeInMillis());
//            Calendar calendar = Calendar.getInstance(Locale.getDefault());
//            calendar.setTimeInMillis(Long.parseLong(pTimeStamp));
//            String pTime = DateFormat.format("dd/MM/yyyy hh:mm aa", calendar).toString();
//
              holder.pTimeTv.setText(pTime);


        //calendar.setTimeInMillis(Long.parseLong(pTimeStamp));




        holder.uNameTv.setText(uName);

        holder.pTitleTv.setText(pTitle);
        holder.pDescriptionTv.setText(pDescripition);
//        holder.pComments.setText(pComments + " Comment");
       // holder.pCommentsTv.setText(pCommentsTv + "Comments");



        try{
            Picasso.get().load(uDp).placeholder(R.drawable.ic_menu_camera).into(holder.pProfileImage);

        }catch (Exception e){


//(pImage.equals("noImage")
        }


        if (pImage != null && pImage.equals("noImage")){
            holder.pImageIv.setVisibility(View.GONE);


        }else {
            holder.pImageIv.setVisibility(View.VISIBLE);

            try{
                Picasso.get().load(pImage).into(holder.pImageIv);

            }catch (Exception e){



            }
        }


        holder.moreBtn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                showMoreOptions(holder.moreBtn, uid, myUid, pId, pImage);
;            }
        });


        holder.commentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PostDetailActivity.class);
                intent.putExtra("blogId",pId );
                context.startActivity(intent);
                 Toast.makeText(context, "Comment", Toast.LENGTH_SHORT).show();

            }
        });




    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void showMoreOptions(ImageButton moreBtn, String uid, String myUid, final String pId, final String pImage){
        final PopupMenu popupMenu = new PopupMenu(context, moreBtn, Gravity.END);

        if (uid.equals(myUid)){
            popupMenu.getMenu().add(Menu.NONE,0,0,"Delete");
            popupMenu.getMenu().add(Menu.NONE,1,0,"Edit");


        }
        popupMenu.getMenu().add(Menu.NONE,2,0,"View Detail");



        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int id = item.getItemId();
                if(id==0){
                     beginDelete(pId, pImage);

                }else if (id==1){
//                  Edit Blog

                    Intent intent = new Intent(context, Blog.class);
                    intent.putExtra("key" ,"editCurrentBlog");
                    intent.putExtra("editBlogId", pId);
                    context.startActivity(intent);

                }else if (id ==2){
                    Intent intent = new Intent(context, PostDetailActivity.class);
                    intent.putExtra("blogId",pId );
                    context.startActivity(intent);





                }
                return false;
            }
        });

        popupMenu.show();


    }

    private void beginDelete(String pId, String pImage) {
       //Blog without Image

        if (pImage.equals("noImage")){
            //Blog is without image
            deleteWithoutImage(pId);
        }else {
            //Blog with image
            deleteWithImage(pId, pImage);

        }

    }

    private void deleteWithImage(final String pId, String pImage) {
        final ProgressDialog pd = new ProgressDialog(context);
        pd.setMessage("Deleting....");


        StorageReference picRef = FirebaseStorage.getInstance().getReferenceFromUrl(pImage);
        picRef.delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                        Query  query = FirebaseDatabase.getInstance().getReference("Blog").orderByChild("pId").equalTo(pId);
                        query.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                for (DataSnapshot ds: dataSnapshot.getChildren()){
                                    ds.getRef().removeValue(); // remove values from firebase pid matches
                                }

                                // delete
                                Toast.makeText(context, "Deleted successfully", Toast.LENGTH_SHORT).show();
                                pd.dismiss();

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });



                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });

    }

    private void deleteWithoutImage(String pId) {
        final ProgressDialog pd = new ProgressDialog(context);
        pd.setMessage("Deleting....");

        Query  query = FirebaseDatabase.getInstance().getReference("Blog").orderByChild("pId").equalTo(pId);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds: dataSnapshot.getChildren()){
                    ds.getRef().removeValue(); // remove values from firebase pid matches
                }

                // delete
                Toast.makeText(context, "Deleted successfully", Toast.LENGTH_SHORT).show();
                pd.dismiss();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    //view holder class
    class MyHolder extends RecyclerView.ViewHolder{

        ImageView pProfileImage, pImageIv;
        TextView uNameTv, pTimeTv, pTitleTv, pDescriptionTv, pLikeTv, pComments, pCommentsTv ;
        ImageButton moreBtn;
        Button likeBtn, commentBtn, shareBtn;


        public  MyHolder(@NonNull View itemView){
            super(itemView);

            //init view
            pProfileImage = itemView.findViewById(R.id.pProfileImage);
            pImageIv = itemView.findViewById(R.id.pImageIv);
            uNameTv = itemView.findViewById(R.id.uNameTv);
            pTimeTv = itemView.findViewById(R.id.uTimeTv);
            pTitleTv = itemView.findViewById(R.id.pTitleTv);
            pDescriptionTv = itemView.findViewById(R.id. pDescriptionTv);
            pLikeTv = itemView.findViewById(R.id. pLikesIv);
           // pComments = itemView.findViewById(R.id.pCommentsTv);
            //pCommentsTv = itemView.findViewById(R.id.pCommentsCountTv);
            moreBtn = itemView.findViewById(R.id. moreBtn);
           // likeBtn = itemView.findViewById(R.id. likeBtn);
            commentBtn= itemView.findViewById(R.id. commentBtn);
            //shareBtn = itemView.findViewById(R.id. shareBtn);

        }
    }
}
