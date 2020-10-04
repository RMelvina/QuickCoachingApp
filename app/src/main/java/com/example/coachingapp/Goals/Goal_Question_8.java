package com.example.coachingapp.Goals;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.coachingapp.AboutUs;
import com.example.coachingapp.Issues_Problems.LastQuestion;
import com.example.coachingapp.Login;
import com.example.coachingapp.R;
import com.example.coachingapp.UserDashboard;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

public class Goal_Question_8 extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    ImageButton imageIconRight;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;

    TextView txtOne, txtTwo;

    private int buttonState = 1;

    long lastDown;
    long lastDuration;

    Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn10, myButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goal__question_8);

        btn1 = findViewById(R.id.scale_btn_1);
        btn2 = findViewById(R.id.scale_btn_2);
        btn3 = findViewById(R.id.scale_btn_3);
        btn4 = findViewById(R.id.scale_btn_4);
        btn5 = findViewById(R.id.scale_btn_5);
        btn6 = findViewById(R.id.scale_btn_6);
        btn7 = findViewById(R.id.scale_btn_7);
        btn8 = findViewById(R.id.scale_btn_8);
        btn9 = findViewById(R.id.scale_btn_9);
        btn10 = findViewById(R.id.scale_btn_10);

        Isclicked();

        //btnBtn();
        vaildate();
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






    private boolean Isclicked() {
        btn1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    btn1.setBackgroundColor(Color.parseColor("#9E9E9E"));
                    btn1.setPressed(true);

                    btn2.setBackgroundColor(Color.parseColor("#FAFAFA"));
                    btn3.setBackgroundColor(Color.parseColor("#FAFAFA"));
                    btn4.setBackgroundColor(Color.parseColor("#FAFAFA"));
                    btn5.setBackgroundColor(Color.parseColor("#FAFAFA"));
                    btn6.setBackgroundColor(Color.parseColor("#FAFAFA"));
                    btn7.setBackgroundColor(Color.parseColor("#FAFAFA"));
                    btn8.setBackgroundColor(Color.parseColor("#FAFAFA"));
                    btn9.setBackgroundColor(Color.parseColor("#FAFAFA"));
                    btn10.setBackgroundColor(Color.parseColor("#FAFAFA"));




                } else if (event.getAction() == MotionEvent.ACTION_UP) {


                }

                return true;
            }

        });

        btn2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    btn2.setBackgroundColor(Color.parseColor("#9E9E9E"));
                    btn2.setPressed(true);

                    btn1.setBackgroundColor(Color.parseColor("#FAFAFA"));
                    btn3.setBackgroundColor(Color.parseColor("#FAFAFA"));
                    btn4.setBackgroundColor(Color.parseColor("#FAFAFA"));
                    btn5.setBackgroundColor(Color.parseColor("#FAFAFA"));
                    btn6.setBackgroundColor(Color.parseColor("#FAFAFA"));
                    btn7.setBackgroundColor(Color.parseColor("#FAFAFA"));
                    btn8.setBackgroundColor(Color.parseColor("#FAFAFA"));
                    btn9.setBackgroundColor(Color.parseColor("#FAFAFA"));
                    btn10.setBackgroundColor(Color.parseColor("#FAFAFA"));




                } else if (event.getAction() == MotionEvent.ACTION_UP) {


                }

                return true;
            }

        });


        btn3.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    btn3.setBackgroundColor(Color.parseColor("#9E9E9E"));
                    btn3.setPressed(true);

                    btn2.setBackgroundColor(Color.parseColor("#FAFAFA"));
                    btn1.setBackgroundColor(Color.parseColor("#FAFAFA"));
                    btn4.setBackgroundColor(Color.parseColor("#FAFAFA"));
                    btn5.setBackgroundColor(Color.parseColor("#FAFAFA"));
                    btn6.setBackgroundColor(Color.parseColor("#FAFAFA"));
                    btn7.setBackgroundColor(Color.parseColor("#FAFAFA"));
                    btn8.setBackgroundColor(Color.parseColor("#FAFAFA"));
                    btn9.setBackgroundColor(Color.parseColor("#FAFAFA"));
                    btn10.setBackgroundColor(Color.parseColor("#FAFAFA"));




                } else if (event.getAction() == MotionEvent.ACTION_UP) {


                }

                return true;
            }

        });

        btn4.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    btn4.setBackgroundColor(Color.parseColor("#9E9E9E"));
                    btn4.setPressed(true);

                    btn2.setBackgroundColor(Color.parseColor("#FAFAFA"));
                    btn3.setBackgroundColor(Color.parseColor("#FAFAFA"));
                    btn1.setBackgroundColor(Color.parseColor("#FAFAFA"));
                    btn5.setBackgroundColor(Color.parseColor("#FAFAFA"));
                    btn6.setBackgroundColor(Color.parseColor("#FAFAFA"));
                    btn7.setBackgroundColor(Color.parseColor("#FAFAFA"));
                    btn8.setBackgroundColor(Color.parseColor("#FAFAFA"));
                    btn9.setBackgroundColor(Color.parseColor("#FAFAFA"));
                    btn10.setBackgroundColor(Color.parseColor("#FAFAFA"));



                } else if (event.getAction() == MotionEvent.ACTION_UP) {


                }

                return true;
            }

        });

        btn5.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    btn5.setBackgroundColor(Color.parseColor("#9E9E9E"));
                    btn5.setPressed(true);

                    btn2.setBackgroundColor(Color.parseColor("#FAFAFA"));
                    btn3.setBackgroundColor(Color.parseColor("#FAFAFA"));
                    btn4.setBackgroundColor(Color.parseColor("#FAFAFA"));
                    btn1.setBackgroundColor(Color.parseColor("#FAFAFA"));
                    btn6.setBackgroundColor(Color.parseColor("#FAFAFA"));
                    btn7.setBackgroundColor(Color.parseColor("#FAFAFA"));
                    btn8.setBackgroundColor(Color.parseColor("#FAFAFA"));
                    btn9.setBackgroundColor(Color.parseColor("#FAFAFA"));
                    btn10.setBackgroundColor(Color.parseColor("#FAFAFA"));




                } else if (event.getAction() == MotionEvent.ACTION_UP) {


                }

                return true;
            }

        });

        btn6.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    btn6.setBackgroundColor(Color.parseColor("#9E9E9E"));
                    btn6.setPressed(true);

                    btn2.setBackgroundColor(Color.parseColor("#FAFAFA"));
                    btn3.setBackgroundColor(Color.parseColor("#FAFAFA"));
                    btn4.setBackgroundColor(Color.parseColor("#FAFAFA"));
                    btn5.setBackgroundColor(Color.parseColor("#FAFAFA"));
                    btn1.setBackgroundColor(Color.parseColor("#FAFAFA"));
                    btn7.setBackgroundColor(Color.parseColor("#FAFAFA"));
                    btn8.setBackgroundColor(Color.parseColor("#FAFAFA"));
                    btn9.setBackgroundColor(Color.parseColor("#FAFAFA"));
                    btn10.setBackgroundColor(Color.parseColor("#FAFAFA"));




                } else if (event.getAction() == MotionEvent.ACTION_UP) {


                }

                return true;
            }

        });

        btn7.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    btn7.setBackgroundColor(Color.parseColor("#9E9E9E"));
                    btn7.setPressed(true);

                    btn2.setBackgroundColor(Color.parseColor("#FAFAFA"));
                    btn3.setBackgroundColor(Color.parseColor("#FAFAFA"));
                    btn4.setBackgroundColor(Color.parseColor("#FAFAFA"));
                    btn5.setBackgroundColor(Color.parseColor("#FAFAFA"));
                    btn6.setBackgroundColor(Color.parseColor("#FAFAFA"));
                    btn1.setBackgroundColor(Color.parseColor("#FAFAFA"));
                    btn8.setBackgroundColor(Color.parseColor("#FAFAFA"));
                    btn9.setBackgroundColor(Color.parseColor("#FAFAFA"));
                    btn10.setBackgroundColor(Color.parseColor("#FAFAFA"));




                } else if (event.getAction() == MotionEvent.ACTION_UP) {


                }

                return true;
            }

        });

        btn8.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    btn8.setBackgroundColor(Color.parseColor("#9E9E9E"));
                    btn8.setPressed(true);

                    btn2.setBackgroundColor(Color.parseColor("#FAFAFA"));
                    btn3.setBackgroundColor(Color.parseColor("#FAFAFA"));
                    btn4.setBackgroundColor(Color.parseColor("#FAFAFA"));
                    btn5.setBackgroundColor(Color.parseColor("#FAFAFA"));
                    btn6.setBackgroundColor(Color.parseColor("#FAFAFA"));
                    btn7.setBackgroundColor(Color.parseColor("#FAFAFA"));
                    btn1.setBackgroundColor(Color.parseColor("#FAFAFA"));
                    btn9.setBackgroundColor(Color.parseColor("#FAFAFA"));
                    btn10.setBackgroundColor(Color.parseColor("#FAFAFA"));




                } else if (event.getAction() == MotionEvent.ACTION_UP) {


                }

                return true;
            }

        });

        btn9.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    btn9.setBackgroundColor(Color.parseColor("#9E9E9E"));
                    btn9.setPressed(true);

                    btn2.setBackgroundColor(Color.parseColor("#FAFAFA"));
                    btn3.setBackgroundColor(Color.parseColor("#FAFAFA"));
                    btn4.setBackgroundColor(Color.parseColor("#FAFAFA"));
                    btn5.setBackgroundColor(Color.parseColor("#FAFAFA"));
                    btn6.setBackgroundColor(Color.parseColor("#FAFAFA"));
                    btn7.setBackgroundColor(Color.parseColor("#FAFAFA"));
                    btn8.setBackgroundColor(Color.parseColor("#FAFAFA"));
                    btn1.setBackgroundColor(Color.parseColor("#FAFAFA"));
                    btn10.setBackgroundColor(Color.parseColor("#FAFAFA"));



                } else if (event.getAction() == MotionEvent.ACTION_UP) {


                }

                return true;
            }

        });

        btn10.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    btn10.setBackgroundColor(Color.parseColor("#9E9E9E"));
                    btn10.setPressed(true);

                    btn2.setBackgroundColor(Color.parseColor("#FAFAFA"));
                    btn3.setBackgroundColor(Color.parseColor("#FAFAFA"));
                    btn4.setBackgroundColor(Color.parseColor("#FAFAFA"));
                    btn5.setBackgroundColor(Color.parseColor("#FAFAFA"));
                    btn6.setBackgroundColor(Color.parseColor("#FAFAFA"));
                    btn7.setBackgroundColor(Color.parseColor("#FAFAFA"));
                    btn8.setBackgroundColor(Color.parseColor("#FAFAFA"));
                    btn9.setBackgroundColor(Color.parseColor("#FAFAFA"));
                    btn1.setBackgroundColor(Color.parseColor("#FAFAFA"));




                } else if (event.getAction() == MotionEvent.ACTION_UP) {


                }

                return true;
            }

        });





        return true;
    }



    private void vaildate(){
        txtOne = findViewById(R.id.unlikely);
        txtTwo = findViewById(R.id.likely);

        myButton = findViewById(R.id.btn_next_for_scale);


    }



    public void btn_scale_next(View view) {



            buttonClicked();



    }


    private void buttonClicked(){
        if (btn1.isPressed() || btn2.isPressed() || btn3.isPressed() || btn4.isPressed() ||
                btn5.isPressed() || btn6.isPressed() || btn7.isPressed()){


            Intent i = new Intent(Goal_Question_8.this, Goal_Question_8_Part_1.class);
            startActivity(i);


        }else if (btn8.isPressed() || btn9.isPressed() || btn10.isPressed()) {
            Intent intent = new Intent(Goal_Question_8.this, LastQuestion.class);
            startActivity(intent);
            myButton.isEnabled();

        }else {
            Toast.makeText(Goal_Question_8.this, "Please make a selection first ", Toast.LENGTH_LONG);

        }


    }



    }
