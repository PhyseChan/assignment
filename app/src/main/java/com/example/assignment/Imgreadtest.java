package com.example.assignment;

import android.content.Intent;
import android.os.Bundle;

import com.example.assignment.db.PicDao;
import com.example.assignment.db.PicDataBase;
import com.example.assignment.map.UpdateLocationService;
import com.example.assignment.viewModel.RouteViewModel;
import com.example.assignment.viewModel.TripViewModel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.util.List;

public class Imgreadtest extends AppCompatActivity {
    File phonefile;
    ImageView img_v;
    PicDataBase picDataBase;
    PicDao picDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = findViewById(R.id.toolbar);
        img_v = (ImageView) findViewById(R.id.readimagandshow);
        setSupportActionBar(toolbar);

        Button fab = findViewById(R.id.testreadimgbtn);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TripViewModel tripViewModel = new TripViewModel();
                System.out.println(tripViewModel.getLastTrip().toString());
                List<RouteBean> routelist = new RouteViewModel().requestDataNotlive(tripViewModel.getLastTrip().getId());
                for (RouteBean routeBean : routelist) {
                    System.out.println(routeBean.toString());
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