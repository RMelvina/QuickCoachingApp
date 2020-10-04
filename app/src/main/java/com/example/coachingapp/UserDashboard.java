package com.example.coachingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.coachingapp.Goals.Goal_DisplaySummary;
import com.example.coachingapp.Goals.Goal_Question_4;
import com.example.coachingapp.ResilienceandCopingMechanisms.ResilienceMain;
import com.example.coachingapp.ResilienceandCopingMechanisms.Slide_12_Behavioural;
import com.example.coachingapp.ResilienceandCopingMechanisms.Slide_13;
import com.example.coachingapp.ResilienceandCopingMechanisms.resilience_Screen24;
import com.example.coachingapp.ResilienceandCopingMechanisms.resilience_Screen25;
import com.example.coachingapp.ResilienceandCopingMechanisms.resilience_Screen38;
import com.example.coachingapp.ResilienceandCopingMechanisms.resilience_Screen51;
import com.example.coachingapp.WebBlog.DashboardBlog;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class UserDashboard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener  {


    ImageButton imageIconRight;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;

    Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_dashboard);


        // Setting toolbar as action bar

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

       // button = findViewById(R.id.btn_begin_Quick_Coaching);

//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(UserDashboard.this, QuickCoachingOptions.class);
//                startActivity(intent);
//            }
//        });

        nav();

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

    public void quick_coaching(View view) {
        Intent intent = new Intent(UserDashboard.this, AboutUs.class);
        startActivity(intent);
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

    public void btn_blog(View view) {
        Intent intent = new Intent(UserDashboard.this, DashboardBlog.class);
        startActivity(intent);
    }
//QuickCoachingOptions
    public void btn_btn_btn(View view) {
        Intent intent = new Intent(UserDashboard.this, QuickCoachingOptions.class);
        startActivity(intent);
    }

    public void btnResilience(View view) {
//ResilienceMain, Slide_12_Behavioural  resilience_Screen25
        Intent intent = new Intent(UserDashboard.this, ResilienceMain.class);
        startActivity(intent);
    }
}
