package com.example.proyek_uas_hakim.ui.favorite;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyek_uas_hakim.R;
import com.example.proyek_uas_hakim.adapter.EndemikAdapter;
import com.example.proyek_uas_hakim.model.Endemik;
import com.example.proyek_uas_hakim.repository.FavoritRepository;
import com.example.proyek_uas_hakim.ui.detail.DetailActivity;

import java.util.List;

public class FavoritActivity extends AppCompatActivity {

    private RecyclerView rvFavorit;
    private TextView tvEmpty;
    private FavoritRepository favoritRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorit);

        ImageButton btnBack = findViewById(R.id.btn_back);
        rvFavorit = findViewById(R.id.rv_favorit);
        tvEmpty = findViewById(R.id.tv_empty);

        rvFavorit.setLayoutManager(new GridLayoutManager(this, 2));
        favoritRepository = new FavoritRepository(this);

        btnBack.setOnClickListener(v -> finish());

        loadFavorit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadFavorit(); // refresh tiap kali balik ke halaman ini (misal habis unfavorite dari Detail)
    }

    private void loadFavorit() {
        favoritRepository.getAllFavoritAsEndemik(this::showResult);
    }

    private void showResult(List<Endemik> list) {
        if (list.isEmpty()) {
            tvEmpty.setVisibility(View.VISIBLE);
            rvFavorit.setVisibility(View.GONE);
            return;
        }

        tvEmpty.setVisibility(View.GONE);
        rvFavorit.setVisibility(View.VISIBLE);

        EndemikAdapter adapter = new EndemikAdapter(this, list, endemik -> {
            Intent intent = new Intent(this, DetailActivity.class);
            intent.putExtra(DetailActivity.EXTRA_ENDEMIK, endemik);
            startActivity(intent);
        });
        rvFavorit.setAdapter(adapter);
    }
}