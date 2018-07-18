package com.example.enzo.asynclistutildemo.db;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

@Entity
public class FilePo {
    @PrimaryKey(autoGenerate = true)
    public long uid;

    public String fileName;

    public String path;

    public String author;

    public Date createdAt;

    public Date modifiedAt;

    public long length;

    @Override
    public String toString() {
        return String.format("%s %s", fileName, path);
    }
}
