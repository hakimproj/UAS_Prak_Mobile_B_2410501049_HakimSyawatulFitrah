package com.example.proyek_uas_hakim.ui.home;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.proyek_uas_hakim.R;
import com.example.proyek_uas_hakim.adapter.EndemikAdapter;
import com.example.proyek_uas_hakim.model.Endemik;
import com.example.proyek_uas_hakim.repository.EndemikRepository;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import com.example.proyek_uas_hakim.ui.detail.DetailActivity;

public class ListFragment extends Fragment {

    private static final String ARG_TIPE = "tipe";

    private RecyclerView rvList;
    private ProgressBar progressBar;
    private String tipe;

    public static ListFragment newInstance(String tipe) {
        ListFragment fragment = new ListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TIPE, tipe);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            tipe = getArguments().getString(ARG_TIPE);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        rvList = view.findViewById(R.id.rv_list);
        progressBar = view.findViewById(R.id.progress_bar);

        rvList.setLayoutManager(new GridLayoutManager(getContext(), 2));

        loadData();
        return view;
    }

    private void loadData() {
        progressBar.setVisibility(View.VISIBLE);

        EndemikRepository.getAllEndemik(new EndemikRepository.DataCallback() {
            @Override
            public void onSuccess(List<Endemik> data) {
                progressBar.setVisibility(View.GONE);

                List<Endemik> filtered = new ArrayList<>();
                for (Endemik e : data) {
                    if (e.getTipe() != null && e.getTipe().equalsIgnoreCase(tipe)) {
                        filtered.add(e);
                    }
                }

                EndemikAdapter adapter = new EndemikAdapter(getContext(), filtered, endemik -> {
                    Intent intent = new Intent(getContext(), DetailActivity.class);
                    intent.putExtra(DetailActivity.EXTRA_ENDEMIK, endemik);
                    startActivity(intent);
                });
                rvList.setAdapter(adapter);
            }

            @Override
            public void onError(String message) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(getContext(), "Error: " + message, Toast.LENGTH_SHORT).show();
            }
        });
    }
}