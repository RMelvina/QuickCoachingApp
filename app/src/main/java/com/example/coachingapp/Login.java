package com.example.coachingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.coachingapp.Goals.Goal_DisplaySummary;
import com.example.coachingapp.Goals.Goal_Question_4;
import com.example.coachingapp.Goals.Goal_Question_5;
import com.example.coachingapp.ResilienceandCopingMechanisms.resilience_Screen24;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuth.AuthStateListener;

public class Login extends AppCompatActivity {
    EditText sign_in_email;
    EditText sign_in_password;
    AuthStateListener mAuthListener;
    FirebaseAuth mAuth;


    ProgressDialog progressDialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        validate();
        firebaseAuth();
        //sample

        progressDialog= new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();

        if (mAuth.getCurrentUser() != null){

            Intent intent = new Intent(Login.this, UserDashboard.class);
            startActivity(intent);
            finish();
        }




    }


    private void firebaseAuth(){
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser() == null){

                    Toast.makeText(Login.this, "Sorry there is no user under this email", Toast.LENGTH_SHORT).show();
                }

            }
        };

    }


    @Override
    protected void onStart(){
        super.onStart();

        mAuth.addAuthStateListener(mAuthListener);


    }

    public void button_login(View view) {
        singIn();
    }

    public void button_register(View view) {
        Intent intent = new Intent(Login.this , RegisterUser.class);
        startActivity(intent);
    }

    private void validate(){
        sign_in_email = findViewById(R.id.email_text_field);
        sign_in_password = findViewById(R.id.password_text_field);
    }

    private void singIn(){
        String email = sign_in_email.getText().toString().trim();
        String pwd = sign_in_password
                .getText().toString().trim();

        if(TextUtils.isEmpty(email) || TextUtils.isEmpty(pwd)){
            Toast.makeText(Login.this, "Fields are empty", Toast.LENGTH_SHORT ).show();

        }else {
            progressDialog.setMessage("Signing User....");
            progressDialog.show();
            mAuth.signInWithEmailAndPassword(email,pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
//                    progressBar.setVisibility(View.GONE);
                    if (task.isSuccessful()){
                        //UserDashboard


                        if (mAuth.getCurrentUser().isEmailVerified()){

                            startActivity(new Intent(Login.this, UserDashboard.class));

                            Toast.makeText(Login.this, "Login Successful", Toast.LENGTH_SHORT ).show();

                        }else {

                            Toast.makeText(Login.this, "Please verify your email", Toast.LENGTH_LONG ).show();
                        }
                    }else {
                        progressDialog.dismiss();
                        Toast.makeText(Login.this, "Login Unsuccessful", Toast.LENGTH_SHORT ).show();
                    }

                }




            });
        }
    }

//    private void singIn(){
//        String email = sign_in_email.getText().toString().trim();
//        String pwd = sign_in_password
//                .getText().toString().trim();
//
//        if(TextUtils.isEmpty(email) || TextUtils.isEmpty(pwd)){
//            Toast.makeText(Login.this, "Fields are empty", Toast.LENGTH_SHORT ).show();
//
//        }else {
//            progressDialog.setMessage("Signing User....");
//            progressDialog.show();
//            mAuth.signInWithEmailAndPassword(email,pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                @Override
//                public void onComplete(@NonNull Task<AuthResult> task) {
////                    progressBar.setVisibility(View.GONE);
//                    if (task.isSuccessful()){
//                        //UserDashboard
//                        if (mAuth.getCurrentUser().isEmailVerified()){
//
//                            startActivity(new Intent(Login.this, UserDashboard.class));
//
//                            Toast.makeText(Login.this, "Login Successful", Toast.LENGTH_SHORT ).show();
//
//                        }else {
//
//                            Toast.makeText(Login.this, "Please verify your email", Toast.LENGTH_LONG ).show();
//                        }
//                        }else {
//                        progressDialog.dismiss();
//                        Toast.makeText(Login.this, "Login Unsuccessful", Toast.LENGTH_SHORT ).show();
//                    }
//
//                        }
//
//
//
//
//            });
//        }
//    }
}
