package com.example.proyek_uas_hakim.ui.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proyek_uas_hakim.MainActivity;
import com.example.proyek_uas_hakim.R;
import com.example.proyek_uas_hakim.utils.ThemeHelper;

public class SplashActivity extends AppCompatActivity {

    private static final int DURATION = 2000; // 2 detik

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ThemeHelper.applySavedTheme(this); // dipanggil ulang di sini, sebelum super.onCreate()
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            startActivity(new Intent(SplashActivity.this, MainActivity.class));
            finish();
        }, DURATION);
    }
}