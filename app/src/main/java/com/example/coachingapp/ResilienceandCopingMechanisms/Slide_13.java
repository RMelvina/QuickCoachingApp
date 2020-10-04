package com.example.coachingapp.ResilienceandCopingMechanisms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.coachingapp.AboutUs;
import com.example.coachingapp.Login;
import com.example.coachingapp.Models.Resilience_1;
import com.example.coachingapp.R;
import com.example.coachingapp.UserDashboard;
import com.example.coachingapp.ViewStressSigns;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Slide_13 extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    ImageButton imageIconRight;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;

    EditText editText;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase database;
    DatabaseReference myRef;
    FirebaseUser user;
    LinearLayout mLayout;
    List<String> signsList;
    ScrollView goalDisplayParentView, goalDisplayChildView;
    int len;

    Button myButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide_13);
        nav();

        editText = findViewById(R.id.question_1_edit_text);

        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        myRef = FirebaseDatabase.getInstance().getReference();
        database = FirebaseDatabase.getInstance();
        mLayout = findViewById(R.id.showsings);

        goalDisplayParentView = findViewById(R.id.goalDisplayParentView);
        goalDisplayChildView = findViewById(R.id.goalDisplayChildView);

        goalDisplayParentView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                findViewById(R.id.goalDisplayParentView).getParent().requestDisallowInterceptTouchEvent(false);
                return false;
            }
        });


        goalDisplayChildView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                v.getParent().requestDisallowInterceptTouchEvent(true);

                return false;
            }
        });







        Query displayStressSigns = myRef.child("users").child(user.getUid()).child("Resilience").child("behavioural").child("choices");
        displayStressSigns.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                signsList = new ArrayList<>();

                for (DataSnapshot ds: dataSnapshot.getChildren()){


                    String s = ds.getValue(String.class);
                    signsList.add(s);
                }
//
//
                len = signsList.size();

                for (int i = 0; i < len; i++) {



                    TextView ed = new TextView(Slide_13.this);
                    ed.setText(signsList.get(i).toString());
                    TableLayout.LayoutParams layoutParams = new TableLayout.LayoutParams();
                    ed.setTextColor(Color.BLACK);
                    ed.setLayoutParams(layoutParams);
                    ed.setEnabled(false);
                    ed.setTextSize(18);
                    ed.setBackground(getDrawable(R.drawable.text_box));
                   layoutParams.setMargins(0, 0, 0, 0);
                    mLayout.addView(ed);


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        Query displayStressSignsP = myRef.child("users").child(user.getUid()).child("Resilience").child("resilience_10").child("choices");
        displayStressSignsP.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                signsList = new ArrayList<>();

                for (DataSnapshot ds: dataSnapshot.getChildren()){


                    String s = ds.getValue(String.class);
                    signsList.add(s);
                }
//
//
                len = signsList.size();

                for (int i = 0; i < len; i++) {
                   // editText.setText(signsList.get(i).toString());
                    TextView ed = new TextView(Slide_13.this);
                    ed.setText(signsList.get(i).toString());
                    TableLayout.LayoutParams layoutParams = new TableLayout.LayoutParams();
                    ed.setBackground(getDrawable(R.drawable.text_box));
                    ed.setTextColor(Color.BLACK);
                    ed.setLayoutParams(layoutParams);
                    ed.setEnabled(false);
                    ed.setTextSize(18);
                    layoutParams.setMargins(0, 0, 0, 0);

                    mLayout.addView(ed);


                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        Query displayStressSignsIT = myRef.child("users").child(user.getUid()).child("Resilience").child("resilience_11").child("choices");
        displayStressSignsIT.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                signsList = new ArrayList<>();

                for (DataSnapshot ds: dataSnapshot.getChildren()){


                    String s = ds.getValue(String.class);
                    signsList.add(s);
                }
//
//
                len = signsList.size();

                for (int i = 0; i < len; i++) {
                   // editText.setText(signsList.get(i).toString());
                    TextView ed = new TextView(Slide_13.this);
                    ed.setText(signsList.get(i).toString());
                    TableLayout.LayoutParams layoutParams = new TableLayout.LayoutParams();
                    ed.setBackground(getDrawable(R.drawable.text_box));
                    ed.setTextColor(Color.BLACK);
                    ed.setTextSize(18);
                    ed.setLayoutParams(layoutParams);
                    ed.setEnabled(false);
                    layoutParams.setMargins(0, 0, 0, 0);
                    mLayout.addView(ed);


                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    public void btn_resilience_question_one(View view) {
        Intent intent = new Intent(Slide_13.this, Slide_14.class);
        startActivity(intent);

//        if (editText.getText().toString().equals("")) {
//            Toast.makeText(getApplicationContext(),"Please enter something...", Toast.LENGTH_SHORT).show();
//        } else {
//            addToFirebase();
//        }
    }

//    private void addToFirebase() {
//        String input_1 = editText.getText().toString().trim();
//
//        if (user != null) {
//
//            Resilience_1 resilience = new Resilience_1(input_1);
//            String id = user.getUid();
////            myRef.child("users").child(id).push().setValue(issues);
//            myRef.child("users").child(id).child("Resilience").child("resilience_1").setValue(resilience);
//
//            Toast.makeText(this, "Information Saved", Toast.LENGTH_SHORT).show();
//            Intent intent = new Intent(Slide_13.this, Slide_14.class);
//            startActivity(intent);
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
}
