package com.example.proyek_uas_hakim.ui.search;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyek_uas_hakim.R;
import com.example.proyek_uas_hakim.adapter.EndemikAdapter;
import com.example.proyek_uas_hakim.model.Endemik;
import com.example.proyek_uas_hakim.repository.EndemikRepository;
import com.example.proyek_uas_hakim.ui.detail.DetailActivity;
import com.example.proyek_uas_hakim.ui.favorite.FavoritActivity;

import java.util.ArrayList;
import java.util.List;

public class PencarianActivity extends AppCompatActivity {

    private RecyclerView rvResult;
    private EditText etSearch;
    private ImageButton btnClear;
    private TextView tvEmpty;
    private List<Endemik> allData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pencarian);

        ImageButton btnBack = findViewById(R.id.btn_back);
        ImageButton btnFavorite = findViewById(R.id.btn_favorite);
        etSearch = findViewById(R.id.et_search);
        btnClear = findViewById(R.id.btn_clear);
        rvResult = findViewById(R.id.rv_result);
        tvEmpty = findViewById(R.id.tv_empty);

        rvResult.setLayoutManager(new GridLayoutManager(this, 2));

        btnBack.setOnClickListener(v -> finish());
        btnFavorite.setOnClickListener(v -> startActivity(new Intent(this, FavoritActivity.class)));
        btnClear.setOnClickListener(v -> etSearch.setText(""));

        setupSearchListener();
        loadAllData();
    }

    private void loadAllData() {
        EndemikRepository.getAllEndemik(this, new EndemikRepository.DataCallback() {
            @Override
            public void onSuccess(List<Endemik> data) {
                allData = data;
            }

            @Override
            public void onError(String message) {
                // bisa tampilkan toast kalau perlu
            }
        });
    }

    private void setupSearchListener() {
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                btnClear.setVisibility(s.length() > 0 ? View.VISIBLE : View.GONE);
                filterData(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    private void filterData(String keyword) {
        if (keyword.trim().isEmpty()) {
            rvResult.setAdapter(null);
            tvEmpty.setVisibility(View.GONE);
            rvResult.setVisibility(View.VISIBLE);
            return;
        }

        List<Endemik> filtered = new ArrayList<>();
        for (Endemik e : allData) {
            if (e.getNama() != null && e.getNama().toLowerCase().contains(keyword.toLowerCase())) {
                filtered.add(e);
            }
        }

        if (filtered.isEmpty()) {
            tvEmpty.setVisibility(View.VISIBLE);
            rvResult.setVisibility(View.GONE);
        } else {
            tvEmpty.setVisibility(View.GONE);
            rvResult.setVisibility(View.VISIBLE);

            EndemikAdapter adapter = new EndemikAdapter(this, filtered, endemik -> {
                Intent intent = new Intent(this, DetailActivity.class);
                intent.putExtra(DetailActivity.EXTRA_ENDEMIK, endemik);
                startActivity(intent);
            });
            rvResult.setAdapter(adapter);
        }
    }
}