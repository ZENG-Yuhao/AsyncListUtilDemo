package com.example.enzo.asynclistutildemo.ui.injectdata;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.enzo.asynclistutildemo.R;
import com.example.enzo.asynclistutildemo.db.MyDatabase;
import com.example.enzo.asynclistutildemo.db.FilePo;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class DatabaseSimActivity extends AppCompatActivity {
    @BindView(R.id.txtContent)
    TextView txtContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database_sim);
        ButterKnife.bind(this);
    }

    @SuppressLint("CheckResult")
    @OnClick(R.id.btnFakeData)
    protected void generateFakeData() {
        Date today = new Date();
        Observable
                .create((ObservableOnSubscribe<Long>) emitter -> {
                    Log.d("TAG", "Start injecting...");
                    long num = 100_000L;
                    for (long i = 0; i < num; i++) {
                        FilePo file = new FilePo();
                        file.author = "Author " + i;
                        file.fileName = "File " + i;
                        file.length = num * 100;
                        file.createdAt = today;
                        file.modifiedAt = today;
                        file.path = "/storage/emulated/0/Android/data/Document" + i;
                        Log.d("TAG", "Saving file " + i + " to DB");
                        MyDatabase.get().fileDAO().insertFile(file);
                        emitter.onNext(i);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        i -> {
                            txtContent.setText("File " + i + " added to DB.");
                        },
                        throwable -> {
                            Toast.makeText(this, throwable.toString(), Toast.LENGTH_SHORT).show();
                        }
                );
    }
}
