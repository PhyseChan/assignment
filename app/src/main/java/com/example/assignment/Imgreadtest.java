package com.example.assignment;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.location.LocationManager;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;

import com.example.assignment.db.PicDao;
import com.example.assignment.db.PicDataBase;
import com.example.assignment.utils.LocationUtil;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
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
        img_v = (ImageView) findViewById(R.id.readimagandshow);
        setSupportActionBar(toolbar);

        Button fab = findViewById(R.id.testreadimgbtn);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ExifInterface exif;
                String tag_gps_latitude = "";
                String tag_gps_Longitude = "";
                List<PicBean> livedatalist = picDao.getallPics();
                for (PicBean pic : livedatalist) {
                    System.out.println(pic.toString());
                    System.out.println("latitude:"+pic.getLatitude()+" longitude:"+pic.getLongitude());
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