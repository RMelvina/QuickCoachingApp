package com.example.coachingapp.SendEmail;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.coachingapp.R;

public class SendEmail extends AppCompatActivity {

    EditText etTo, etSubject;
    TextView tvRes;
    Button btnSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_email);

        etTo = findViewById(R.id.editEmail);
        etSubject = findViewById(R.id.editSubject);
        tvRes = findViewById(R.id.editSubject);
        btnSend = findViewById(R.id.btn_email);


        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent email = new Intent(Intent.ACTION_VIEW ,Uri.parse("mailto:" +etTo.getText().toString()));
                email.putExtra(Intent.EXTRA_SUBJECT,etSubject.getText().toString());
                email.putExtra(Intent.EXTRA_TEXT,tvRes.getText().toString());
                startActivity(email);

            }
        });
    }
}
