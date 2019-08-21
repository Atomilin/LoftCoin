package com.example.loftcoin.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.loftcoin.R;
import com.example.loftcoin.main.MainActivity;
import com.example.loftcoin.util.Settings;
import com.example.loftcoin.welcome.WelcomeActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        final Settings settings = Settings.of(this);
        new Handler().postDelayed(() -> {
            if (settings.shouldShowWelcomeScreen()) {
                startActivity(new Intent(this, WelcomeActivity.class));
            } else {
                startActivity(new Intent(this, MainActivity.class));
            }
        }, 1000);
    }

}
