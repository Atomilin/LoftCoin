package com.example.loftcoin.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.loftcoin.R;
import com.example.loftcoin.adapter.WelcomePagerAdapter;

public class WelcomeActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private Button mBtnStart;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        mViewPager = findViewById(R.id.pager);
        mViewPager.setAdapter(new WelcomePagerAdapter(getLayoutInflater()));
        mBtnStart = findViewById(R.id.btn_start);

        mBtnStart.setOnClickListener(view -> {
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
            prefs.edit().putBoolean("show_welcome_screen", false).apply();
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        });
    }
}
