package com.example.enzo.asynclistutildemo.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

@Database(entities = {FilePo.class}, version = 1, exportSchema = false)
@TypeConverters({DateConvertor.class})
public abstract class MyDatabase extends RoomDatabase {
    private static MyDatabase sInstance;

    public static void init(Context context) {
        Context applicationContext = context.getApplicationContext() != null
                ? context.getApplicationContext()
                : context;
        if (sInstance == null) {
            sInstance = Room.databaseBuilder(applicationContext, MyDatabase.class, "MyDatabase")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
    }

    public static MyDatabase get() {
        if (sInstance != null) {
            return sInstance;
        } else {
            throw new RuntimeException("MyDatabase has not been initialized.");
        }
    }

    public abstract FileDao fileDAO();
}
