package com.example.coachingapp.Goals;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.coachingapp.AboutUs;
import com.example.coachingapp.Login;
import com.example.coachingapp.Models.User_Goal_3;
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

public class Goal_Question_3 extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    FirebaseAuth mAuth;
    DatabaseReference myRef;
    FirebaseDatabase mFirebaseDatabase;
    FirebaseAuth firebaseAuth;
    FirebaseUser user;
    FirebaseAuth.AuthStateListener mAuthListener;
    String usrID;
    TextView textView_1,textView_2;
    EditText goal_7_edit_text,goal_7_edit_text_2;

    ImageButton imageIconRight;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goal__question_3);

        mAuth = FirebaseAuth.getInstance();

        goal_7_edit_text = findViewById(R.id.goal_question_3_box_2);
        goal_7_edit_text_2 = findViewById(R.id.goal_question_3_box_4);




        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        myRef = FirebaseDatabase.getInstance().getReference();
        mFirebaseDatabase = FirebaseDatabase.getInstance();

       // usrID = user.getUid();
        myRef = mFirebaseDatabase.getInstance().getReference();//.child("users").child(user.getUid());

        textView_1 = (TextView) findViewById(R.id.goal_question_3_box_1);
        textView_2 = (TextView) findViewById(R.id.goal_question_3_box_3);


        nav();


        Query query = myRef.child("users").child(user.getUid());

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String ans = dataSnapshot.child("Goals").child("input_1").child("user_input_1").getValue().toString();
                String ans_2 = dataSnapshot.child("Goals").child("input_2").child("user_input_2").getValue().toString();

                textView_1.setText(ans);
                textView_2.setText(ans_2);
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

    private void addToFirebase() {

        String input_1 = goal_7_edit_text.getText().toString().trim();
        String input_2 = goal_7_edit_text_2.getText().toString().trim();

        if (user != null) {

            User_Goal_3 user_goal_3 = new User_Goal_3(input_1,input_2);
            String id = user.getUid();
            myRef.child("users").child(id).child("Goals").child("input_3_and_input_4").setValue(user_goal_3);
            Toast.makeText(this, "Information Saved", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Goal_Question_3.this, Goal_7.class);
            startActivity(intent);
        }
    }

    public void btn_next_my_goals_more_details(View view) {
        if(goal_7_edit_text.getText().toString().equals("")) {
            Toast.makeText(getApplicationContext(), "Please enter your goals", Toast.LENGTH_SHORT).show();
        }else {
            addToFirebase();
        }
    }
}
