package com.example.enzo.asynclistutildemo.asyncutilbase;

import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.v7.util.AsyncListUtil;
import android.support.v7.widget.RecyclerView;

public abstract class RecyclerViewAsyncAdapter<T, VH extends DataViewHolder<T>> extends RecyclerView.Adapter<VH> {
    private final AsyncDataCallback<T> dataCallback;
    private final AsyncListUtil<T> listUtil;
    private final RecyclerView.OnScrollListener onScrollListener;
    private final RecyclerView boundRecyclerView;

    public RecyclerViewAsyncAdapter(Class<T> dataClass, DataSource<T> dataSource, RecyclerView recyclerView, int tileSize) {
        dataCallback = new AsyncDataCallback<>(dataSource);
        listUtil = new AsyncListUtil<>(dataClass, tileSize, dataCallback, new RecyclerViewCallback(recyclerView));
        onScrollListener = new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                listUtil.onRangeChanged();
            }
        };
        boundRecyclerView = recyclerView;
    }

    @CallSuper
    public void onStart() {
        if (boundRecyclerView != null) {
            boundRecyclerView.addOnScrollListener(onScrollListener);
//            listUtil.refresh();
        }
    }

    @CallSuper
    public void onStop() {
        if (boundRecyclerView != null) {
            boundRecyclerView.removeOnScrollListener(onScrollListener);
            dataCallback.closeDataSource();
        }
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        holder.bindData(listUtil.getItem(position));
    }

    @CallSuper
    @Override
    public int getItemCount() {
        return listUtil.getItemCount();
    }
}
