package com.example.assignment;

import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;

import com.example.assignment.db.PicDao;
import com.example.assignment.db.PicDataBase;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.LiveData;
import androidx.room.Room;

import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Imgreadtest extends AppCompatActivity {
    File phonefile;
    ImageView img_v;
    PicDataBase picDataBase;
    PicDao picDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        databaseinit();
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = findViewById(R.id.toolbar);
        img_v = (ImageView)findViewById(R.id.readimagandshow);
        setSupportActionBar(toolbar);

        Button fab = findViewById(R.id.testreadimgbtn);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ExifInterface exif;
                String tag_gps_latitude = "";
                String tag_gps_Longitude = "";
                System.out.println("click button to print pic");
                List<PicBean> livedatalist = picDao.getallPics();
                for (PicBean pic:livedatalist) {
                    try {
                        exif = new ExifInterface(pic.getUrl());
                        tag_gps_latitude = exif.getAttribute(ExifInterface.TAG_GPS_ALTITUDE);
                        tag_gps_Longitude = exif.getAttribute(ExifInterface.TAG_GPS_LONGITUDE);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println(pic.toString());
                    System.out.println("latitude:"+tag_gps_latitude+" longitude:"+tag_gps_Longitude);
                }
                System.out.println("end click button to print pic");
            }
        });
    }


    public void databaseinit(){
        picDataBase = PicDataBase.getDatabase(AppContext.getInstance());
        picDao = picDataBase.getUserDao();
    }
}