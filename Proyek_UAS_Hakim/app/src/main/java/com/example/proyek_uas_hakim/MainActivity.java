package com.example.proyek_uas_hakim;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.proyek_uas_hakim.ui.home.ViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import android.content.Intent;
import com.example.proyek_uas_hakim.ui.favorite.FavoritActivity;

import com.example.proyek_uas_hakim.ui.search.PencarianActivity;

import com.example.proyek_uas_hakim.ui.profile.ProfilActivity;

public class MainActivity extends AppCompatActivity {

    private ViewPager2 viewPager;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.view_pager);
        tabLayout = findViewById(R.id.tab_layout);

        ImageButton btnSearch = findViewById(R.id.btn_search);
        ImageButton btnFavorite = findViewById(R.id.btn_favorite);
        ImageButton btnPerson = findViewById(R.id.btn_person);

        setupViewPager();

        btnSearch.setOnClickListener(v -> startActivity(new Intent(this, PencarianActivity.class)));
        btnFavorite.setOnClickListener(v -> startActivity(new Intent(this, FavoritActivity.class)));
        btnPerson.setOnClickListener(v -> startActivity(new Intent(this, ProfilActivity.class)));
    }

    private void setupViewPager() {
        ViewPagerAdapter adapter = new ViewPagerAdapter(this);
        viewPager.setAdapter(adapter);

        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            if (position == 0) {
                tab.setText("Hewan");
                tab.setIcon(R.drawable.ic_pets);
            } else {
                tab.setText("Tumbuhan");
                tab.setIcon(R.drawable.ic_eco);
            }
        }).attach();
    }
}