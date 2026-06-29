package com.example.proyek_uas_hakim.repository;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.example.proyek_uas_hakim.api.RetrofitClient;
import com.example.proyek_uas_hakim.data.local.AppDatabase;
import com.example.proyek_uas_hakim.data.local.EndemikDao;
import com.example.proyek_uas_hakim.data.local.EndemikEntity;
import com.example.proyek_uas_hakim.model.Endemik;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EndemikRepository {

    public interface DataCallback {
        void onSuccess(List<Endemik> data);
        void onError(String message);
    }

    private static List<Endemik> cache;
    private static final ExecutorService executor = Executors.newSingleThreadExecutor();
    private static final Handler mainHandler = new Handler(Looper.getMainLooper());

    public static void getAllEndemik(Context context, DataCallback callback) {
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
                    saveToLocalDatabase(context, cache);
                } else {
                    loadFromLocalDatabase(context, callback);
                }
            }

            @Override
            public void onFailure(Call<List<Endemik>> call, Throwable t) {
                loadFromLocalDatabase(context, callback);
            }
        });
    }

    private static void saveToLocalDatabase(Context context, List<Endemik> data) {
        executor.execute(() -> {
            EndemikDao dao = AppDatabase.getInstance(context).endemikDao();
            List<EndemikEntity> entities = new ArrayList<>();
            for (Endemik e : data) {
                entities.add(new EndemikEntity(
                        e.getId(), e.getTipe(), e.getNama(), e.getNamaLatin(),
                        e.getFamili(), e.getGenus(), e.getDeskripsi(), e.getAsal(),
                        e.getSebaran(), e.getFoto(), e.getSumberFoto(), e.getVideo(),
                        e.getSumberVideo(), e.getStatus()
                ));
            }
            dao.insertAll(entities);
        });
    }

    private static void loadFromLocalDatabase(Context context, DataCallback callback) {
        executor.execute(() -> {
            EndemikDao dao = AppDatabase.getInstance(context).endemikDao();
            List<EndemikEntity> entities = dao.getAll();

            if (entities.isEmpty()) {
                mainHandler.post(() -> callback.onError("Tidak ada koneksi internet dan belum ada data tersimpan."));
                return;
            }

            List<Endemik> result = new ArrayList<>();
            for (EndemikEntity en : entities) {
                Endemik e = new Endemik();
                e.setId(en.getId());
                e.setTipe(en.getTipe());
                e.setNama(en.getNama());
                e.setNamaLatin(en.getNamaLatin());
                e.setFamili(en.getFamili());
                e.setGenus(en.getGenus());
                e.setDeskripsi(en.getDeskripsi());
                e.setAsal(en.getAsal());
                e.setSebaran(en.getSebaran());
                e.setFoto(en.getFoto());
                e.setSumberFoto(en.getSumberFoto());
                e.setVideo(en.getVideo());
                e.setSumberVideo(en.getSumberVideo());
                e.setStatus(en.getStatus());
                result.add(e);
            }

            cache = result;
            mainHandler.post(() -> callback.onSuccess(result));
        });
    }

    public static void getAllRegions(Context context, RegionCallback callback) {
        executor.execute(() -> {
            EndemikDao dao = AppDatabase.getInstance(context).endemikDao();
            List<String> regions = dao.getAllRegions();
            mainHandler.post(() -> callback.onResult(regions));
        });
    }

    public interface RegionCallback {
        void onResult(List<String> regions);
    }
}