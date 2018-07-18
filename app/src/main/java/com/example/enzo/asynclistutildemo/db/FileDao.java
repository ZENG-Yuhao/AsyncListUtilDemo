package com.example.enzo.asynclistutildemo.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.database.Cursor;

import java.util.List;

@Dao
public interface FileDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertFile(FilePo file);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertFiles(List<FilePo> files);

    @Query("SELECT * FROM FilePo LIMIT 50")
    List<FilePo> query();

    @Query("SELECT * FROM FilePo")
    Cursor queryAll();

    @Query("SELECT * FROM FilePo WHERE uid>50000")
    Cursor queryGreaterThan50k();
}
