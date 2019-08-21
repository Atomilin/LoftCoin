package com.example.loftcoin.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import androidx.annotation.NonNull;

class SettingsImpl implements Settings {

    private static final String KEY_SHOW_WELCOME_SCREEN = "show_welcome_screen";

    private final SharedPreferences mPrefs;

    SettingsImpl(@NonNull Context context) {
        mPrefs = PreferenceManager.getDefaultSharedPreferences(context);
    }

    @Override
    public boolean shouldShowWelcomeScreen() {
        return mPrefs.getBoolean(KEY_SHOW_WELCOME_SCREEN, true);
    }

    @Override
    public void doNotShowWelcomeScreenNextTime() {
        mPrefs.edit().putBoolean(KEY_SHOW_WELCOME_SCREEN, false).apply();
    }

}
