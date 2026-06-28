package com.example.proyek_uas_hakim.ui.profile;

import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proyek_uas_hakim.R;
import com.example.proyek_uas_hakim.utils.ThemeHelper;
import com.google.android.material.switchmaterial.SwitchMaterial;

public class ProfilActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        ImageButton btnBack = findViewById(R.id.btn_back);
        btnBack.setOnClickListener(v -> finish());

        SwitchMaterial switchDarkMode = findViewById(R.id.switch_dark_mode);
        switchDarkMode.setChecked(ThemeHelper.isDarkModeOn(this));

        switchDarkMode.setOnCheckedChangeListener((buttonView, isChecked) -> {
            ThemeHelper.setDarkMode(this, isChecked);
            recreate(); // refresh activity ini supaya warna langsung ganti
        });
    }
}