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
import android.widget.TextView;
import android.widget.Toast;

import com.example.coachingapp.AboutUs;
import com.example.coachingapp.Login;
import com.example.coachingapp.Models.Issues_6;
import com.example.coachingapp.R;
import com.example.coachingapp.UserDashboard;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

public class IP_QuestionEight extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener  {
    ImageButton imageIconRight;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;

    Button yesbtn,nobtn;
    TextView textView;
    private int buttonState = 1;

//    Button btnNext;

    EditText editText;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase database;
    DatabaseReference myRef;
    FirebaseUser user;
    private boolean isClicked = false;

    Button myButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ip__question_eight);

        editText = findViewById(R.id.why_to_over_come_this_problem_text_box_2);
        myButton = findViewById(R.id.resilience_btn);
        editText.addTextChangedListener(inputText);


        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        myRef = FirebaseDatabase.getInstance().getReference();
        database = FirebaseDatabase.getInstance();

        nav();

        editText.addTextChangedListener(inputText);

    }

//    private TextWatcher inputText = new TextWatcher() {
//        @Override
//        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//        }
//
//        @Override
//        public void onTextChanged(CharSequence s, int start, int before, int count) {
//            String answer = editText.getText().toString().trim();
//
//            btnNext.setVisibility(View.VISIBLE);
//
//        }
//
//        @Override
//        public void afterTextChanged(Editable s) {
//
//        }
//    }


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




    private void addToFirebase() {

        String answer_6 = editText.getText().toString().trim();

        if (user != null) {

            Issues_6 issues_6 = new Issues_6(answer_6);
            String id = user.getUid();
            myRef.child("users").child(id).child("Issues").child("answer_6").setValue(issues_6);
            Toast.makeText(this, "Information Saved", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(IP_QuestionEight.this, IP_Question_9.class);
            startActivity(intent);
        }
    }

    //resilience_btn



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


    public void btn_next_resilience(View view) {
        if(editText.getText().toString().equals("")) {
            Toast.makeText(getApplicationContext(), "Please enter your issues", Toast.LENGTH_SHORT).show();
        }else {
            addToFirebase();
        }
    }
}
