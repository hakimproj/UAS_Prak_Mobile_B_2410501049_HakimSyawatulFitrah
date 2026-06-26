package com.example.proyek_uas_hakim.repository;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.example.proyek_uas_hakim.data.local.AppDatabase;
import com.example.proyek_uas_hakim.data.local.FavoritDao;
import com.example.proyek_uas_hakim.data.local.FavoritEntity;
import com.example.proyek_uas_hakim.model.Endemik;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FavoritRepository {

    private final FavoritDao dao;
    private final ExecutorService executor = Executors.newSingleThreadExecutor();
    private final Handler mainHandler = new Handler(Looper.getMainLooper());

    public FavoritRepository(Context context) {
        dao = AppDatabase.getInstance(context).favoritDao();
    }

    public interface BooleanCallback {
        void onResult(boolean result);
    }

    public interface ListCallback {
        void onResult(List<FavoritEntity> list);
    }

    public void isFavorit(String id, BooleanCallback callback) {
        executor.execute(() -> {
            boolean result = dao.isFavorit(id);
            mainHandler.post(() -> callback.onResult(result));
        });
    }

    public void addFavorit(Endemik e) {
        executor.execute(() -> {
            FavoritEntity entity = new FavoritEntity(
                    e.getId(), e.getTipe(), e.getNama(), e.getNamaLatin(),
                    e.getDeskripsi(), e.getAsal(), e.getSebaran(), e.getFoto(),
                    e.getSumberFoto(), e.getVideo(), e.getSumberVideo(), e.getStatus()
            );
            dao.insert(entity);
        });
    }

    public void removeFavorit(String id) {
        executor.execute(() -> dao.deleteById(id));
    }

    public void getAllFavorit(ListCallback callback) {
        executor.execute(() -> {
            List<FavoritEntity> list = dao.getAllFavorit();
            mainHandler.post(() -> callback.onResult(list));
        });
    }

    public void getAllFavoritAsEndemik(EndemikListCallback callback) {
        executor.execute(() -> {
            List<FavoritEntity> list = dao.getAllFavorit();
            List<Endemik> result = new java.util.ArrayList<>();

            for (FavoritEntity f : list) {
                Endemik e = new Endemik();
                e.setId(f.getId());
                e.setTipe(f.getTipe());
                e.setNama(f.getNama());
                e.setNamaLatin(f.getNamaLatin());
                e.setDeskripsi(f.getDeskripsi());
                e.setAsal(f.getAsal());
                e.setSebaran(f.getSebaran());
                e.setFoto(f.getFoto());
                e.setSumberFoto(f.getSumberFoto());
                e.setVideo(f.getVideo());
                e.setSumberVideo(f.getSumberVideo());
                e.setStatus(f.getStatus());
                result.add(e);
            }

            mainHandler.post(() -> callback.onResult(result));
        });
    }

    public interface EndemikListCallback {
        void onResult(List<Endemik> list);
    }
}