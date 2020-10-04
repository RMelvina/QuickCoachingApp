package com.example.coachingapp.Issues_Problems;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.coachingapp.AboutUs;
import com.example.coachingapp.Login;
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

public class IP_Question_Display_Summary extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    ImageButton imageIconRight;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;

    EditText editText;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase database;
    DatabaseReference myRef;
    FirebaseUser user;

    String usrID;
    TextView textView_1,textView_2,textView_3,textView_4, textView_5, textView_6,textView_7,textView_8,textView_9, textView_10, textView_11, textView_7_part_1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ip__question__display__summary);

        nav();
        editText = findViewById(R.id.question_1_edit_text);

        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        myRef = FirebaseDatabase.getInstance().getReference();
        database = FirebaseDatabase.getInstance();

        textView_1 = (TextView) findViewById(R.id.playback_text_1);
        textView_2 = (TextView) findViewById(R.id.playback_text_2);
        textView_3 = (TextView) findViewById(R.id.playback_text_3);
        textView_4 = (TextView) findViewById(R.id.playback_text_4);
        textView_5 = (TextView) findViewById(R.id.playback_text_5);
        textView_6 = (TextView) findViewById(R.id.playback_text_6);
        textView_7 = (TextView) findViewById(R.id.playback_text_7);
        textView_7_part_1 = (TextView) findViewById(R.id.playback_text_7_part_1);
        textView_8 = (TextView) findViewById(R.id.playback_text_8);

        textView_9 = (TextView) findViewById(R.id.playback_text_9);
        textView_10 = (TextView) findViewById(R.id.playback_text_10);
        textView_11 = (TextView) findViewById(R.id.playback_text_11);

        firebaseAuth = FirebaseAuth.getInstance();
        Query query = myRef.child("users").child(user.getUid());

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String ans1 = dataSnapshot.child("Issues").child("answers_1").child("question_1").getValue().toString();
                String ans_2 = dataSnapshot.child("Issues").child("answer_2").child("question_2").getValue().toString();

               String ans_3 = dataSnapshot.child("Issues").child("answer_4").child("answer_4").getValue().toString();


               String ans_4 = dataSnapshot.child("Issues").child("answer_5").child("question_5").getValue().toString();

                String ans_5 = dataSnapshot.child("Issues").child("answer_6").child("question_6").getValue().toString();

                String ans_6 = dataSnapshot.child("Issues").child("answer_12").child("question_12").getValue().toString();

                String ans_7 = dataSnapshot.child("Issues").child("answer_13_answer_14").child("question_13").getValue().toString();

                String ans_7_part_1  = dataSnapshot.child("Issues").child("answer_13_answer_14").child("question_14").getValue().toString();

                String ans_8 = dataSnapshot.child("Issues").child("answer_9").child("question_9").getValue().toString();
                String ans_9 = dataSnapshot.child("Issues").child("answer_10").child("question_10").getValue().toString();

                String ans_10 = dataSnapshot.child("Issues").child("answer_8").child("question_8").getValue().toString();

                String ans_11 = dataSnapshot.child("Issues").child("answer_10").child("question_10").getValue().toString();




                textView_1.setText(ans1);
                textView_2.setText(ans_2);
                textView_3.setText(ans_3);
                textView_4.setText(ans_4);
                textView_5.setText(ans_5);
                textView_6.setText(ans_6);
                textView_7.setText(ans_7);
                textView_7_part_1.setText(ans_7_part_1);
                textView_8.setText(ans_8);
                textView_9.setText(ans_9);
                textView_10.setText(ans_10);
                textView_11.setText(ans_11);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
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

    public void btn_home(View view) {
        Intent intent = new Intent(IP_Question_Display_Summary.this, UserDashboard.class);
        startActivity(intent);
    }
}
