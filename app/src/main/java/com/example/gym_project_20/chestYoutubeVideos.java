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

public class chestYoutubeVideos extends AppCompatActivity {

    YouTubePlayerView chestYoutubePlay, chestYoutubePlay2;
    TextView chestback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chest_youtube_videos);

        chestYoutubePlay = findViewById(R.id.chestYoutubePlayerView);
        chestYoutubePlay2 = findViewById(R.id.chestYoutubePlayerView2);
        chestback = findViewById(R.id.chestBackBtn);

        chestYoutubePlay.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady( YouTubePlayer youTubePlayer) {
                String videosId = "MVMNk0HiTMg";
                youTubePlayer.loadVideo(videosId,0);
            }
        });

        chestYoutubePlay2.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(  YouTubePlayer youTubePlayer) {
                String videosId = "UxdTsE1esIQ";
                youTubePlayer.loadVideo(videosId,1);
            }
        });

        chestback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(chestYoutubeVideos.this, WorkoutActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }
}