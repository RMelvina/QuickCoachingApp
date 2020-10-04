package com.example.coachingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TableLayout;

import com.example.coachingapp.Goals.Goal_DisplaySummary;
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

public class ViewYourRoadMapStepsHazard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    ImageButton imageIconRight;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;

    FirebaseAuth firebaseAuth;
    FirebaseDatabase database;
    DatabaseReference myRef;
    FirebaseUser user;
    LinearLayout mLayout;
    int len;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_your_road_map_steps_hazard);
        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        myRef = FirebaseDatabase.getInstance().getReference();
        database = FirebaseDatabase.getInstance();
        mLayout = findViewById(R.id.linerLayout_displaySteps_harzrd_icons);




        Query displayRoadMapQuery = myRef.child("users").child(user.getUid()).child("Goals").child("Hazard_Steps").child("hazard");
        displayRoadMapQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<String> stepsList = new ArrayList<>();

                for (DataSnapshot ds: dataSnapshot.getChildren()){
                    String s = ds.getValue(String.class);
                    stepsList.add(s);
                }


                len = stepsList.size();

                for (int i = 0; i < len; i++){
                    EditText ed = new EditText(ViewYourRoadMapStepsHazard.this);
                    ed.setText(stepsList.get(i).toString());
                    TableLayout.LayoutParams layoutParams = new TableLayout.LayoutParams();
                    ed.setBackground(getDrawable(R.drawable.text_box));
                    ed.setLayoutParams(layoutParams);
                    ed.setEnabled(false);
                    layoutParams.setMargins(0, 30, 0, 10);
                    mLayout.addView(ed);


                }

                // String displayRoadMapSteps= dataSnapshot.child("Steps").child("answers").getValue().toString();

                // displayRoadMap.setText(displayRoadMapSteps);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }



    public void btn_back(View view) {
        Intent intent = new Intent(ViewYourRoadMapStepsHazard.this, ViewYourRoadMapSteps.class);
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
