package com.example.coachingapp;

        import androidx.appcompat.app.AppCompatActivity;

        import android.content.Intent;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.View;
        import android.view.animation.Animation;
        import android.view.animation.AnimationUtils;
        import android.widget.Button;

        import com.example.coachingapp.Goals.Goal_1;
        import com.example.coachingapp.Goals.Goal_11;
        import com.example.coachingapp.Goals.Goal_12;
        import com.example.coachingapp.Goals.Goal_2;
        import com.example.coachingapp.Goals.Goal_3;
        import com.example.coachingapp.Goals.Goal_DisplaySummary;
        import com.example.coachingapp.Issues_Problems.IP_QuestionSix;
        import com.example.coachingapp.Issues_Problems.IP_Question_12;
        import com.example.coachingapp.Issues_Problems.IP_Question_13;
        import com.example.coachingapp.Issues_Problems.IP_Question_14;
        import com.example.coachingapp.Issues_Problems.IP_Question_15;
        import com.example.coachingapp.Issues_Problems.IP_Question_9;
        import com.example.coachingapp.Issues_Problems.LastQuestion;
        import com.example.coachingapp.ResilienceandCopingMechanisms.ResilienceMain;
        import com.example.coachingapp.ResilienceandCopingMechanisms.Slide_12_Behavioural;
        import com.example.coachingapp.ResilienceandCopingMechanisms.Slide_13;
        import com.example.coachingapp.ResilienceandCopingMechanisms.Slide_15;
        import com.example.coachingapp.ResilienceandCopingMechanisms.Slide_16;
        import com.example.coachingapp.ResilienceandCopingMechanisms.Slide_16_choices;
        import com.example.coachingapp.ResilienceandCopingMechanisms.Slide_5;
        import com.example.coachingapp.ResilienceandCopingMechanisms.Slide_8;
        import com.example.coachingapp.ResilienceandCopingMechanisms.Slide_9_DNA;
        import com.example.coachingapp.ResilienceandCopingMechanisms.resilience_Screen21;
        import com.example.coachingapp.ResilienceandCopingMechanisms.resilience_Screen22;
        import com.example.coachingapp.ResilienceandCopingMechanisms.resilience_Screen24;
        import com.example.coachingapp.ResilienceandCopingMechanisms.resilience_Screen25;
        import com.example.coachingapp.ResilienceandCopingMechanisms.resilience_Screen51;


public class SplashScreen extends AppCompatActivity  {

    Button splashScreenButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        start();
    }


    public void start(){
        splashScreenButton = (Button)findViewById(R.id.btn_start);
        Animation button_fade_in_ani = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_in);
        splashScreenButton.startAnimation(button_fade_in_ani);

    }

    public void btn_splash_screen_begin(View view) {
        Intent intent = new Intent(SplashScreen.this,  Login.class);
        startActivity(intent);

    }
            }
