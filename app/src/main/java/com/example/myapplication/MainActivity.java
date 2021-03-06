package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import com.chaquo.python.Kwarg;
import com.chaquo.python.PyObject;
import com.chaquo.python.android.AndroidPlatform;
import com.chaquo.python.Python;
import com.wang.avi.AVLoadingIndicatorView;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ContentUris;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;




public class MainActivity extends AppCompatActivity {
    static final String TAG = "PythonOnAndroid";


    public static final int TAKE_PHOTO = 1;
    public static final int CHOOSE_PHOTO = 2;
    private ImageView picture;
    private Intent intent1, intent2;
    private AVLoadingIndicatorView avLoadingIndicatorView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        avLoadingIndicatorView = (AVLoadingIndicatorView) findViewById(R.id.avi);
        avLoadingIndicatorView.show();
        Button chooseFromAlbum = findViewById(R.id.choose_from_album);
        chooseFromAlbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, CHOOSE_PHOTO);
                } else {
                    openAlbum();//??????album?????????
                }
            }
        });
        Button takePhoto = findViewById(R.id.take_photo);
        picture = findViewById(R.id.picture);

        takePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // ??????????????????
                if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CAMERA}, TAKE_PHOTO);
                } else {
                    // ??????????????????
                    startCamera();
                }
            }
        });
        intent1 = new Intent(this, Albums.class);//???????????????Albums??????????????????Intent
        intent2 = new Intent(this, Camera.class);//???????????????Camera??????????????????Intent

    }

    private void openAlbum() {
        startActivity(intent1);//??????album???????????????
    }

    private void startCamera() {
        startActivity(intent2);//??????camera???????????????
        //Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        // ??????????????????????????????imageUri
        //mPhotoPath = getSDPath()+"/"+ getPhotoFileName();//???????????????????????????getSDPath()???getPhotoFileName()?????????????????????
        //intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        //startActivityForResult(intent, TAKE_PHOTO);
    }





}




