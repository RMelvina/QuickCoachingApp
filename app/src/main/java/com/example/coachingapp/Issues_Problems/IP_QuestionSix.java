package com.example.coachingapp.Issues_Problems;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.coachingapp.AboutUs;
import com.example.coachingapp.Login;
import com.example.coachingapp.R;
import com.example.coachingapp.UserDashboard;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

public class IP_QuestionSix extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener{
    Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn10, myButton;
    ImageButton imageIconRight;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;

    FirebaseAuth firebaseAuth;
    FirebaseDatabase database;
    DatabaseReference myRef;
    FirebaseUser user;
    private boolean isClicked = false;
    LinearLayout mLayout;
    private int buttonState = 1;


    private List<String> myList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ip__question_six);

        vaildate();
        nav();
        Isclicked();

        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        myRef = FirebaseDatabase.getInstance().getReference();
        database = FirebaseDatabase.getInstance();
        myButton = findViewById(R.id.issues_scale_btn);

        mLayout = findViewById(R.id.scale);

        myList = new ArrayList<>();


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


    private void vaildate(){


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

    private void buttonClicked(){



         if( btn8.isPressed() || btn9.isPressed() || btn10.isPressed()){

            Intent i = new Intent(IP_QuestionSix.this, IP_Question_Five_Part_Two.class);
            startActivity(i);
            addToFirebase();
            myButton.setVisibility(View.VISIBLE);

        }else {
            Intent intent = new Intent(IP_QuestionSix.this, IP_QuestionSeven.class);
            startActivity(intent);
            addToFirebase();
            myButton.setVisibility(View.VISIBLE);




        }

//        if ( btn7.isPressed() || btn9.isPressed() || btn10.isPressed()){
//
//            Intent i = new Intent(IP_QuestionSix.this, IP_Question_Five.class);
//            startActivity(i);
//            addToFirebase();
//            //Toast.makeText(this, "Information saved 1", Toast.LENGTH_LONG).show();
//
//
//        }else  {
//            Intent intent = new Intent(IP_QuestionSix.this, IP_QuestionSeven.class);
//            startActivity(intent);
//            addToFirebase();
//            //Toast.makeText(this, "Information saved 2", Toast.LENGTH_LONG).show();
//
//        }

    }




    private void addToFirebase() {

        String txt1 = btn1.getText().toString().trim();
        String txt2 = btn2.getText().toString().trim();
        String txt3 = btn3.getText().toString().trim();
        String txt4 = btn4.getText().toString().trim();
        String txt5 = btn5.getText().toString().trim();
        String txt6 = btn6.getText().toString().trim();
        String txt7 = btn7.getText().toString().trim();
        String txt8 = btn8.getText().toString().trim();
        String txt9 = btn9.getText().toString().trim();
        String txt10 = btn10.getText().toString().trim();

//        myList.add(txt1);
//        myList.add(txt2);
//        myList.add(txt3);
//        myList.add(txt4);
//        myList.add(txt5);
//        myList.add(txt6);
//        myList.add(txt7);
//        myList.add(txt8);
//        myList.add(txt9);
//        myList.add(txt10);


//









        if (user != null) {


//            Issues_4 issues_4 = new Issues_4();
            String id = user.getUid();

           if (btn1.isPressed()){
               myRef.child("users").child(id).child("Issues").child("answer_4").child("answer_4").setValue("1");

            }else if (btn2.isPressed()){
               myRef.child("users").child(id).child("Issues").child("answer_4").child("answer_4").setValue("2");
           }else if(btn3.isPressed()){
               myRef.child("users").child(id).child("Issues").child("answer_4").child("answer_4").setValue("3");
           }else if (btn4.isPressed()){
               myRef.child("users").child(id).child("Issues").child("answer_4").child("answer_4").setValue("4");
           }else if (btn5.isPressed()){
               myRef.child("users").child(id).child("Issues").child("answer_4").child("answer_4").setValue("5");
           }else if (btn6.isPressed()){
               myRef.child("users").child(id).child("Issues").child("answer_4").child("answer_4").setValue("6");
           }else if (btn7.isPressed()){
               myRef.child("users").child(id).child("Issues").child("answer_4").child("answer_4").setValue("7");
           }else if (btn8.isPressed()){
               myRef.child("users").child(id).child("Issues").child("answer_4").child("answer_4").setValue("8");
           }else if (btn9.isPressed()){
               myRef.child("users").child(id).child("Issues").child("answer_4").child("answer_4").setValue("9");
           }else if (btn10.isPressed()){
               myRef.child("users").child(id).child("Issues").child("answer_4").child("answer_4").setValue("10");
           }
            Toast.makeText(this, "Information Saved", Toast.LENGTH_SHORT).show();


//            String id = user.getUid();
//            myRef.child("users").child(id).child("Issues").child("answer_4").setValue(issues_4);
//            Toast.makeText(this, "Information Saved", Toast.LENGTH_SHORT).show();
//            Intent intent = new Intent(IssuesAndProblemsPart7.this, IssuesAndProblemsPart8.class);
//            startActivity(intent);
        }
    }

    public void btn_next_scale_issuse(View view) {

        if(btn1.isPressed() || btn2.isPressed() || btn3.isPressed() || btn4.isPressed() || btn5.isPressed()|| btn6.isPressed() ||
                btn7.isPressed() || btn8.isPressed() || btn9.isPressed() || btn10.isPressed()){
          // myButton.setVisibility(View.VISIBLE );

            buttonClicked();

        }else {
            Toast.makeText(IP_QuestionSix.this, "Please make a selection", Toast.LENGTH_LONG).show();

        }



    }


}
