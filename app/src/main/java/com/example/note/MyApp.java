package com.example.note;

import android.app.Application;

import androidx.room.Room;

public class MyApp extends Application {
    public static AppDB db;

    @Override
    public void onCreate() {
        super.onCreate();
        db = Room.databaseBuilder(getApplicationContext(),
                AppDB.class,"mahasiswa").allowMainThreadQueries().build();
    }

}
