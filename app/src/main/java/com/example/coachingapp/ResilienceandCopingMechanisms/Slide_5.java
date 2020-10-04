package com.example.coachingapp.ResilienceandCopingMechanisms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.transition.Slide;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Switch;

import com.example.coachingapp.AboutUs;
import com.example.coachingapp.Login;
import com.example.coachingapp.R;
import com.example.coachingapp.UserDashboard;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class Slide_5 extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    AlertDialog.Builder alertDialog;

    ConstraintLayout cCd1, cCd2, cCd3;
    ImageButton imageIconRight;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide_5);
        cCd1 = findViewById(R.id.constraintLayout11);
        cCd2 = findViewById(R.id.constraintLayout12);
        cCd3 = findViewById(R.id.constraintLayout13);
        nav();






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

    public void btn_next(View view) {
        Intent intent = new Intent(Slide_5.this, Slide_6.class);
        startActivity(intent);
    }

    public void btnrecognise(View view) {


        arletDialogBox_1();

    }

    private void arletDialogBox_1(){
       alertDialog = new AlertDialog.Builder(Slide_5.this);
        alertDialog.setTitle("Are you sure?");
        alertDialog.setMessage("Are you sure you would like to jump ahead to this section");
        alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {


                    Intent intent = new Intent(Slide_5.this, Slide_11.class);
                    startActivity(intent);


//                if (alertDialog.equals(cCd1)){  android:clickable="true"
//                    Intent intent = new Intent(Slide_5.this, Slide_11.class);
//                    startActivity(intent);
//                }else if (alertDialog.equals(cCd2)){
//                    Intent intent = new Intent(Slide_5.this, resilience_Screen39.class);
//                    startActivity(intent);
//                }else if (alertDialog.equals(cCd3)){
//                    Intent intent = new Intent(Slide_5.this, Slide_14.class);
//                    startActivity(intent);
//
//                }



            }
        });
       alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
           @Override
           public void onClick(DialogInterface dialog, int which) {
               dialog.dismiss();

           }
       });


       alertDialog.create().show();



    }

    private void arletDialogBox_2(){
        alertDialog = new AlertDialog.Builder(Slide_5.this);
        alertDialog.setTitle("Are you sure?");
        alertDialog.setMessage("Are you sure you would like to jump ahead to this section");
        alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

//                Intent intent = new Intent(Slide_5.this, Slide_11.class);
//                startActivity(intent);



//
                    Intent intent = new Intent(Slide_5.this, Slide_14.class);
                    startActivity(intent);

//                }



            }
        });
        alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();

            }
        });


        alertDialog.create().show();



    }

    private void arletDialogBox_3(){
        alertDialog = new AlertDialog.Builder(Slide_5.this);
        alertDialog.setTitle("Are you sure?");
        alertDialog.setMessage("Are you sure you would like to jump ahead to this section");
        alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

//
                    Intent intent = new Intent(Slide_5.this, resilience_Screen39.class);
                    startActivity(intent);
//
//                }



            }
        });
        alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();

            }
        });


        alertDialog.create().show();



    }










    public void btnresolve(View view) {
        arletDialogBox_3();

    }

    public void btnrespond(View view) {
        arletDialogBox_2();

    }
}

