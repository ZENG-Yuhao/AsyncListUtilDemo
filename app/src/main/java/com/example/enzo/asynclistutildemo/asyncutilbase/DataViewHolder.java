package com.example.enzo.asynclistutildemo.asyncutilbase;

import android.support.v7.widget.RecyclerView;
import android.view.View;

public abstract class DataViewHolder<T> extends RecyclerView.ViewHolder {
    public DataViewHolder(View itemView) {
        super(itemView);
    }

    public abstract void bindData(T data);
}
