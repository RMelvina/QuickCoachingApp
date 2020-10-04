package com.example.coachingapp.ResilienceandCopingMechanisms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.coachingapp.AboutUs;
import com.example.coachingapp.Issues_Problems.IP_Question_10;
import com.example.coachingapp.Issues_Problems.IP_Question_13;
import com.example.coachingapp.Login;
import com.example.coachingapp.Models.Behavioural;
import com.example.coachingapp.Models.Issues_13;
import com.example.coachingapp.R;
import com.example.coachingapp.UserDashboard;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Slide_12_Behavioural extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    ImageButton imageIconRight;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;

    FirebaseAuth firebaseAuth;
    FirebaseDatabase database;
    DatabaseReference myRef;
    FirebaseUser user;

    LinearLayout mLayout;

    CheckBox check_0,check_1,check_2,check_4,check_5,check_6,check_7,check_8,check_9,check_10,check_11;
    List<String> options = new ArrayList<>();
    List<CheckBox> checkBoxes = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide_12__behavioural);

        mLayout = findViewById(R.id.steps_layouts);
        nav();

        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        myRef = FirebaseDatabase.getInstance().getReference();
        database = FirebaseDatabase.getInstance();

        check_0 = findViewById(R.id.check_0);
        check_1 = (CheckBox) findViewById(R.id.check_1);
        check_2 = (CheckBox) findViewById(R.id.check_2);

        check_4 = (CheckBox) findViewById(R.id.check_4);
        check_5 = (CheckBox) findViewById(R.id.check_5);
        check_6 = (CheckBox) findViewById(R.id.check_6);
        check_7 = (CheckBox) findViewById(R.id.check_7);
        check_8 = (CheckBox) findViewById(R.id.check_8);
        check_9 = (CheckBox) findViewById(R.id.check_9);
        check_10 = (CheckBox) findViewById(R.id.check_10);
        check_11= (CheckBox) findViewById(R.id.check_11);

        checkBoxes.add(check_0);
        checkBoxes.add(check_1);
        checkBoxes.add(check_2);

        checkBoxes.add(check_4);
        checkBoxes.add(check_5);
        checkBoxes.add(check_6);
        checkBoxes.add(check_7);
        checkBoxes.add(check_8);
        checkBoxes.add(check_9);
        checkBoxes.add(check_10);
        checkBoxes.add(check_11);







    }


    public void b_btn_next(View view) {
        addToFirebase();
    }



    private void addToFirebase() {

        if (user != null) {


            for (CheckBox checkBox: checkBoxes)
            {
                if (checkBox.isChecked()){
                    String text = checkBox.getText().toString();
                    options.add(text);
                }


                Behavioural behavioural = new Behavioural(options);
                String id = user.getUid();
                myRef.child("users").child(id).child("Resilience").child("behavioural").setValue(behavioural);
                Intent intent = new Intent(Slide_12_Behavioural.this, Slide_12_Physical.class);
                startActivity(intent);

            }



            }

        }


//


//






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

