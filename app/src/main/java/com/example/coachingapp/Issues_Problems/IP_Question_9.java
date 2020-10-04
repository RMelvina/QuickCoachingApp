package com.example.coachingapp.Issues_Problems;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.coachingapp.AboutUs;
import com.example.coachingapp.Login;
import com.example.coachingapp.Models.Issues_12;
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

public class IP_Question_9 extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    ImageButton imageIconRight;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;

    Button yesbtn,nobtn;
    TextView textView;
    private int buttonState = 1;

    EditText editText;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase database;
    DatabaseReference myRef;
    FirebaseUser user;

    Button myButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ip__question_9);

        nav();

        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        myRef = FirebaseDatabase.getInstance().getReference();
        database = FirebaseDatabase.getInstance();

        textView = (TextView) findViewById(R.id.number);
        myButton = findViewById(R.id.respose_so_far_btn);

        yesbtn = (Button) findViewById(R.id.btnYes);
        nobtn = (Button) findViewById(R.id.btnNo);

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

    public void btn_no(View view) {
        if(buttonState % 2 == 0){
            yesbtn.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

        }
        else{
            yesbtn.setBackgroundColor(getResources().getColor(R.color.colorAccent));
            nobtn.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            textView.setText("No");
            Toast.makeText(getBaseContext(), "You are choosing No", Toast.LENGTH_SHORT).show();
        }
        buttonState++;

    }

    public void btn_yes(View view) {

        if(buttonState % 2 == 0){
            yesbtn.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        }
        else{
            yesbtn.setBackgroundColor(getResources().getColor(R.color.colorAccent));
            nobtn.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            textView.setText("Yes");
            Toast.makeText(getBaseContext(), "You are choosing YES", Toast.LENGTH_SHORT).show();
        }
        buttonState++;
    }

    private void addToFirebase() {

        String answer_12 = textView.getText().toString();

        if (user != null) {
            Issues_12 issues_12 = new Issues_12(answer_12);
            String id = user.getUid();
            myRef.child("users").child(id).child("Issues").child("answer_12").setValue(issues_12);
            Toast.makeText(this, "Information Saved", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(IP_Question_9.this, IP_Question_10.class);
            startActivity(intent);
        }
    }

    private void clicked(){
        if (!yesbtn.isPressed() || !nobtn.isPressed()){
            myButton.setVisibility(View.INVISIBLE);

        }else if(yesbtn.isPressed() || nobtn.isPressed()){

            myButton.setVisibility(View.VISIBLE);

        }
    }



    public void btn_next_respose_so_far(View view) {

        if(textView.getText().toString().equals("1")){
            Toast.makeText(IP_Question_9.this, "Please select either yes or no", Toast.LENGTH_LONG).show();
        }else {
            addToFirebase();
        }

    }
}
