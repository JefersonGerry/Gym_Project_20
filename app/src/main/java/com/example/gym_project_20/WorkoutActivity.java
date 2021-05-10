package com.example.gym_project_20;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

public class WorkoutActivity extends AppCompatActivity {

    TextView titlepage, subtitlepage, intropage,subintropage,btnexercise,fitChestWork,fitscChestWork,
            fitBicepsWork,fitscBicepsWork,fitShoulderWork,fitscShoulderWork,fitLegWork,fitscLegWork;
    LinearLayout fitChest,fitBiceps,fitShoulder,fitLeg;
    View divpage, bgprogress;
    Animation bttone,bttwo, bttfour, bttfive,bttsix,bttsevem,btteight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);

        // carregando animações
        bttone = AnimationUtils.loadAnimation(this,R.anim.bttone);
        bttwo = AnimationUtils.loadAnimation(this,R.anim.bttwo);
        bttfour = AnimationUtils.loadAnimation(this,R.anim.bttfour);
        bttfive = AnimationUtils.loadAnimation(this,R.anim.bttfive);
        bttsix = AnimationUtils.loadAnimation(this,R.anim.bttsix);
        bttsevem = AnimationUtils.loadAnimation(this,R.anim.bttsevem);
        btteight = AnimationUtils.loadAnimation(this,R.anim.btteight);

        // carregando atributos de activity_workout
        titlepage = findViewById(R.id.titlePageWork);
        subtitlepage = findViewById(R.id.subTitlePageWork);
        intropage = findViewById(R.id.introPageWork);
        subintropage = findViewById(R.id.subIntroPageWork);
        btnexercise = findViewById(R.id.exerciceBtnWork);

        fitChest = findViewById(R.id.fitTitleChest);
        fitChestWork = findViewById(R.id.fitChestWork);
        fitscChestWork = findViewById(R.id.fitscChestWork);
        fitBiceps = findViewById(R.id.fitTitleBiceps);
        fitBicepsWork = findViewById(R.id.fitBicepsWork);
        fitscBicepsWork = findViewById(R.id.fitscBicepsWork);
        fitShoulder = findViewById(R.id.fitTitleShoulder);
        fitShoulderWork = findViewById(R.id.fitShoulderWork);
        fitscShoulderWork = findViewById(R.id.fitscShoulderWork);
        fitLeg = findViewById(R.id.fitTitleLeg);
        fitLegWork = findViewById(R.id.fitLegWork);
        fitscLegWork = findViewById(R.id.fitscLegWork);

        divpage = findViewById(R.id.divpageWork);
        bgprogress = findViewById(R.id.progressBtnWork);

        // aplicando animação

        titlepage.startAnimation(bttone);
        subtitlepage.startAnimation(bttwo);
        divpage.startAnimation(bttone);

        intropage.startAnimation(bttwo);
        subintropage.startAnimation(bttwo);

        fitChest.startAnimation(bttwo);
        fitBiceps.startAnimation(bttfour);
        fitShoulder.startAnimation(bttfive);
        fitLeg.startAnimation(bttsix);

        btnexercise.startAnimation(btteight);
        bgprogress.startAnimation(bttsevem);

    }
}