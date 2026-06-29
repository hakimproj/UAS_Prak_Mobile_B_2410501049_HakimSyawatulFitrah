package com.example.proyek_uas_hakim.ui.home;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.proyek_uas_hakim.R;
import com.example.proyek_uas_hakim.adapter.EndemikAdapter;
import com.example.proyek_uas_hakim.model.Endemik;
import com.example.proyek_uas_hakim.repository.EndemikRepository;

import java.util.ArrayList;
import java.util.List;

public class ListFragment extends Fragment {

    private static final String ARG_TIPE = "tipe";

    private RecyclerView rvList;
    private ProgressBar progressBar;
    private Spinner spinnerRegion;
    private String tipe;

    private List<Endemik> allDataByTipe = new ArrayList<>();
    private ArrayAdapter<String> spinnerAdapter;
    private List<String> regionList = new ArrayList<>();

    private SharedRegionViewModel sharedViewModel;
    private boolean isUpdatingFromViewModel = false;

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
        spinnerRegion = view.findViewById(R.id.spinner_region);

        rvList.setLayoutManager(new GridLayoutManager(getContext(), 2));

        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedRegionViewModel.class);

        loadData();
        return view;
    }

    private void loadData() {
        progressBar.setVisibility(View.VISIBLE);

        EndemikRepository.getAllEndemik(getContext(), new EndemikRepository.DataCallback() {
            @Override
            public void onSuccess(List<Endemik> data) {
                progressBar.setVisibility(View.GONE);

                allDataByTipe = new ArrayList<>();
                for (Endemik e : data) {
                    if (e.getTipe() != null && e.getTipe().equalsIgnoreCase(tipe)) {
                        allDataByTipe.add(e);
                    }
                }

                setupRegionSpinner();
                observeSharedRegion();
            }

            @Override
            public void onError(String message) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(getContext(), "Error: " + message, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setupRegionSpinner() {
        regionList = new ArrayList<>();
        regionList.add("Semua Region");

        for (Endemik e : allDataByTipe) {
            String asal = e.getAsal();
            if (asal != null && !regionList.contains(asal)) {
                regionList.add(asal);
            }
        }

        spinnerAdapter = new ArrayAdapter<>(getContext(), R.layout.item_spinner_region, regionList);
        spinnerAdapter.setDropDownViewResource(R.layout.item_spinner_region);
        spinnerRegion.setAdapter(spinnerAdapter);

        spinnerRegion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (isUpdatingFromViewModel) return; // hindari loop tak berujung
                String selected = regionList.get(position);
                sharedViewModel.setSelectedRegion(selected);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }

    private void observeSharedRegion() {
        sharedViewModel.getSelectedRegion().observe(getViewLifecycleOwner(), region -> {
            if (region == null) return;

            // Kalau region tersebut belum ada di daftar fragment ini, tambahkan supaya tetap tersinkron
            if (!regionList.contains(region)) {
                regionList.add(region);
                spinnerAdapter.notifyDataSetChanged();
            }

            int position = regionList.indexOf(region);
            isUpdatingFromViewModel = true;
            spinnerRegion.setSelection(position);
            isUpdatingFromViewModel = false;

            showFilteredData(region);
        });
    }

    private void showFilteredData(String selectedRegion) {
        List<Endemik> filtered = new ArrayList<>();

        for (Endemik e : allDataByTipe) {
            if (selectedRegion.equals("Semua Region") || selectedRegion.equals(e.getAsal())) {
                filtered.add(e);
            }
        }

        EndemikAdapter adapter = new EndemikAdapter(getContext(), filtered, endemik -> {
            android.content.Intent intent = new android.content.Intent(getContext(),
                    com.example.proyek_uas_hakim.ui.detail.DetailActivity.class);
            intent.putExtra(com.example.proyek_uas_hakim.ui.detail.DetailActivity.EXTRA_ENDEMIK, endemik);
            startActivity(intent);
        });
        rvList.setAdapter(adapter);
    }
}