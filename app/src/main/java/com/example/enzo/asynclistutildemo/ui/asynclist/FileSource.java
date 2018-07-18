package com.example.enzo.asynclistutildemo.ui.asynclist;

import android.database.Cursor;

import com.example.enzo.asynclistutildemo.db.FilePo;
import com.example.enzo.asynclistutildemo.asyncutilbase.DataSource;

import java.util.Date;

public class FileSource implements DataSource<FilePo> {
    private Cursor cursor;
    private CursorProvider provider;

    public FileSource(CursorProvider provider) {
        this.provider = provider;
    }

    @Override
    public int refresh() {
        cursor = provider.provideCursor();
        return cursor.getCount();
    }

    @Override
    public FilePo getItem(int position) {
        cursor.moveToPosition(position);
        FilePo file = new FilePo();
        file.uid = cursor.getLong(0);
        file.fileName = cursor.getString(1);
        file.path = cursor.getString(2);
        file.author = cursor.getString(3);
        file.createdAt = new Date(cursor.getLong(4));
        file.modifiedAt = new Date(cursor.getLong(5));
        file.length = cursor.getLong(6);
        return file;
    }

    @Override
    public void close() {
        cursor.close();
    }

    @FunctionalInterface
    interface CursorProvider {
        Cursor provideCursor();
    }
}
