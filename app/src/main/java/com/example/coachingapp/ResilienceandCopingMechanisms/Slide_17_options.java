package com.example.coachingapp.ResilienceandCopingMechanisms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.example.coachingapp.AboutUs;
import com.example.coachingapp.Login;
import com.example.coachingapp.R;
import com.example.coachingapp.UserDashboard;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class Slide_17_options extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    ImageButton imageIconRight;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide_17_options);
    }
    public void btn_Breathing_and_pulse_rate(View view) {

        Intent intent = new Intent(Slide_17_options.this, Slide_18.class );
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

    public void btn_Positive_images(View view) {
        Intent intent = new Intent(Slide_17_options.this, resilience_Screen20.class );
        startActivity(intent);
    }

    public void btn_Music(View view) {
        Intent intent = new Intent(Slide_17_options.this, resilience_Screen22.class );
        startActivity(intent);
    }

    public void btn_Videos(View view) {
        Intent intent = new Intent(Slide_17_options.this, resilience_Screen22.class );
        startActivity(intent);
    }


    public void btn_Exercise(View view) {
        Intent intent = new Intent(Slide_17_options.this, resilience_Screen26.class );
        startActivity(intent);
    }



    public void btn_Stance(View view) {
        Intent intent = new Intent(Slide_17_options.this, resilience_Screen27.class );
        startActivity(intent);
    }



    public void btn_Gratefulness(View view) {
        Intent intent = new Intent(Slide_17_options.this, resilience_Screen28.class );
        startActivity(intent);
    }


    public void btn_Using_nature(View view) {
        Intent intent = new Intent(Slide_17_options.this, resilience_Screen30.class );
        startActivity(intent);
    }

    public void btn_Saying_no(View view) {
        Intent intent = new Intent(Slide_17_options.this, resilience_Screen31.class );
        startActivity(intent);
    }

    public void btn_Meditation_and_Mindfulness(View view) {
        Intent intent = new Intent(Slide_17_options.this, resilience_Screen34.class );
        startActivity(intent);
    }

    public void btn_Purpose_and_meaning(View view) {
        Intent intent = new Intent(Slide_17_options.this, resilience_Screen35.class );
        startActivity(intent);
    }

    public void btn_next(View view) {
        Intent intent = new Intent(Slide_17_options.this, Slide_18.class );
        startActivity(intent);
    }
}
