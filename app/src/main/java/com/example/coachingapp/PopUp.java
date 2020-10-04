package com.example.coachingapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;

import com.example.coachingapp.Issues_Problems.IP_Question_13;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import androidx.annotation.Nullable;

public class PopUp extends YouTubeBaseActivity {
    YouTubePlayerView playerView;
    String API_KEY = "AIzaSyBWlnrHL6YB_xIfym7WoBs52EmgvNeVzY8";
    String VIDEO_ID = "brpkjT9m2Oo&t=13s";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.are_you_sure_custom_box);

        playerView = findViewById(R.id.yourtube_play_video);

        playerView.initialize(API_KEY, new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.loadVideo(VIDEO_ID);

            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        });


        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int hight = dm.heightPixels;

        //getWindow().setLayout((int)(width*.8),(int)(hight*.6));

    }


    public void btn_n(View view) {
        Intent intent = new Intent(PopUp.this, IP_Question_13.class);
        startActivity(intent);
    }
}

