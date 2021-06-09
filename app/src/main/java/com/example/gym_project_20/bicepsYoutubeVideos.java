package com.example.gym_project_20;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.annotations.NotNull;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

public class bicepsYoutubeVideos extends AppCompatActivity {

    YouTubePlayerView bicepsYoutubePlay, bicepsYoutubePlay2;
    TextView bicepsBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_biceps_youtube_videos);

        bicepsYoutubePlay = findViewById(R.id.bicepsYoutubePlayerView);
        bicepsYoutubePlay2 = findViewById(R.id.bicepsYoutubePlayerView2);
        bicepsBack = findViewById(R.id.bicepsBackBtn);

        bicepsYoutubePlay.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(  YouTubePlayer  youTubePlayer) {
                String videosId = "mqfzsbV0lUs";
                youTubePlayer.loadVideo(videosId,0);
            }
        });

        bicepsYoutubePlay2.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady( YouTubePlayer youTubePlayer) {
                String videosId = "GxjWKyzMx3E";
                youTubePlayer.loadVideo(videosId,1);
            }
        });

        bicepsBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(bicepsYoutubeVideos.this, WorkoutActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}