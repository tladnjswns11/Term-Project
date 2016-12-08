package com.example.b10720.scheduleapplication;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity implements Animation.AnimationListener {
    private Animation alphaAni;
    private ImageView splash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        splash = (ImageView) findViewById(R.id.splashAni);
        alphaAni = AnimationUtils.loadAnimation(this, R.anim.alpha_ani);
        alphaAni.setAnimationListener(this);
        splash.startAnimation(alphaAni);
    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}
