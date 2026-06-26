package com.example.proyek_uas_hakim.repository;

import com.example.proyek_uas_hakim.api.RetrofitClient;
import com.example.proyek_uas_hakim.model.Endemik;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EndemikRepository {

    public interface DataCallback {
        void onSuccess(List<Endemik> data);
        void onError(String message);
    }

    private static List<Endemik> cache; // cache supaya tidak fetch API berulang-ulang

    public static void getAllEndemik(DataCallback callback) {
        if (cache != null) {
            callback.onSuccess(cache);
            return;
        }

        RetrofitClient.getApiService().getEndemik().enqueue(new Callback<List<Endemik>>() {
            @Override
            public void onResponse(Call<List<Endemik>> call, Response<List<Endemik>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    cache = response.body();
                    callback.onSuccess(cache);
                } else {
                    callback.onError("Response gagal: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<Endemik>> call, Throwable t) {
                callback.onError(t.getMessage());
            }
        });
    }
}