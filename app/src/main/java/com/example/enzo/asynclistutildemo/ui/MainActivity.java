package com.example.enzo.asynclistutildemo.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.enzo.asynclistutildemo.R;
import com.example.enzo.asynclistutildemo.ui.asynclist.AsyncListUtilActivity;
import com.example.enzo.asynclistutildemo.ui.injectdata.DatabaseSimActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btnGenerateData).setOnClickListener(v -> {
            Intent intent = new Intent(this, DatabaseSimActivity.class);
            startActivity(intent);
        });

        findViewById(R.id.btnDemo).setOnClickListener(v -> {
            Intent intent = new Intent(this, AsyncListUtilActivity.class);
            startActivity(intent);
        });
    }
}
