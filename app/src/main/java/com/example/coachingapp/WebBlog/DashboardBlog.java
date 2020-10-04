package com.example.coachingapp.WebBlog;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SearchView;

import com.example.coachingapp.R;
import com.example.coachingapp.ResilienceandCopingMechanisms.resilience_Screen25;
import com.example.coachingapp.WebBlog.Fragments.HomeFragment;

import com.example.coachingapp.WebBlog.Fragments.UserFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.FragmentTransaction;

public class DashboardBlog extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
    FirebaseDatabase database;
    DatabaseReference myRef;
    FirebaseUser user;
    ImageButton searchBtn;
    Intent intent;


//    ActionBar actionBar;
////    android.app.ActionBar bar;

    ActionBar actionBar;


//btn_search
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_blog);

        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        myRef = FirebaseDatabase.getInstance().getReference();
        database = FirebaseDatabase.getInstance();


        actionBar = getSupportActionBar();

         intent = getIntent();
        intent.getStringExtra("move");





       // actionBar = getSupportActionBar();

//       actionBar = getSupportActionBar();
//        actionBar.setTitle("Home Feed");



//        Toolbar toolbar = findViewById(R.id.blog_toolbar);
//        setSupportActionBar(toolbar);

//         actionBar = getSupportActionBar();
//        actionBar.setTitle("Profile");

        BottomNavigationView navigationView = findViewById(R.id.navigation);
        navigationView.setOnNavigationItemSelectedListener(selectedListener);


        actionBar.setTitle("Home Feed");
        HomeFragment fragment1 = new HomeFragment();
        FragmentTransaction ft1 = getSupportFragmentManager().beginTransaction();
        ft1.replace(R.id.content, fragment1,"");
        ft1.commit();
    }








    private BottomNavigationView.OnNavigationItemSelectedListener selectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                    switch(menuItem.getItemId()){
                        case R.id.nav_home:
                            actionBar.setTitle("Home feed");
                            HomeFragment fragment1 = new HomeFragment();
                            FragmentTransaction ftl = getSupportFragmentManager().beginTransaction();
                            ftl.replace(R.id.content, fragment1, "");
                            ftl.commit();
                        return true;
                        // Add new blog
                        case R.id.nav_profile:
                            actionBar.setTitle("Add new blog");

                            Intent intent = new Intent(DashboardBlog.this, Blog.class);
                            startActivity(intent);
//                            ProfileFragment fragment2 = new ProfileFragment();
//                            FragmentTransaction ft2 = getSupportFragmentManager().beginTransaction();
//                            ft2.replace(R.id.content, fragment2, "");
//                            ft2.commit();
                            return true;

                    }
                    return false;
                }


            };

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//
//        MenuItem item1 = menu.findItem(R.id.arrow_back);
//        if (intent.equals("move")){
//
//            item1.setVisible(true);
//            item1.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
//                @Override
//                public boolean onMenuItemClick(MenuItem item) {
//                    Intent back = new Intent(DashboardBlog.this, resilience_Screen25.class);
//                    startActivity(back);
//                    return true;
//                }
//            });
//
//
//
//
//
//        }
//
//        return super.onCreateOptionsMenu(menu);
//    }


    //    private void searchBlogs(String searchQuery){
//
//
//    }
//    @Override
//    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
//        getMenuInflater().inflate(R.menu.blog_menu, menu);
//
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
