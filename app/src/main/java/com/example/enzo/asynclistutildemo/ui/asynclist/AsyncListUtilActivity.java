package com.example.enzo.asynclistutildemo.ui.asynclist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ViewGroup;

import com.example.enzo.asynclistutildemo.R;
import com.example.enzo.asynclistutildemo.asyncutilbase.AsyncDataCallback;
import com.example.enzo.asynclistutildemo.asyncutilbase.DataSource;
import com.example.enzo.asynclistutildemo.asyncutilbase.DataViewHolder;
import com.example.enzo.asynclistutildemo.asyncutilbase.RecyclerViewCallback;
import com.example.enzo.asynclistutildemo.db.MyDatabase;
import com.example.enzo.asynclistutildemo.asyncutilbase.RecyclerViewAsyncAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * This activity shows how to use a set of utils extending from {@link android.support.v7.util.AsyncListUtil}, which
 * preloads data in cache during scrolling and fling of a list,  it makes possible that read/show large amounts of data
 * (like a SQL query with 1000k results) without blocking the UI.
 * <p>
 * There are some main classes :
 * <ul>
 * <li>{@link AsyncDataCallback}</li>
 * <li>{@link DataSource}</li>
 * <li>{@link DataViewHolder}</li>
 * <li>{@link RecyclerViewAsyncAdapter}</li>
 * <li>{@link RecyclerViewCallback}</li>
 * </ul>
 *
 * These classes are designed for extensibility and facility, basically, with these classes, you haven't to understand
 * the principe of {@link android.support.v7.util.AsyncListUtil}, but if you do, you can use your own implementation as well.
 * <p>
 *
 * <b>Usage:</b>
 * <ol>
 * <li>Create a class implements {@link DataSource}, and provide your data. (reference: {@link FileSource}</li>
 *
 * <li>Create a ViewHolder implements {@link DataViewHolder}, bind your data in {@link DataViewHolder#bindData(Object)}.
 * (reference: {@link FileViewHolder})</li>
 *
 * <li>Craate a class implements {@link RecyclerViewAsyncAdapter} and inflate a layout and create a ViewHolder in
 * {@link RecyclerViewAsyncAdapter#onCreateViewHolder(ViewGroup, int)}. (reference: {@link FileAsyncAdapter})</li>
 *
 * <li>Set the {@link RecyclerViewAsyncAdapter} you created to a {@link RecyclerView}.</li>
 * </ol>
 *
 * Voila.
 */
public class AsyncListUtilActivity extends AppCompatActivity {
    private static final String TAG = "AsyncListUtilActivity";

    @BindView(R.id.rvAsyncList)
    RecyclerView rvAsyncList;
    private RecyclerViewAsyncAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_list_util);
        ButterKnife.bind(this);

        FileSource source = new FileSource(
                () -> MyDatabase.get().fileDAO().queryGreaterThan50k()
        );
        adapter = new FileAsyncAdapter(source, rvAsyncList, 50);
        rvAsyncList.setLayoutManager(new GridLayoutManager(this, 2));
        rvAsyncList.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        Log.d(TAG, "-----> onStart()");
        super.onStart();
        adapter.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.onStop();
    }
}
