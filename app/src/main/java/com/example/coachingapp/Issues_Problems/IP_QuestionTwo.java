package com.example.coachingapp.Issues_Problems;

import android.app.AlertDialog;
import android.content.DialogInterface;
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

public class IP_QuestionTwo extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private boolean isClicked = false;
    ImageButton imageIconRight;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;

    TextView text;
    Button addbtn, minusbtn;

    FirebaseAuth firebaseAuth;
    FirebaseDatabase database;
    DatabaseReference myRef;
    FirebaseUser user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ip__question_two);

        nav();
        vaildate();

        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        myRef = FirebaseDatabase.getInstance().getReference();
        database = FirebaseDatabase.getInstance();
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


    private void vaildate() {
        text = findViewById(R.id.number);
        addbtn = findViewById(R.id.add);
        minusbtn = findViewById(R.id.minus);

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





    private void dialogBoxTwo(){

        final AlertDialog.Builder alert = new AlertDialog.Builder(IP_QuestionTwo.this);
        alert.setTitle("Help");
        alert.setCancelable(true);
        View mView = getLayoutInflater().inflate(R.layout.custom_dialog_box_three, null);
        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });


        alert.setView(mView);


        final AlertDialog alertDialog = alert.create();
        alertDialog.show();
    }


    private void addToFirebase() {

        String answer_2 = text.getText().toString();

        if (user != null) {
            Issues_2 issues_2 = new Issues_2(answer_2);
            String id = user.getUid();
            myRef.child("users").child(id).child("Issues").child("answer_2").setValue(issues_2);
            Toast.makeText(this, "Information Saved", Toast.LENGTH_SHORT).show();
//            Intent intent = new Intent(IP_QuestionTwo.this, IP_QuestionThree.class);
//            startActivity(intent);
        }


        }

        //ip_question_2_btn

    public void btn_ip_question_2__next(View view) {
        if (Integer.parseInt(text.getText().toString()) < 1 || Integer.parseInt(text.getText().toString()) > 10) {
            Toast.makeText(getApplicationContext(), "Please select number between 1 to 10", Toast.LENGTH_SHORT).show();
        } else {
            if (Integer.parseInt(text.getText().toString()) >= 7 && Integer.parseInt(text.getText().toString()) <= 10) {
                addToFirebase();

                Intent intent = new Intent(IP_QuestionTwo.this, IP_QuestionTwo_Part_one.class);
                startActivity(intent);

            }else {
                Intent intent = new Intent(IP_QuestionTwo.this, IP_QuestionSix.class);
                startActivity(intent);
                addToFirebase();

            }
        }
    }
}

