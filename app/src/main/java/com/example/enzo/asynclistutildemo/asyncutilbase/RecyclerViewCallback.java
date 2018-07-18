package com.example.enzo.asynclistutildemo.asyncutilbase;

import android.support.v7.util.AsyncListUtil;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

public class RecyclerViewCallback extends AsyncListUtil.ViewCallback {
    private final static String TAG = "RecyclerViewCallback";
    private final RecyclerView recyclerView;

    public RecyclerViewCallback(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
    }

    @Override
    public void getItemRangeInto(int[] outRange) {
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof LinearLayoutManager) {
            // Since GridLayoutManager extends LinearLayoutManager, so both cases included.
            outRange[0] = ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
            outRange[1] = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
            if (outRange[0] == -1) {
                outRange[0] = 0;
            }
            if (outRange[1] == -1) {
                outRange[1] = 0;
            }
            Log.d(TAG, "getItemRangeInto:" + outRange[0] + ", " + outRange[1]);
        } else {
            throw new RuntimeException("LayoutManager of RecyclerView is neither " +
                    "LinearLayoutManager or GridLayoutManager, positions of first and last " +
                    "visible items cannot be got.");
        }
    }

    @Override
    public void onDataRefresh() {
        Log.d(TAG, "onDataRefresh");
        getRecyclerViewAdapter().notifyDataSetChanged();
    }

    @Override
    public void onItemLoaded(int position) {
        getRecyclerViewAdapter().notifyItemChanged(position);
    }

    private RecyclerView.Adapter getRecyclerViewAdapter() {
        if (recyclerView.getAdapter() != null) {
            return recyclerView.getAdapter();
        } else {
            throw new RuntimeException("There is no adapter bound to the RecyclerView.");
        }
    }
}
