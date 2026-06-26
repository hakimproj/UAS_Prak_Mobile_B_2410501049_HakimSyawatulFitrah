package com.example.proyek_uas_hakim.ui.detail;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.proyek_uas_hakim.R;
import com.example.proyek_uas_hakim.model.Endemik;
import com.example.proyek_uas_hakim.repository.FavoritRepository;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_ENDEMIK = "extra_endemik";

    private ImageButton btnFavorite;
    private boolean isFavorit = false;
    private Endemik endemik;
    private FavoritRepository favoritRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        favoritRepository = new FavoritRepository(this);

        endemik = (Endemik) getIntent().getSerializableExtra(EXTRA_ENDEMIK);
        if (endemik == null) {
            finish();
            return;
        }

        ImageButton btnBack = findViewById(R.id.btn_back);
        btnFavorite = findViewById(R.id.btn_favorite);
        ImageView imgDetail = findViewById(R.id.img_detail);
        TextView tvNama = findViewById(R.id.tv_nama);
        TextView tvNamaLatin = findViewById(R.id.tv_nama_latin);
        TextView tvStatus = findViewById(R.id.tv_status);
        TextView tvAsal = findViewById(R.id.tv_asal);
        TextView tvSebaran = findViewById(R.id.tv_sebaran);
        TextView tvDeskripsi = findViewById(R.id.tv_deskripsi);

        tvNama.setText(endemik.getNama());
        tvNamaLatin.setText(endemik.getNamaLatin());
        tvStatus.setText(endemik.getStatus());

        if ("Aman".equalsIgnoreCase(endemik.getStatus())) {
            tvStatus.setBackgroundColor(getResources().getColor(android.R.color.holo_green_dark));
        } else if ("Terancam Punah".equalsIgnoreCase(endemik.getStatus())) {
            tvStatus.setBackgroundColor(getResources().getColor(android.R.color.holo_red_dark));
        }

        tvAsal.setText(endemik.getAsal());
        tvSebaran.setText(endemik.getSebaran());
        tvDeskripsi.setText(endemik.getDeskripsi());

        Glide.with(this)
                .load(endemik.getFoto())
                .placeholder(R.drawable.ic_placeholder_image)
                .error(R.drawable.ic_placeholder_image)
                .centerCrop()
                .into(imgDetail);

        btnBack.setOnClickListener(v -> finish());

        checkFavoritStatus();

        btnFavorite.setOnClickListener(v -> toggleFavorit());
    }

    private void checkFavoritStatus() {
        favoritRepository.isFavorit(endemik.getId(), result -> {
            isFavorit = result;
            updateFavoriteIcon();
        });
    }

    private void toggleFavorit() {
        if (isFavorit) {
            favoritRepository.removeFavorit(endemik.getId());
            isFavorit = false;
        } else {
            favoritRepository.addFavorit(endemik);
            isFavorit = true;
        }
        updateFavoriteIcon();
    }

    private void updateFavoriteIcon() {
        if (isFavorit) {
            btnFavorite.setImageResource(R.drawable.ic_favorite);
        } else {
            btnFavorite.setImageResource(R.drawable.ic_favorite_border);
        }
    }
}