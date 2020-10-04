package com.example.coachingapp.Goals;

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
import com.example.coachingapp.Models.User_Goal_2;
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

public class Goal_Question_2 extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    ImageButton imageIconRight;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;

    EditText goal_2_edit_text;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase database;
    DatabaseReference myRef;
    FirebaseUser user;

    Button goal_question_2_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goal__question_2);

        goal_2_edit_text = findViewById(R.id.goal_question_2);
        goal_question_2_btn = findViewById(R.id.goal_question_2_btn);
        goal_2_edit_text.addTextChangedListener(inputText);

        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        myRef = FirebaseDatabase.getInstance().getReference();
        database = FirebaseDatabase.getInstance();

        nav();
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

    private TextWatcher inputText = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String answer = goal_2_edit_text.getText().toString().trim();

            if(answer.length() >= 4)
            {
                goal_question_2_btn.setVisibility(View.VISIBLE);

            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

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


    public void btn_goal_question_2_next(View view) {
        if(goal_2_edit_text.getText().toString().equals("")) {
            Toast.makeText(getApplicationContext(), "Please enter your reasons", Toast.LENGTH_SHORT).show();
        }else {
            addToFirebase();
        }
    }

    private void addToFirebase() {

        String input_2 = goal_2_edit_text.getText().toString().trim();

        if (user != null) {

            User_Goal_2 user_goal_2 = new User_Goal_2(input_2);
            String id = user.getUid();
            myRef.child("users").child(id).child("Goals").child("input_2").setValue(user_goal_2);
            Toast.makeText(this, "Information Saved", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Goal_Question_2.this, Goal_3.class);
            startActivity(intent);
        }
    }
}
