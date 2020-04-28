package com.example.hairchange;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.util.ArrayList;

public class PhotoViewActivity extends AppCompatActivity {
    private static final String TAG = "PhotoViewActivity";

    private ImageItemAdapter adapter;
    private RecyclerView recyclerView;
    private Button man;
    private Button woman;
    private Button theOthers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photoview);

        // get Intent data
        String photoUri = getIntent().getExtras().getString("PhotoUri");
        Log.d(TAG, "photoUri : " + photoUri);

        getImageFromURI(photoUri);  // uri 로 부터 이미지가져오기

        // Set Hair style
        man = findViewById(R.id.man);
        woman = findViewById(R.id.woman);
        theOthers = findViewById(R.id.the_others);
        man.setSelected(true);

        recyclerView = findViewById(R.id.recycler1);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        adapter = new ImageItemAdapter();

        adapter.addItem(R.drawable.man_raised1);
        adapter.addItem(R.drawable.man_raised2);

        recyclerView.setAdapter(adapter);
    }

    private void getImageFromURI(String photoUri) {
        ImageView photo = (ImageView)findViewById(R.id.photo);
        photoUri = photoUri.replace("file://", "");
        File imgFile = new File(photoUri);
        if (imgFile.exists()) {
            Bitmap bitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            Log.i("abPath", bitmap.toString());
            photo.setImageBitmap(bitmap);
        }
    }

    public void btnClick(View view) {
        adapter = new ImageItemAdapter();
        view.setSelected(true);
        if(view.getId() == R.id.man) {
            woman.setSelected(false);
            theOthers.setSelected(false);

            adapter.addItem(R.drawable.man_raised1);
            adapter.addItem(R.drawable.man_raised2);
        } else if (view.getId() == R.id.woman) {
            man.setSelected(false);
            theOthers.setSelected(false);

            adapter.addItem(R.drawable.woman_blond_long);
        } else if (view.getId() == R.id.the_others) {
            man.setSelected(false);
            woman.setSelected(false);

            adapter.addItem(R.drawable.man_raised2);
            adapter.addItem(R.drawable.man_raised2);
            adapter.addItem(R.drawable.man_raised2);
            adapter.addItem(R.drawable.man_raised2);
            adapter.addItem(R.drawable.man_raised2);
            adapter.addItem(R.drawable.man_raised2);
            adapter.addItem(R.drawable.man_raised2);
            adapter.addItem(R.drawable.man_raised2);
        }

        recyclerView.setAdapter(adapter);
    }
}
