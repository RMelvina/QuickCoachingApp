package com.example.coachingapp.Goals;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import com.example.coachingapp.AboutUs;
import com.example.coachingapp.Login;
import com.example.coachingapp.R;
import com.example.coachingapp.UserDashboard;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

public class Goal_Question_6 extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    ImageButton imageIconRight;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;

    TextView editText;
    LinearLayout mLayout;

    FirebaseAuth firebaseAuth;
    FirebaseDatabase database;
    DatabaseReference myRef;
    FirebaseUser user;
    EditText ed;



    int len;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goal__question_6);
        nav();



        mLayout = findViewById(R.id.steps_layout_layout);
        editText = findViewById(R.id.title_1);



        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        myRef = FirebaseDatabase.getInstance().getReference();
        database = FirebaseDatabase.getInstance();




       // Query query =myRef.child("users").child(user.getUid()).child("Goals").child("Steps").child("answers");

         Query query1 = myRef.child("users").child(user.getUid());
        Query query2 =  myRef.child("users").child(user.getUid()).child("Goals").child("Hazard_Steps").child("hazard");





//query..
//        query.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                List<String> goalsList = new ArrayList<>();
//
////                String goal = dataSnapshot.child("input_1").child("user_input_1").getValue().toString();
////                editText.setText(goal);
//
//
//                for (DataSnapshot ds: dataSnapshot.getChildren()){
//                    String s = ds.getValue(String.class);
//                    goalsList.add(s);
//                }
//
//                len = goalsList.size();
//
//                for (int i = 0; i < len; i++){
//
//                    EditText ed = new EditText(Goal_Question_6.this);
//                    ed.setText(goalsList.get(i).toString());
//                    TableLayout.LayoutParams layoutParams = new TableLayout.LayoutParams();
//                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                        ed.setBackground(getDrawable(R.drawable.text_box));
//                    }
//                    ed.setLayoutParams(layoutParams);
//                    layoutParams.setMargins(0, 30, 0, 10);
//                    mLayout.addView(ed);
//                }
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });

        query1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String s = dataSnapshot.child("Goals").child("input_1").child("user_input_1").getValue().toString();
                editText.setText(s);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        query2.addValueEventListener(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                List<String> hazardList = new ArrayList<>();

                for (DataSnapshot ds: dataSnapshot.getChildren()){
                    String s = ds.getValue(String.class);
                    hazardList.add(s);


                }

                len = hazardList.size();
                for (int i = 0; i < len; i++){
                    ed = new EditText(Goal_Question_6.this);
                    ed.setText(hazardList.get(i));
                    ed.getSelectionStart();
                    ed.setCompoundDrawablesWithIntrinsicBounds(R.drawable.mark, 0,0,0);
                    TableLayout.LayoutParams layoutParams = new TableLayout.LayoutParams();
                    ed.setBackground(getDrawable(R.drawable.text_box));
                    ed.setLayoutParams(layoutParams);


                    //goalsList.get(i).equals(hazardList.get(i)

                    layoutParams.setMargins(0, 30, 0, 10);
                    mLayout.addView(ed);
                }
//
//                if (hazardList.size() != 0){
//
//                    ed.setText(hazardList.get(i));
//                    ed.getSelectionStart();
//                    ed.setCompoundDrawablesWithIntrinsicBounds(R.drawable.mark, 0,0,0);
//
//
//
//
//
//
//
//
//                }




            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

//    public void setTimer(){
//        final Button button = (Button)findViewById(R.id.btn_next_my_rd_map_2);
//        final Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_in);
//
//
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                button.setVisibility(View.VISIBLE);
//                button.startAnimation(animation);
//
//
//            }
//        },5000);
//    }


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

    private void singOut() {
        FirebaseAuth.getInstance().signOut();
        finish();
        startActivity(new Intent(this, Login.class));
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()) {
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
        Intent intent = new Intent(Goal_Question_6.this, Goal_Question_7.class);
        startActivity(intent);
    }





}

