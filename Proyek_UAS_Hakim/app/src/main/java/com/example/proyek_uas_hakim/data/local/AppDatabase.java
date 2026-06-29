package com.example.proyek_uas_hakim.data.local;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {FavoritEntity.class, EndemikEntity.class}, version = 2, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract FavoritDao favoritDao();
    public abstract EndemikDao endemikDao();

    private static volatile AppDatabase instance;

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "endemikdb.db")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}