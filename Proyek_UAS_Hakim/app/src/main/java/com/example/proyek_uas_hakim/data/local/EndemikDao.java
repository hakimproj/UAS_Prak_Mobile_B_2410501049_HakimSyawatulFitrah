package com.example.proyek_uas_hakim.data.local;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface EndemikDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<EndemikEntity> list);

    @Query("SELECT * FROM endemik")
    List<EndemikEntity> getAll();

    @Query("SELECT * FROM endemik WHERE tipe = :tipe")
    List<EndemikEntity> getByTipe(String tipe);

    @Query("SELECT DISTINCT asal FROM endemik WHERE asal IS NOT NULL ORDER BY asal ASC")
    List<String> getAllRegions();
}