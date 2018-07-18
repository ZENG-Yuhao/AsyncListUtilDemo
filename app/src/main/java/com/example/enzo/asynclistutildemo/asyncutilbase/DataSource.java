package com.example.enzo.asynclistutildemo.asyncutilbase;

public interface DataSource<T> {
    /**
     * Refresh the data set and return the new data item count.
     * <p>
     * If the data is being accessed through {@link android.database.Cursor} this is where
     * the new cursor should be created.
     */
    int refresh();

    T getItem(int position);

    void close();
}
