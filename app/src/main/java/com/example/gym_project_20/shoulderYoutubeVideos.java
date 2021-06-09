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

public class shoulderYoutubeVideos extends AppCompatActivity {

    YouTubePlayerView shoulderYoutubePlay, shoulderYoutubePlay2;
    TextView shouderBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoulder_youtube_videos);

        shoulderYoutubePlay = findViewById(R.id.shoulderYoutubePlayerView);
        shoulderYoutubePlay2 = findViewById(R.id.shoulderYoutubePlayerView2);
        shouderBack = findViewById(R.id.shoulderBackBtn);

        shoulderYoutubePlay.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady( YouTubePlayer youTubePlayer) {
                String videosId = "mUbnnR9ClJk";
                youTubePlayer.loadVideo(videosId,0);
            }
        });

        shoulderYoutubePlay2.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady( YouTubePlayer youTubePlayer) {
                String videosId = "UO2p2UtAAqw";
                youTubePlayer.loadVideo(videosId,1);
            }
        });

        shouderBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(shoulderYoutubeVideos.this, WorkoutActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}