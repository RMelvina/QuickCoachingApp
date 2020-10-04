package com.example.coachingapp.WebBlog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.coachingapp.Models.ModelPost;
import com.example.coachingapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

//import com.example.coachingapp.adapters.AdapterPost;


public class DisplayPostRecycleView extends Fragment {

    FirebaseAuth firebaseAuth;
    FirebaseDatabase database;
    DatabaseReference myRef;
    FirebaseUser user;

    RecyclerView recyclerView;
    List<ModelPost> postList;
    //AdapterPost adapterPost;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_display_post_recycle_view, container, false);


        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        myRef = FirebaseDatabase.getInstance().getReference();
        database = FirebaseDatabase.getInstance();
        postList = new ArrayList<>();

        //checkUserStatus();
//        recyclerView = view.findViewById(R.id.postRecyclerView);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
//        layoutManager.setStackFromEnd(true);
//        layoutManager.setReverseLayout(true);
//        // set layout to recycleview
//        recyclerView.setLayoutManager(layoutManager);
//        adapterPost = new AdapterPost(getActivity(), postList);
//        recyclerView.setAdapter(adapterPost);



       // loadPost();

        return view;
        //return super.onCreateView(inflater, container, savedInstanceState);
    }


}
