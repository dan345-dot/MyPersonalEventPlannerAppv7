package com.example;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


@Database(entities = {Event.class}, version = 1, exportSchema = false)
public abstract class RoomDB extends RoomDatabase {

    private static RoomDB database;

    private static final String DATABASE_NAME = "EventApp";

    // Singleton database instance
    public static synchronized RoomDB getInstance(Context context) {

        if (database == null) {

            database = Room.databaseBuilder(
                            context.getApplicationContext(),
                            RoomDB.class,
                            DATABASE_NAME
                    )
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }

        return database;
    }


    public abstract EventDao eventDao();
}