package com.example.coachingapp.Issues_Problems;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.coachingapp.AboutUs;
import com.example.coachingapp.Login;
import com.example.coachingapp.Models.Issues_2;
import com.example.coachingapp.Models.Issues_3;
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

public class IP_Question_Two_Part_Two extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    ImageButton imageIconRight;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;

    FirebaseAuth firebaseAuth;
    FirebaseDatabase database;
    DatabaseReference myRef;
    FirebaseUser user;
    private boolean isClicked = false;
    TextView text;
    Button addbtn,minusbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ip__question__two__part__two);

        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        myRef = FirebaseDatabase.getInstance().getReference();
        database = FirebaseDatabase.getInstance();

        text = findViewById(R.id.number_part_2);
        addbtn = findViewById(R.id.add_part_2);
        minusbtn = findViewById(R.id.minus_part_2);

        nav();
    }



    private void singOut() {
        FirebaseAuth.getInstance().signOut();
        finish();
        startActivity(new Intent(this, Login.class));
    }

    private void nav() {
        drawerLayout = findViewById(R.id.layout_drawer);
        imageIconRight = findViewById(R.id.open_drawer_icon);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        drawerLayout.setDrawerListener(toggle);
        imageIconRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawerLayout.isDrawerOpen(navigationView)) {
                    drawerLayout.closeDrawer(navigationView);
                } else if (!drawerLayout.isDrawerOpen(navigationView)) {
                    drawerLayout.openDrawer(navigationView);
                }
            }
        });
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()) {
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

    public void onAddClicked(View view) {
        String numberCount = text.getText().toString().trim();
        int count = Integer.parseInt(numberCount);
        count++;
        text.setText(String.valueOf(count));
    }

    public void onMinusClicked(View view) {
        String numberCount = text.getText().toString().trim();
        int count = Integer.parseInt(numberCount);
        count--;
        text.setText(String.valueOf(count));
    }

    public void btn_next(View view) {
        if(Integer.parseInt(text.getText().toString()) < 1 || Integer.parseInt(text.getText().toString()) > 10) {
            Toast.makeText(getApplicationContext(), "Please select right number", Toast.LENGTH_SHORT).show();
        }else {
            if(Integer.parseInt(text.getText().toString()) ==9 || Integer.parseInt(text.getText().toString()) ==10){
                Intent intent = new Intent(IP_Question_Two_Part_Two.this, IP_QuestionThree.class);
                startActivity(intent);
                addToFirebase();
            }else{
                Intent intent = new Intent(IP_Question_Two_Part_Two.this, IP_QuestionSix.class);
                startActivity(intent);
                addToFirebase();
        }

        }
    }


    private void addToFirebase() {

        String answer_3 = text.getText().toString();

        if (user != null) {
            Issues_2 issues_3 = new Issues_2(answer_3);
            String id = user.getUid();
            myRef.child("users").child(id).child("Issues").child("answer_2").setValue(issues_3);
            Toast.makeText(this, "Information Saved", Toast.LENGTH_SHORT).show();
//            Intent intent = new Intent(IssuesAndProblemsPart3.this, IssuesAndProblemsPart4.class);
//            startActivity(intent);
        }
    }
}
