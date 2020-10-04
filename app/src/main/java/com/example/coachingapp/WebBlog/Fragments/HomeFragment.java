package com.example.coachingapp.WebBlog.Fragments;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.coachingapp.Models.ModelPost;
import com.example.coachingapp.R;
import com.example.coachingapp.WebBlog.Adapters.AdapterPost;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

//import com.example.coachingapp.adapters.AdapterPost;


public class HomeFragment extends Fragment {
    FirebaseAuth firebaseAuth;
    FirebaseDatabase database;
    DatabaseReference myRef;
    FirebaseUser user;
    FirebaseAuth.AuthStateListener mAuthListener;

    FloatingActionButton actionButton;
    RecyclerView recyclerView;
    List<ModelPost> postList;
    AdapterPost adapterPost;

    String  blogId;
    ActionBar actionBar;

    public HomeFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);


        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
       // myRef = FirebaseDatabase.getInstance().getReference().child("users").child(user.getUid()).child("Blog");
        myRef = FirebaseDatabase.getInstance().getReference();
        database = FirebaseDatabase.getInstance();

        postList = new ArrayList<>();
        recyclerView = view.findViewById(R.id.postRecyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());


        //Show new blogs first
        layoutManager.setStackFromEnd(true);
        layoutManager.setReverseLayout(true);
        recyclerView.setLayoutManager(layoutManager);
        adapterPost = new AdapterPost(getActivity(), postList);
        recyclerView.setAdapter(adapterPost);


//getSupportActionBar()
//       androidx.appcompat.app.ActionBar actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
//        actionBar.setTitle("Home Feed");

//        actionButton = view.findViewById(R.id.floatingActionButton1);







        checkUserStatus();

        // set layout to recycleview



        loadPost();

//        actionButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent in = new Intent(getActivity(), Blog.class);
//                startActivity(in);
//            }
//        });

        return view;
        //return super.onCreateView(inflater, container, savedInstanceState);
    }

//    private void searchForBlogs(String searchQuery ){
//
//    }


//    @Override
//    public void onStart() {

    //child(user.getUid()).child("Blog");

    // DatabaseReference ref = FirebaseDatabase.getInstance().getReference("users").child(user.getUid());
    //child(user.getUid())
//        super.onStart();
//        firebaseAuth.addAuthStateListener(mAuthListener);
//    }

    private void loadPost() {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Blog");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                postList.clear();
                for (DataSnapshot ds: dataSnapshot.getChildren()){
                    ModelPost modelPost = ds.getValue(ModelPost.class);

                    postList.add(modelPost);

                    adapterPost = new AdapterPost(getActivity(), postList);
                    recyclerView.setAdapter(adapterPost);
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
              //  Toast.makeText(getActivity(), ""+databaseError.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

//    private void DisplayAllUserPost(){
//        FirebaseRecyclerAdapter<ModelPost, adapterPost>
//    }





    private void searchBlogs(final String searchQuery){

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Blog");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                postList.clear();
                for (DataSnapshot ds: dataSnapshot.getChildren()){
                    ModelPost modelPost = ds.getValue(ModelPost.class);



                    if (modelPost.getpTitle().toLowerCase().contains(searchQuery.toLowerCase()) ||
                            modelPost.getpDescr().toLowerCase().contains(searchQuery.toLowerCase())){

                        postList.add(modelPost);
                    }


                    adapterPost = new AdapterPost(getActivity(), postList);
                    recyclerView.setAdapter(adapterPost);
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getActivity(), ""+databaseError.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });




    }




    private void checkUserStatus(){
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if(user!=null){

        }else {


        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.blog_menu, menu);

        MenuItem item = menu.findItem(R.id.action_search);

        SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // called when the user press search button
                if (!TextUtils.isEmpty(query)){
                    searchBlogs(query);
                }else {


                    loadPost();
                }

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // called when the user press any letter

                if (!TextUtils.isEmpty(newText)){
                    searchBlogs(newText);
                }else {


                    loadPost();
                }
                return false;
            }
        });

         super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }
}
