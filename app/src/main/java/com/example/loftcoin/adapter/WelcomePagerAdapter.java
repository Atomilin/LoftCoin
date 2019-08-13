package com.example.loftcoin.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.loftcoin.R;

import java.util.Objects;

public class WelcomePagerAdapter extends PagerAdapter {

    private static final int[] IMAGES = {
            R.drawable.welcome_page_1,
            R.drawable.welcome_page_2,
            R.drawable.welcome_page_3
    };
    private static final int[] TITLE = {
            R.string.welcome_page_1_title,
            R.string.welcome_page_2_title,
            R.string.welcome_page_3_title
    };
    private static final int[] SUBTITLE = {
            R.string.welcome_page_1_subtitle,
            R.string.welcome_page_2_subtitle,
            R.string.welcome_page_3_subtitle
    };

    private final LayoutInflater mInflater;

    public WelcomePagerAdapter(@NonNull LayoutInflater inflater) {
        mInflater = Objects.requireNonNull(inflater);
    }

    @Override
    public int getCount() {
        return IMAGES.length;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = mInflater.inflate(R.layout.welcome_page, container, false);
        container.addView(view, new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
        ));
        view.<ImageView>findViewById(R.id.image).setImageResource(IMAGES[position]);
        view.<TextView>findViewById(R.id.title).setText(TITLE[position]);
        view.<TextView>findViewById(R.id.subtitle).setText(SUBTITLE[position]);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return Objects.equals(view, object);
    }
}
