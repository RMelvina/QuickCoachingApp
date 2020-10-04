package com.example.coachingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.coachingapp.Models.Register_ViewModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class RegisterUser extends AppCompatActivity {

    EditText uNmae;
    EditText uUsername;
    EditText uAddress;
    EditText uBirthdate;
    EditText uEmail;
    EditText uPassword;
    EditText uReEnterPassword;
    FirebaseAuth mAuth;
    FirebaseDatabase database;
    DatabaseReference myRef;
    FirebaseUser user;
    ProgressDialog progressDialog;
    Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);


        calendar = Calendar.getInstance();


        validate();
      //  datePicker();

        mAuth = FirebaseAuth.getInstance();
        progressDialog= new ProgressDialog(this);


    }


    @Override
    protected void onStart() {
        super.onStart();

        if (mAuth.getCurrentUser() != null) {
            //handle the already login user

        }
    }

    public void btn_createAccount(View view) {
        createNewAccount();
    }

    private void validate(){
        uNmae = findViewById(R.id.register_user_name);
        uUsername = findViewById(R.id.register_user_username);
//        uAddress = findViewById(R.id.register_user_address);
//        uBirthdate = findViewById(R.id.register_user_birthdate);
        uEmail = findViewById(R.id.register_user_email);
        uPassword = findViewById(R.id.register_user_password);
        uReEnterPassword = findViewById(R.id.register_user_reEnterPassword);
    }

//        private void datePicker(){
//
//        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
//            @Override
//            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//
//                calendar.set(Calendar.YEAR, year);
//                calendar.set(Calendar.MONTH, month);
//                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
//                updateLable();
//
//
//
//            }
//
//
//
//        };
//
//        uBirthdate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                new DatePickerDialog(RegisterUser.this, date, calendar.get(calendar.YEAR),calendar.get(calendar.MONTH), calendar.get(calendar.DAY_OF_MONTH) ).show();
//            }
//        });
//
//    }

//    private void updateLable(){
//        String myFormat = "MM/dd/yy";
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(myFormat, Locale.ENGLISH);
//
//        uBirthdate.setText(simpleDateFormat.format(calendar.getTime()));
//    }




    private void createNewAccount() {
        final String name = uNmae.getText().toString().trim();
        final String username = uUsername.getText().toString().trim();
//        final String address = uAddress.getText().toString().trim();
//        final String birthdate = uBirthdate.getText().toString().trim();
        final String email = uEmail.getText().toString().trim();
        final String password = uPassword.getText().toString().trim();
        final String reEnterPassword = uReEnterPassword.getText().toString().trim();


        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(username)
                || TextUtils.isEmpty(email) || TextUtils.isEmpty(password) || TextUtils.isEmpty(reEnterPassword)) {

            Toast.makeText(RegisterUser.this, "One or more fields are empty, please check again", Toast.LENGTH_SHORT).show();
        }

        if (!password.equals(reEnterPassword)) {

            Toast.makeText(RegisterUser.this, "Passwords do not match ", Toast.LENGTH_SHORT).show();

        } else {
            progressDialog.setMessage("Registering User....");
            progressDialog.show();
            mAuth.createUserWithEmailAndPassword(email,password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                        // progressBar.setVisibility(View.GONE);
                                //We will store the additional fields in freebase





                                Register_ViewModel registerViewModel = new Register_ViewModel(
                                        name,
                                        username,
                                        email,
                                        password,
                                        reEnterPassword
                                );


                                FirebaseDatabase.getInstance().getReference("users")
                                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .child("users info")
                                        .setValue(registerViewModel);

                                FirebaseUser fbUser = mAuth.getInstance().getCurrentUser();
                                fbUser.sendEmailVerification()

                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()){
                                                    startActivity(new Intent(RegisterUser.this, Login.class));

                                                    Toast.makeText(RegisterUser.this, "Registration is Successful, Please check your email for verification ", Toast.LENGTH_LONG).show();
                                                }else {
                                                    Toast.makeText(RegisterUser.this, "Registration is Unsuccessful ", Toast.LENGTH_LONG).show();
                                                }
                                            }
                                        });





                            }else {
                                Toast.makeText(RegisterUser.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }

                        }
                    });

        }
    }

}
