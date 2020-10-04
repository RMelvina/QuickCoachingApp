package com.example.coachingapp.Issues_Problems;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.coachingapp.AboutUs;
import com.example.coachingapp.Goals.Goal_DisplaySummary;
import com.example.coachingapp.Login;
import com.example.coachingapp.Models.DateTimeDay;
import com.example.coachingapp.R;
import com.example.coachingapp.UserDashboard;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

public class LastQuestion extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    ImageButton imageIconRight;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;

    FirebaseAuth firebaseAuth;
    FirebaseDatabase database;
    DatabaseReference myRef;
    FirebaseUser user;
    EditText dayTxt, timeTxt, monthTxt;
    TextView stepTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last_question);
        nav();
        //setTimer();

        dayTxt = findViewById(R.id.day_text_box);
        timeTxt = findViewById(R.id.time_text_box);
        monthTxt = findViewById(R.id.month_text_box);
       // stepTxt = findViewById(R.id.display_step_one);

        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        myRef = FirebaseDatabase.getInstance().getReference();
        database = FirebaseDatabase.getInstance();


//        Query query = myRef.child("users").child(user.getUid());
//        query.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                String ans = dataSnapshot.child("Goals").child("Steps").child("answers").child("0").getValue().toString();
//                stepTxt.setText(ans);
//            }
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });



    }

//    public void setTimer(){
//        final Button button = (Button)findViewById(R.id.btn_next_my_rd_map_2);
//        final Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_in);
//
//
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                button.setVisibility(View.VISIBLE);
//                button.startAnimation(animation);
//
//
//            }
//        },5000);
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

    public void btn_next(View view) {
        addToFirebase();
    }

    private void addToFirebase(){

        String time = timeTxt.getText().toString();
        String day = dayTxt.getText().toString();
        String month = monthTxt.getText().toString();


        if (user != null){

            DateTimeDay dateTimeDay = new DateTimeDay(time, day, month);
            String id = user.getUid();
            myRef.child("users").child(id).child("Time_Day_Month").setValue(dateTimeDay);


            Toast.makeText(this, "Information Saved", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(LastQuestion.this, Goal_DisplaySummary.class);
            startActivity(intent);

        }




    }
}
