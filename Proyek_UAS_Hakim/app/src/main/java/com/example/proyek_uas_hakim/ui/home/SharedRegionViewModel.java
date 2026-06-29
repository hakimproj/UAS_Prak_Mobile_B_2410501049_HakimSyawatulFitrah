package com.example.proyek_uas_hakim.ui.home;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SharedRegionViewModel extends ViewModel {

    private final MutableLiveData<String> selectedRegion = new MutableLiveData<>("Semua Region");

    public MutableLiveData<String> getSelectedRegion() {
        return selectedRegion;
    }

    public void setSelectedRegion(String region) {
        selectedRegion.setValue(region);
    }
}