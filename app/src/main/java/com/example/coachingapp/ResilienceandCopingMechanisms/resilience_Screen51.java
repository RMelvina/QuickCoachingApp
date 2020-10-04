package com.example.coachingapp.ResilienceandCopingMechanisms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import com.example.coachingapp.AboutUs;
import com.example.coachingapp.Issues_Problems.IP_Question_Display_Summary;
import com.example.coachingapp.Login;
import com.example.coachingapp.R;
import com.example.coachingapp.UserDashboard;
import com.example.coachingapp.ViewStressSigns;
import com.example.coachingapp.ViewYourRoadMapSteps;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class resilience_Screen51 extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener {
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
    int len;

    String usrID;
    TextView expectationsTV_1, expectationsTV_2, expectationsTV_3, expectationsTV_4, expectationsTV_5, expectationsTV_6,gratefulTv_1,happyTv,
            purposeAndGoalsTV_1, purposeAndGoalsTV_2,controlTV_1, controlTV_2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resilience__screen51);
        nav();

        editText = findViewById(R.id.question_1_edit_text);

        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        myRef = FirebaseDatabase.getInstance().getReference();
        database = FirebaseDatabase.getInstance();


        expectationsTV_1 = findViewById(R.id.expectationsTV_1);
        expectationsTV_2 = findViewById(R.id.expectationsTV_2);
        expectationsTV_3 = findViewById(R.id.expectationsTV_3);
        expectationsTV_4 = findViewById(R.id.expectationsTV_4);
        expectationsTV_5 = findViewById(R.id.expectationsTV_5);
        expectationsTV_6 = findViewById(R.id.expectationsTV_6);
        gratefulTv_1 = findViewById(R.id.gratefulTv_1);
        purposeAndGoalsTV_1 = findViewById(R.id.purposeAndGoalsTV_1);
        purposeAndGoalsTV_2 = findViewById(R.id.purposeAndGoalsTV_2);
        controlTV_1  = findViewById(R.id.controlTV_1);
        controlTV_2 = findViewById(R.id.controlTV_2);
        happyTv = findViewById(R.id.happyTv);
        mLayout = findViewById(R.id.display_stress_signs);




        firebaseAuth = FirebaseAuth.getInstance();
        Query query = myRef.child("users").child(user.getUid());



        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {




                String ans_2 = dataSnapshot.child("Resilience").child("resilience_2").child("resilienceInput_2").getValue().toString();

                String ans_3 = dataSnapshot.child("Resilience").child("resilience_3").child("resilienceInput_3").getValue().toString();


                String ans4_1 = dataSnapshot.child("Resilience").child("resilience_4").child("resilienceInput_4").getValue().toString();
                String ans4_2 = dataSnapshot.child("Resilience").child("resilience_4").child("resilienceInput_5").getValue().toString();


                String ans5_1 = dataSnapshot.child("Resilience").child("resilience_5").child("resilienceInput_6").getValue().toString();
                String ans5_2 = dataSnapshot.child("Resilience").child("resilience_5").child("resilienceInput_7").getValue().toString();

                String ans6_1 = dataSnapshot.child("Resilience").child("resilience_6").child("resilienceInput_8").getValue().toString();
                String ans6_2 = dataSnapshot.child("Resilience").child("resilience_6").child("resilienceInput_9").getValue().toString();

                String ans7_1 = dataSnapshot.child("Resilience").child("resilience_7").child("resilienceInput_10").getValue().toString();
                String ans7_2 = dataSnapshot.child("Resilience").child("resilience_7").child("resilienceInput_11").getValue().toString();

                String ans8_1 = dataSnapshot.child("Resilience").child("resilience_8").child("resilienceInput_12").getValue().toString();
                String ans8_2 = dataSnapshot.child("Resilience").child("resilience_8").child("resilienceInput_13").getValue().toString();

                gratefulTv_1.setText(ans_2);
                happyTv.setText(ans_3);
                expectationsTV_1.setText(ans4_1);
                expectationsTV_2.setText(ans4_2);
                expectationsTV_3.setText(ans5_1);
                expectationsTV_4.setText(ans5_2);
                expectationsTV_5.setText(ans6_1);
                expectationsTV_6.setText(ans6_2);

                purposeAndGoalsTV_1.setText(ans7_1);
                purposeAndGoalsTV_2.setText(ans7_2);

                controlTV_1.setText(ans8_1);
                controlTV_2.setText(ans8_2);
//




            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });








    }



    public void btn_home(View view) {
        Intent intent = new Intent(resilience_Screen51.this, UserDashboard.class);
        startActivity(intent);
    }

    public void btn_viewStressSigns(View view) {
        Intent intent = new Intent(resilience_Screen51.this, ViewStressSigns.class);
        startActivity(intent);
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
}
