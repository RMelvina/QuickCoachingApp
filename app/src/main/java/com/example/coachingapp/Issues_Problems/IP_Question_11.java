package com.example.coachingapp.Issues_Problems;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.coachingapp.AboutUs;
import com.example.coachingapp.Login;
import com.example.coachingapp.Models.Issues_7;
import com.example.coachingapp.R;
import com.example.coachingapp.UserDashboard;
import com.google.android.material.navigation.NavigationView;
import com.google.android.youtube.player.YouTubePlayerView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

public class IP_Question_11 extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    EditText editText;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase database;
    DatabaseReference myRef;
    FirebaseUser user;
    private boolean isClicked = false;
    ImageButton imageIconRight;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;
    Button myButton;

    YouTubePlayerView playerView;
    String API_KEY = "AIzaSyBWlnrHL6YB_xIfym7WoBs52EmgvNeVzY8";
    String VIDEO_ID = "brpkjT9m2Oo&t=13s";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ip__question_11);

        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        myRef = FirebaseDatabase.getInstance().getReference();
        database = FirebaseDatabase.getInstance();

        editText = findViewById(R.id.positive_influence_text_box);
        myButton = findViewById(R.id.positive_influence_btn);
        editText.addTextChangedListener(inputText);

        playerView = findViewById(R.id.yourtube_play_video);


        nav();




//        playerView.initialize(API_KEY, new YouTubePlayer.OnInitializedListener() {
//            @Override
//            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
//                youTubePlayer.loadVideo(VIDEO_ID);
//
//            }
//
//            @Override
//            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
//
//            }
//        });youTubeInitializationResult
    }




    private void addToFirebase() {

        String answer_7 = editText.getText().toString().trim();

        if (user != null) {

            Issues_7 issues_7 = new Issues_7(answer_7);
            String id = user.getUid();
            myRef.child("users").child(id).child("Issues").child("answer_7").setValue(issues_7);
            Toast.makeText(this, "Information Saved", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(IP_Question_11.this, IP_Question_12.class);
            startActivity(intent);
        }
    }

    private void nav(){
        drawerLayout = findViewById(R.id.layout_drawer);
        imageIconRight = findViewById(R.id.open_drawer_icon);

        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        drawerLayout.setDrawerListener(toggle);
        imageIconRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(drawerLayout.isDrawerOpen(navigationView)){
                    drawerLayout.closeDrawer(navigationView);
                }else if(!drawerLayout.isDrawerOpen(navigationView)){
                    drawerLayout.openDrawer(navigationView);
                }
            }
        });
    }

    private void singOut(){
        FirebaseAuth.getInstance().signOut();
        finish();
        startActivity(new Intent(this, Login.class));
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()){
            case R.id.nav_aboutus:

                Intent aboutUs = new Intent(getApplicationContext(), AboutUs.class);
                startActivity(aboutUs);
                drawerLayout.closeDrawers();

                break;
            case R.id.nav_singout:
                singOut();
                break;
            case R.id.nav_home:
                Intent home = new Intent(getApplicationContext(), UserDashboard.class);
                startActivity(home);
                break;

        }

        return true;
    }


    private TextWatcher inputText = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String answer = editText.getText().toString().trim();

            if(answer.length() >= 4)
            {
                myButton.setVisibility(View.VISIBLE);

            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    public void btn_next_positive_influence(View view) {
        if(editText.getText().toString().equals("")) {
            Toast.makeText(getApplicationContext(), "Please enter your issues", Toast.LENGTH_SHORT).show();
        }else if(editText.getText().toString().equals("nothing") || editText.getText().toString().equals("not sure")  ){


            addToFirebase();

        }else {
            addToFirebase();
        }
    }

//
//    private void dialogBoxHelp() {
//
//        final AlertDialog.Builder alert = new AlertDialog.Builder(IP_Question_11.this);
//        alert.setTitle("Help");
//        alert.setCancelable(true);
//        View mView = getLayoutInflater().inflate(R.layout.are_you_sure_custom_box, null);
//        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//
//            }
//        });


//        alert.setView(mView);
//
//
//        final AlertDialog alertDialog = alert.create();
//        alertDialog.show();
//
//
//    }
}
