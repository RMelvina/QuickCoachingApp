package com.example.coachingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import com.example.coachingapp.ResilienceandCopingMechanisms.resilience_Screen51;
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

public class ViewStressSigns extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    FirebaseDatabase database;
    DatabaseReference myRef;
    FirebaseUser user;
    LinearLayout mLayout;
    List<String> signsList;
    int len;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_stress_signs);

        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        myRef = FirebaseDatabase.getInstance().getReference();
        database = FirebaseDatabase.getInstance();
        mLayout = findViewById(R.id.display_stress_signs);
        firebaseAuth = FirebaseAuth.getInstance();



        Query displayStressSigns = myRef.child("users").child(user.getUid()).child("Resilience").child("behavioural").child("choices");
        displayStressSigns.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                signsList = new ArrayList<>();

                for (DataSnapshot ds: dataSnapshot.getChildren()){


                    String s = ds.getValue(String.class);
                    signsList.add(s);
                }
//
//
                len = signsList.size();

                for (int i = 0; i < len; i++) {
                    TextView ed = new TextView(ViewStressSigns.this);
                    ed.setText(signsList.get(i).toString());
                    TableLayout.LayoutParams layoutParams = new TableLayout.LayoutParams();

                    ed.setTextColor(Color.BLACK);
                    ed.setLayoutParams(layoutParams);
                    ed.setEnabled(false);
                    layoutParams.setMargins(0, 30, 0, 10);
                    mLayout.addView(ed);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        Query displayStressSignsP = myRef.child("users").child(user.getUid()).child("Resilience").child("resilience_10").child("choices");
        displayStressSignsP.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                signsList = new ArrayList<>();

                for (DataSnapshot ds: dataSnapshot.getChildren()){


                    String s = ds.getValue(String.class);
                    signsList.add(s);
                }
//
//
                len = signsList.size();

                for (int i = 0; i < len; i++) {
                    TextView ed = new TextView(ViewStressSigns.this);
                    ed.setText(signsList.get(i).toString());
                    TableLayout.LayoutParams layoutParams = new TableLayout.LayoutParams();

                    ed.setTextColor(Color.BLACK);
                    ed.setLayoutParams(layoutParams);
                    ed.setEnabled(false);
                    layoutParams.setMargins(30, 30, 0, 10);
                    mLayout.addView(ed);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        Query displayStressSignsIT = myRef.child("users").child(user.getUid()).child("Resilience").child("resilience_11").child("choices");
        displayStressSignsIT.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                signsList = new ArrayList<>();

                for (DataSnapshot ds: dataSnapshot.getChildren()){


                    String s = ds.getValue(String.class);
                    signsList.add(s);
                }
//
//
                len = signsList.size();

                for (int i = 0; i < len; i++) {
                    TextView ed = new TextView(ViewStressSigns.this);
                    ed.setText(signsList.get(i).toString());
                    TableLayout.LayoutParams layoutParams = new TableLayout.LayoutParams();

                    ed.setTextColor(Color.BLACK);
                    ed.setLayoutParams(layoutParams);
                    ed.setEnabled(false);
                    layoutParams.setMargins(0, 30, 0, 10);
                    mLayout.addView(ed);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
