package com.example.enzo.asynclistutildemo.ui.asynclist;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.enzo.asynclistutildemo.R;
import com.example.enzo.asynclistutildemo.db.FilePo;
import com.example.enzo.asynclistutildemo.asyncutilbase.RecyclerViewAsyncAdapter;
import com.example.enzo.asynclistutildemo.asyncutilbase.DataSource;

public class FileAsyncAdapter extends RecyclerViewAsyncAdapter<FilePo, FileViewHolder> {
    public FileAsyncAdapter(DataSource<FilePo> dataSource, RecyclerView recyclerView, int tileSize) {
        super(FilePo.class, dataSource, recyclerView, tileSize);
    }

    @NonNull
    @Override
    public FileViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.file_item_list, parent, false);
        return new FileViewHolder(v);
    }
}
