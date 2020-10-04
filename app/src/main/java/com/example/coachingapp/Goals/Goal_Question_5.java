package com.example.coachingapp.Goals;

import android.content.ClipData;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.coachingapp.AboutUs;
import com.example.coachingapp.Login;
import com.example.coachingapp.Models.Goals_Road_Map_Identify;
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

public class Goal_Question_5 extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    ImageButton imageIconRight;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;

    FirebaseAuth firebaseAuth;
    FirebaseDatabase database;
    DatabaseReference myRef;
    FirebaseUser user;


    EditText  ed_ed;
    TextView editText;
    LinearLayout mLayout;
    ScrollView goalDisplayParentView, goalDisplayChildView;

    ImageView i1, i2, i3, i4;
    List<EditText> myList = new ArrayList<EditText>();
    List<String> etText = new ArrayList<String>();


    List<EditText> myLists = new ArrayList<EditText>();
    List<EditText> choosingLists = new ArrayList<EditText>();


    int len;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goal__question_5);
        nav();

       // etText = new ArrayList<>();
        mLayout = findViewById(R.id.steps_layout_display_steps);
        editText = findViewById(R.id.display_goal_step_txt_box);





        i1 = (ImageView) findViewById(R.id.i1);
        i1.setImageResource(R.drawable.mark);
        i2 = (ImageView) findViewById(R.id.i2) ;
        i2.setImageResource(R.drawable.mark);
        i3 = (ImageView) findViewById(R.id.i3);
        i3.setImageResource(R.drawable.mark);
        i4 = (ImageView) findViewById(R.id.i4) ;
        i4.setImageResource(R.drawable.mark);

        i1.setBackgroundResource(R.drawable.hazardicon);

        i1.setOnLongClickListener(longClickListener);


        i2.setOnLongClickListener(longClickListener);
        i3.setOnLongClickListener(longClickListener);
        i4.setOnLongClickListener(longClickListener);
        goalDisplayParentView = findViewById(R.id.goalDisplayParentView);
        goalDisplayChildView = findViewById(R.id.goalDisplayChildView);








        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        myRef = FirebaseDatabase.getInstance().getReference();
        database = FirebaseDatabase.getInstance();

        goalDisplayParentView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                findViewById(R.id.goalDisplayParentView).getParent().requestDisallowInterceptTouchEvent(false);
                return false;
            }
        });


        goalDisplayChildView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                v.getParent().requestDisallowInterceptTouchEvent(true);

                return false;
            }
        });


//        displayText();


        //Query query = myRef.child("users").child(user.getUid());

//        query.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//
//                String day = dataSnapshot.child("Goals").child("input_1").child("user_input_1").getValue().toString();
//                editText.setText(day);
//
//
//
//
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });


        Query query =myRef.child("users").child(user.getUid()).child("Goals").child("Steps").child("answers");

        Query query1 = myRef.child("users").child(user.getUid());

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<String> goalsList = new ArrayList<>();


                for (DataSnapshot ds: dataSnapshot.getChildren()){
                    String s = ds.getValue(String.class);
                    goalsList.add(s);
                }

                len = goalsList.size();

                for (int i = 0; i < len; i++){

                    final EditText ed = new EditText(Goal_Question_5.this);
                    ed.setText(goalsList.get(i).toString());
                    TableLayout.LayoutParams layoutParams = new TableLayout.LayoutParams();
                    ed.setBackground(getDrawable(R.drawable.text_box));
                    ed.setLayoutParams(layoutParams);
                    myLists.add(ed);
                    ed.setOnDragListener(new View.OnDragListener() {
                        @Override
                        public boolean onDrag(View v, DragEvent event) {
                            int dragEvent = event.getAction();
                            final View view = (View)event.getLocalState();
                            switch (dragEvent){
                                case DragEvent.ACTION_DRAG_ENTERED:
                                    break;
                                case DragEvent.ACTION_DRAG_EXITED:
                                    break;
                                case DragEvent.ACTION_DROP:
                                    if (view.getId() == R.id.i1){
                                        // adding list to upload
                                        choosingLists.add(ed);
                                        ed.getSelectionStart();
                                        ed.setCompoundDrawablesWithIntrinsicBounds(R.drawable.mark, 0,0,0);
                                    }else if(view.getId() == R.id.i2){
                                        // adding list to upload
                                        choosingLists.add(ed);
                                        ed.getSelectionStart();
                                        ed.setCompoundDrawablesWithIntrinsicBounds(R.drawable.mark, 0,0,0);
                                    }else if(view.getId() == R.id.i3){
                                        // adding list to upload
                                        choosingLists.add(ed);
                                        ed.getSelectionStart();
                                        ed.setCompoundDrawablesWithIntrinsicBounds(R.drawable.mark, 0,0,0);
                                    }else if(view.getId() == R.id.i4){
                                        // adding list to upload
                                        choosingLists.add(ed);
                                        ed.getSelectionStart();
                                        ed.setCompoundDrawablesWithIntrinsicBounds(R.drawable.mark, 0,0,0);
                                    }

                                    view.animate()
                                            .x(ed.getX())
                                            .y(ed.getY())
                                            .setDuration(700)
                                            .start();

                                    break;

                            }
                            return true;

                        }
                    });
                    layoutParams.setMargins(0, 30, 0, 10);
                    mLayout.addView(ed);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

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



    }

    View.OnLongClickListener longClickListener = new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {

            ClipData clipData = ClipData.newPlainText("","");
            View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                v.startDragAndDrop(clipData, shadowBuilder, v, 0);
            }

            return true;
        }
    };






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

        if(choosingLists.size() != 0) {
            addToFirebase(choosingLists);
            Intent intent = new Intent(Goal_Question_5.this, Goal_14.class);
            startActivity(intent);


        }else {
            Intent intent = new Intent(Goal_Question_5.this, Goal_16.class);
            startActivity(intent);

        }
//        Intent intent = new Intent(Goal_Question_5.this, Goal_14.class);
//        startActivity(intent);

    }






    private void addToFirebase(List<EditText> list) {




        if (user != null) {



            for (EditText et : list) {

                String setText = et.getText().toString();
                etText.add(setText);

                Goals_Road_Map_Identify issues = new Goals_Road_Map_Identify(etText);
                String id = user.getUid();
                myRef.child("users").child(id).child("Goals").child("Hazard_Steps").setValue(issues);
                Toast.makeText(this,"Information saved", Toast.LENGTH_LONG).show();



                //  myRef.child("users").child(id).child("Goals").child("more").setValue(issues);

                }



        }
    }









}
