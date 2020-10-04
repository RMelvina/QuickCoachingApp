package com.example.coachingapp.ResilienceandCopingMechanisms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageButton;

import com.example.coachingapp.AboutUs;
import com.example.coachingapp.Login;
import com.example.coachingapp.R;
import com.example.coachingapp.UserDashboard;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Calendar;

public class resilience_Screen38 extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    ImageButton imageIconRight;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;
    CheckBox check_1,check_2,check_3,check_4,check_5,check_6,check_7,check_8,check_9,check_10,check_11;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resilience__screen38);

        check_1 = (CheckBox) findViewById(R.id.check_actionPlan_1);
        check_2 = (CheckBox) findViewById(R.id.check_actionPlan_2);
        check_3 = (CheckBox) findViewById(R.id.check_actionPlan_3);
        check_4 = (CheckBox) findViewById(R.id.check_actionPlan_4);
        check_5 = (CheckBox) findViewById(R.id.check_actionPlan_5);
        check_6 = (CheckBox) findViewById(R.id.check_actionPlan_6);
        check_7 = (CheckBox) findViewById(R.id.check_actionPlan_7);
        check_8 = (CheckBox) findViewById(R.id.check_actionPlan_8);
        check_9 = (CheckBox) findViewById(R.id.check_actionPlan_9);
        check_10 = (CheckBox) findViewById(R.id.check_actionPlan_10);
        check_11 = findViewById(R.id.check_actionPlan_11);

        nav();

    }
    public void btn_next(View view) {
        Intent intent = new Intent(resilience_Screen38.this, resilience_Screen39.class );
        startActivity(intent);
    }
    public void btn_add_calender(View view) {

        if(check_1.isChecked()) {

            openCal("Positive images");
        }
        if(check_2.isChecked()) {

            openCal("Music");

        }
        if(check_3.isChecked()) {
            openCal("Videos");
        }
        if(check_4.isChecked()) {
            openCal("Exercise");
        }
        if(check_5.isChecked()) {
            openCal("Stance (physicality)");

        }
        if(check_6.isChecked()) {
            openCal("Gratefulness & Positive thinking");
        }
        if(check_7.isChecked()) {
            openCal("Using nature");

        }
        if(check_8.isChecked()) {
            openCal("Saying no");
        }
        if(check_9.isChecked()) {

            openCal("Meditation and Mindfulnes");
        }
        if(check_10.isChecked()) {

            openCal("Purpose and meaning");
        }
        if(check_11.isChecked()) {
            openCal("Breathing and pulse rate");

        }





    }

    private void openCal(String name){
        Intent claIntent = new Intent(Intent.ACTION_INSERT);
        //claIntent.setData(CalendarContract.Events.CONTENT_URI);
        claIntent.setType("vnd.android.cursor.item/event");
        claIntent.putExtra(CalendarContract.Events.TITLE, name);
        startActivity(claIntent);
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
