package com.example.coachingapp.ResilienceandCopingMechanisms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.coachingapp.AboutUs;
import com.example.coachingapp.Login;
import com.example.coachingapp.R;
import com.example.coachingapp.UserDashboard;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class Slide_17 extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    ImageButton imageIconRight;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide_17);

        nav();

        TextView textView = (TextView)findViewById(R.id.color_text);
        String text = "One of the next screen are ways to <font color = #E53935>manage stress</font> - short term.";
        textView.setText(Html.fromHtml(text));

        TextView textView_2 = (TextView)findViewById(R.id.color_text_2);
        String text2 = "All of  these techniques teach you to <font color = #E53935>change the chemical responses in your brain and body</font> and slow the release of the stress chemicals.";
        textView_2.setText(Html.fromHtml(text2));

        TextView textView_3 = (TextView)findViewById(R.id.color_text_3);
        String text3 = "Stress chemicals = <font color = #E53935>adrenaline and cortisol.</font>";
        textView_3.setText(Html.fromHtml(text3));

        TextView textView_4 = (TextView)findViewById(R.id.color_text_4);
        String text4 = "Feel good chemicals = <font color = #FAFAFA>dopamine, oxytocin, serotonin.</font>";
        textView_4.setText(Html.fromHtml(text4));
    }

    public void btn_next(View view) {
        Intent intent = new Intent(Slide_17.this, Slide_17_options.class );
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
