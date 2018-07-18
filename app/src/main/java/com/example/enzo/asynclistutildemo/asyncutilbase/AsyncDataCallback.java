package com.example.enzo.asynclistutildemo.asyncutilbase;

import android.support.v7.util.AsyncListUtil;
import android.util.Log;

public class AsyncDataCallback<T> extends AsyncListUtil.DataCallback<T> {
    private static final String TAG = "AsyncDataCallback";
    private final DataSource<? extends T> dataSource;

    public AsyncDataCallback(DataSource<? extends T> dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * Refresh the data set and return the new data item count.
     * <p>
     * If the data is being accessed through {@link android.database.Cursor} this is where
     * the new cursor should be created.
     */
    @Override
    public int refreshData() {
        Log.d(TAG, "-----> refreshData()");
        return dataSource.refresh();
    }

    @Override
    public void fillData(T[] data, int startPosition, int itemCount) {
        Log.d(TAG, "-----> fillData() start:" + startPosition + " itemCount:" + itemCount);
        for (int i = 0; i < itemCount; i++) {
            if (data[i] == null) {
                data[i] = dataSource.getItem(startPosition + i);
            }
        }
    }

    @Override
    public void recycleData(T[] data, int itemCount) {
        Log.d(TAG, "----> recycleData() itemCount:" + itemCount);
        for (int i = 0; i < itemCount; i++) {
            data[i] = null;
        }
    }

    public void closeDataSource() {
        dataSource.close();
    }
}
