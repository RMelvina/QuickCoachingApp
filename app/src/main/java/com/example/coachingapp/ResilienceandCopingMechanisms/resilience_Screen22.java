package com.example.coachingapp.ResilienceandCopingMechanisms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.coachingapp.AboutUs;
import com.example.coachingapp.Login;
import com.example.coachingapp.R;
import com.example.coachingapp.UserDashboard;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.io.IOException;

public class resilience_Screen22 extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    ImageButton imageIconRight;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resilience__screen22);
        nav();
    }


    public void btn_play(View view) {
         //Toast.makeText(resilience_Screen22.this, "The audio file may take a while to load. Please wait for 5 seconds.", Toast.LENGTH_SHORT).show();
         mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource("https://firebasestorage.googleapis.com/v0/b/coachingapp-a95d5.appspot.com/o/guidance%20for%20image%20relaxation.m4a?alt=media&token=81f8aac5-a291-4fde-864f-48b78800d7bf");
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mp.start();
                }
            });
            mediaPlayer.prepare();
        }catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void btn_next(View view) {
        mediaPlayer.stop();

        Intent intent = new Intent(resilience_Screen22.this, resilience_Screen23.class );
        startActivity(intent);
    }
    public void btn_back(View view) {
        Intent intent = new Intent(resilience_Screen22.this, Slide_17_options.class );
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
