package com.example.loftcoin.welcome;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.loftcoin.R;
import com.example.loftcoin.main.MainActivity;
import com.example.loftcoin.util.Settings;

public class WelcomeActivity extends AppCompatActivity {

    //private ViewPagerIndicator mViewPagerIndicator;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        final RecyclerView pager = findViewById(R.id.pager);
        pager.setLayoutManager(new LinearLayoutManager(
                this, LinearLayoutManager.HORIZONTAL, false));
        pager.setAdapter(new WelcomePagerAdapter());
        new PagerSnapHelper().attachToRecyclerView(pager);

        findViewById(R.id.btn_start).setOnClickListener(view -> {
            Settings.of(view.getContext()).doNotShowWelcomeScreenNextTime();
            final Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        });

        //mViewPagerIndicator = (ViewPagerIndicator) findViewById(R.id.view_pager_indicator);
        //mViewPagerIndicator.setupWithViewPager(pager, true);
    }

}