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

public class legYoutubeVideos extends AppCompatActivity {

    YouTubePlayerView legYoutubePlay, legYoutubePlay2;
    TextView legBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leg_youtube_videos);

        legYoutubePlay = findViewById(R.id.legYoutubePlayerView);
        legYoutubePlay2 = findViewById(R.id.legYoutubePlayerView2);
        legBack = findViewById(R.id.legBackBtn);

        legYoutubePlay.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady( YouTubePlayer youTubePlayer) {
                String videosId = "9vWgSTdkc2A";
                youTubePlayer.loadVideo(videosId,0);
            }
        });

        legYoutubePlay2.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady( YouTubePlayer youTubePlayer) {
                String videosId = "YnKkT_XQ-hg";
                youTubePlayer.loadVideo(videosId,1);
            }
        });

        legBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(legYoutubeVideos.this, WorkoutActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}