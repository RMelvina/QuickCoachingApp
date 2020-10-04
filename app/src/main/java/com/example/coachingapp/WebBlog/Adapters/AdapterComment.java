package com.example.coachingapp.WebBlog.Adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.coachingapp.Models.ModelComments;
import com.example.coachingapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;


import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdapterComment extends RecyclerView.Adapter<AdapterComment.MyHolder> {
    Context context;
    List<ModelComments> commentsList;
    String myUid, blogId;

    public AdapterComment(Context context, List<ModelComments> commentsList, String myUid, String blogId) {
        this.context = context;
        this.commentsList = commentsList;
        this.myUid = myUid;
        this.blogId = blogId;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_comments, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        final String uid = commentsList.get(position).getUid();
        String name = commentsList.get(position).getuName();
        String email = commentsList.get(position).getuEmail();
        String image = commentsList.get(position).getuDp();
        final String cId = commentsList.get(position).getCid();
        String comment = commentsList.get(position).getComment();
        String timestamp =commentsList.get(position).getTimestamp();


        Calendar calendar = Calendar.getInstance(Locale.getDefault());
        calendar.setTimeInMillis(Long.parseLong(timestamp));
        String pTime = DateFormat.format("dd/MM/yyyy hh:mm aa", calendar).toString();

        //holder.pComments.setText(pComments + " Comment");
        holder.nametv.setText(name);
        holder.commentTv.setText(comment);
        holder.timeTv.setText(pTime);


        try{
            Picasso.get().load(image).placeholder(R.drawable.ic_menu_camera).into(holder.avatarIv);

        }catch (Exception e){ }


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (myUid.equals(uid)){

                    // my comment
                    // show delete dialog
                    AlertDialog.Builder builder = new AlertDialog.Builder(v.getRootView().getContext());
                    builder.setTitle("Delete");
                    builder.setMessage("are you sure you want to delete this comment, it will completely gone");
                    builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            // delete comment
                            deleteComment(cId);


                        }
                    });
                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // dismiss the dialog
                            dialog.dismiss();
                        }
                    });

                    //Show the dialog
                    builder.create().show();


                }else {
                    Toast.makeText(context, "Can't delete other's comment", Toast.LENGTH_SHORT).show();

                }
            }
        });









    }

    private void deleteComment(String cId) {

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Blog").child(blogId).child("Comment");
        ref.child(cId).removeValue(); // this will delete the comment



    }

    @Override
    public int getItemCount() {
        return commentsList.size();
    }

    class MyHolder extends RecyclerView.ViewHolder{

        ImageView avatarIv;
        TextView commentTv, nametv, timeTv ;
        ImageButton commentMoreBtn;
        Button likeBtn, commentBtn, shareBtn;

        public MyHolder(@NonNull View itemView) {
            super(itemView);

            avatarIv = itemView.findViewById(R.id. avatarIv);
            nametv = itemView.findViewById(R.id. nameTv);
            commentTv = itemView.findViewById(R.id. commentTv);
            timeTv = itemView.findViewById(R.id. timeTv);
            commentMoreBtn = itemView.findViewById(R.id.commentMoreBtn);
        }
    }
}
