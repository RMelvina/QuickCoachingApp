package com.example.coachingapp.Goals;

import android.content.Intent;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.coachingapp.AboutUs;
import com.example.coachingapp.Login;
import com.example.coachingapp.Models.Goals_Road_Map;
import com.example.coachingapp.Models.Goals_Road_Map_Identify;
import com.example.coachingapp.R;
import com.example.coachingapp.SendEmail.SendEmail;
import com.example.coachingapp.UserDashboard;
import com.example.coachingapp.ViewYourRoadMapSteps;
import com.example.coachingapp.ViewYourRoadMapStepsHazard;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

public class Goal_DisplaySummary extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    ImageButton imageIconRight;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;
   final int REQUEST_CODE_ASK_PERMISSIONS = 111;
   File pdfFile;
   com.itextpdf.text.pdf.PdfDocument pdfDocument;

    FirebaseAuth firebaseAuth;
    FirebaseDatabase database;
    DatabaseReference myRef;
    EditText editText;
    FirebaseUser user;
    TextView txttime, txtday, txtMonth, display_goal, display_reason;
    List<String> answerList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goal__display_summary);
        nav();


        txtday = findViewById(R.id.display_input_date_day);
        txttime = findViewById(R.id.display_input_date_time);
        txtMonth = findViewById(R.id.display_input_date_month);
        display_goal = findViewById(R.id.display_goal);
        display_reason = findViewById(R.id.display_reason);


        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        myRef = FirebaseDatabase.getInstance().getReference();
        database = FirebaseDatabase.getInstance();

        Query query = myRef.child("users").child(user.getUid());
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String day = dataSnapshot.child("Time_Day_Month").child("mDay").getValue().toString();
                String time = dataSnapshot.child("Time_Day_Month").child("mTime").getValue().toString();
                String month = dataSnapshot.child("Time_Day_Month").child("mMonth").getValue().toString();
                txtday.setText(day);
                txtMonth.setText(month);
                txttime.setText(time);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        Query goalQuery = myRef.child("users").child(user.getUid());
        goalQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String whatGoal = dataSnapshot.child("Goals").child("input_1").child("user_input_1").getValue().toString();
                display_goal.setText(whatGoal);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        Query whyQuery = myRef.child("users").child(user.getUid());
        whyQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String whyGoal = dataSnapshot.child("Goals").child("input_2").child("user_input_2").getValue().toString();
                display_reason.setText(whyGoal);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    // List<String> goalsList = new ArrayList<>();


    private void sentEmail(){
        Query query = myRef.child("users").child(user.getUid());
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String day = dataSnapshot.child("Goals").child("answers").getValue().toString();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }



  //  @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void btn_email(View view)  {


        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto","Write You Email Here", null));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Da Vinci's Workshop");
        emailIntent.putExtra(Intent.EXTRA_TEXT,"Hi There! \n\n Please see summary below: \n\n" + "Your Goal is " + display_goal.getText().toString()  + "\n Which You want to achieve because: " + display_reason.getText().toString()+"\nYou will take first action in making this goal a reality on: " + txtday.getText().toString()+ " " + txtMonth.getText().toString()+ " "+ txttime.getText().toString()+ "\n\n We hope this simple coaching exercise has helped." +"\n\n Thanks," + "\nYour Da Vinci's Workshop Team");
        startActivity(Intent.createChooser(emailIntent, "Send email..."));

      //  try {
       //     createPdf();

       // }catch (FileNotFoundException e){
      //      e.printStackTrace();

   //     }catch (DocumentException e){
   //         e.printStackTrace();
        }
//        PdfDocument pdfDocument = new PdfDocument();
//        PdfDocument.PageInfo myPageInfo = new PdfDocument.PageInfo.Builder(300,600, 1).create();
//        PdfDocument.Page myPage =  pdfDocument.startPage(myPageInfo);
//
//        Paint paint = new Paint();
//        int x = 10, y = 25;
//
//        String displayGoalString = editText.getText().toString();
//        //display_goal.getText().toString();
//        // String displayreasonString = display_reason.getText().toString();
//
//        myPage.getCanvas().drawText(displayGoalString,x, y, paint);
////        myPage.getCanvas().drawText(displayreasonString, x, y, paint);
//        pdfDocument.finishPage(myPage);
//        String directory_path = Environment.getExternalStorageDirectory().getPath() +"/mypdf/";
//        File myfile = new File(directory_path);
//        if (!myfile.exists()){
//            myfile.mkdirs();
//        }
//
//        String targetPdf = directory_path + "test-2.pdf";
//        File filePath = new File(targetPdf);
//
//        try{
//            pdfDocument.writeTo(new FileOutputStream(filePath));
//            Toast.makeText(this, "Done", Toast.LENGTH_LONG).show();
//        }catch (Exception e){
//            e.printStackTrace();
//            Log.e("pdf", "error");
//            Toast.makeText(this, "Something wrong: " + e.toString(),  Toast.LENGTH_LONG).show();
//
//        }
//

    //}

    public void btn_home(View view) {
        Intent intent = new Intent(Goal_DisplaySummary.this, UserDashboard.class);
        startActivity(intent);
    }


    private void sentMail(){

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

    public void btn_view_roadmap(View view) {
        Intent intent = new Intent(Goal_DisplaySummary.this, ViewYourRoadMapSteps.class);
        startActivity(intent);
    }


    public void btnviewroadmaphazardicon(View view) {
        Intent intent = new Intent(Goal_DisplaySummary.this, ViewYourRoadMapStepsHazard.class);
        startActivity(intent);
    }
}


