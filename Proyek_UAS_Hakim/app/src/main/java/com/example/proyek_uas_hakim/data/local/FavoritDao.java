package com.example.proyek_uas_hakim.data.local;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface FavoritDao {

    @Insert
    void insert(FavoritEntity favorit);

    @Delete
    void delete(FavoritEntity favorit);

    @Query("SELECT * FROM favorit")
    List<FavoritEntity> getAllFavorit();

    @Query("SELECT EXISTS(SELECT 1 FROM favorit WHERE id = :id)")
    boolean isFavorit(String id);

    @Query("DELETE FROM favorit WHERE id = :id")
    void deleteById(String id);
}