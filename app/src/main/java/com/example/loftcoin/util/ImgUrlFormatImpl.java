package com.example.loftcoin.util;

import androidx.annotation.NonNull;

import com.example.loftcoin.BuildConfig;

import java.util.Locale;

public class ImgUrlFormatImpl implements ImgUrlFormat {

    @NonNull
    @Override
    public String format(int id) {
        return String.format(Locale.US, "%scoins/64x64/%d.png",
                BuildConfig.CMC_IMG_ENDPOINT, id);
    }

}
