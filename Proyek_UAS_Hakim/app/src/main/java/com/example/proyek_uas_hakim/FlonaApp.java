package com.example.proyek_uas_hakim;

import android.app.Application;

import com.example.proyek_uas_hakim.utils.ThemeHelper;

public class FlonaApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ThemeHelper.applySavedTheme(this);
    }
}