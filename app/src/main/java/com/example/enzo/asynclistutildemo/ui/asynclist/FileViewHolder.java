package com.example.enzo.asynclistutildemo.ui.asynclist;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.enzo.asynclistutildemo.R;
import com.example.enzo.asynclistutildemo.db.FilePo;
import com.example.enzo.asynclistutildemo.asyncutilbase.DataViewHolder;
import com.example.enzo.asynclistutildemo.util.FileSizeFormatter;

import java.text.SimpleDateFormat;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FileViewHolder extends DataViewHolder<FilePo> {
    private static final SimpleDateFormat sFormatter = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
    @BindView(R.id.imgFileIcon) ImageView imgIcon;
    @BindView(R.id.txtFileName) TextView txtFileName;
    @BindView(R.id.txtPath) TextView txtPath;
    @BindView(R.id.txtFileSize) TextView txtFileSize;
    @BindView(R.id.txtAuthor) TextView txtAuthor;
    @BindView(R.id.txtDate) TextView txtDate;

    public FileViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bindData(FilePo data) {
        if (imgIcon.getDrawable() == null) {
            imgIcon.setImageResource(R.drawable.ic_pdf);
        }

        if (data == null) {
            txtFileName.setText("Loading...");
            txtPath.setText("...");
            txtAuthor.setText("...");
            txtFileSize.setText("...");
            txtDate.setText("...");
        } else {
            txtFileName.setText(data.fileName);
            txtPath.setText(data.path);
            txtAuthor.setText(data.author);
            txtFileSize.setText(FileSizeFormatter.format(data.length));
            txtDate.setText(sFormatter.format(data.createdAt));
        }
    }
}
