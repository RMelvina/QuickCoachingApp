package com.example.coachingapp.Goals;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TextView;

import com.example.coachingapp.AboutUs;
import com.example.coachingapp.Login;
import com.example.coachingapp.Models.Goals_Road_Map;
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
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

public class Goal_Question_4 extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    ImageButton imageIconRight;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;

    private boolean isClicked = false;

    EditText ed, ed1, ed2, ed3, ed4, ed5;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase database;
    DatabaseReference myRef;
    FirebaseUser user;
    Button mButton;
    TextView editText;
    ScrollView goalParentSV_1, goalChildSV_1;

    LinearLayout mLayout;
    List<EditText> myList = new ArrayList<EditText>();
    List<String> etText = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goal__question_4);
       // etText = new ArrayList<>();

        editText = findViewById(R.id.edit_text_box_1_display);
        goalParentSV_1 = findViewById(R.id.goalParentSV_1);
        goalChildSV_1 = findViewById(R.id.goalChildSV_1);



        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        myRef = FirebaseDatabase.getInstance().getReference();
        database = FirebaseDatabase.getInstance();

//

        nav();

        validation();



        Query query = myRef.child("users").child(user.getUid());
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String day = dataSnapshot.child("Goals").child("input_1").child("user_input_1").getValue().toString();
                editText.setText(day);





            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        goalParentSV_1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                findViewById(R.id.goalParentSV_1).getParent().requestDisallowInterceptTouchEvent(false);
                return false;
            }
        });


        goalChildSV_1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                v.getParent().requestDisallowInterceptTouchEvent(true);

                return false;
            }
        });
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




    private void validation() {
        mLayout = findViewById(R.id.steps_layout);
        mButton = findViewById(R.id.btn_next_my_rd_map_1);




    }


    private void addToFirebase() {



        if (user != null) {


            for (EditText et : myList) {
                String setText = et.getText().toString();
                etText.add(setText);


                Goals_Road_Map issues = new Goals_Road_Map( etText);
                String id = user.getUid();
                myRef.child("users").child(id).child("Goals").child("Steps").setValue(issues);


                //  myRef.child("users").child(id).child("Goals").child("more").setValue(issues);
            }


        }
    }





    private void dialogBoxHelp() {

        final AlertDialog.Builder alert = new AlertDialog.Builder(Goal_Question_4.this);
        alert.setTitle("Help");
        alert.setCancelable(true);
        View mView = getLayoutInflater().inflate(R.layout.custom_box_1, null);
        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });


        alert.setView(mView);


        final AlertDialog alertDialog = alert.create();
        alertDialog.show();


    }

        private TextView createNewTextView(){

            ed = new EditText(Goal_Question_4.this);
            TableLayout.LayoutParams layoutParams = new TableLayout.LayoutParams();
            ed.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
            mLayout.addView( ed);

            myList.add(ed);


            layoutParams.setMargins(0, 30, 0, 10);
           // ed.setBackground(getDrawable(R.drawable.text_box));
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                ed.setBackground(getDrawable(R.drawable.text_box));
            }

            ed.setLayoutParams(layoutParams);
            return  ed;

        }

    public void btn_add(View view) {
        createNewTextView();
    }

    public void btn_next_next(View view) {

        if(!isClicked){
            dialogBoxHelp();

        }else {


            addToFirebase();
            Intent intent = new Intent(Goal_Question_4.this, Goal_13.class);
//            intent.putExtra("Goals", (Serializable) etText);
            startActivity(intent);
        }

        isClicked = !isClicked;



    }




}


