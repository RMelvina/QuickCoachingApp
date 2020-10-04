package com.example.coachingapp.Goals;

import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.text.style.StyleSpan;
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

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

public class Goal_14 extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    ImageButton imageIconRight;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;

    TextView paragraph_two;
    TextView paragraph_three;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goal_14);
        nav();
        validate();
        styleText();

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



    private void styleText(){

        String text_3 = paragraph_three.getText().toString();

        // Getting text from edit text
        String text = paragraph_two.getText().toString();


        //Setting text from edit text.
        SpannableStringBuilder ss = new SpannableStringBuilder(text);

        StyleSpan boldSpan = new StyleSpan(Typeface.BOLD);
        StyleSpan italicSpan = new StyleSpan(Typeface.ITALIC);

        ss.setSpan(boldSpan, 35,43, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss.setSpan(italicSpan, 72,114, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE );


        paragraph_two.setText(ss);

        // Setting image within the text view.
        Spannable spannable = new SpannableString(" On the next screen you will see your road map plan again with the hazard icons next to the steps you indicated may be a challenge.");
        Drawable android = getResources().getDrawable(R.drawable.hazardicon);
        android.setBounds(10,0,90,60);
        ImageSpan imageSpan = new ImageSpan(android, ImageSpan.ALIGN_BASELINE);
        spannable.setSpan(imageSpan, 66 ,67, spannable.SPAN_INCLUSIVE_EXCLUSIVE);



        paragraph_three.setText(spannable);


    }



    private void validate(){
        paragraph_two = findViewById(R.id.p_two);
        paragraph_three = findViewById(R.id.p_three);}

    public void btn_next_goal_14(View view) {
        Intent intent = new Intent(Goal_14.this, Goal_Question_6.class);
        startActivity(intent);
    }


}
