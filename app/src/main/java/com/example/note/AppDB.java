package com.example.note;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Mahasiswa.class}, version = 1, exportSchema = false)
public abstract class AppDB extends RoomDatabase {
    public abstract MahasiswaDao userDao();
}