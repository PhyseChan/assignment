package com.example.assignment.map;

import com.example.assignment.PicBean;
import com.example.assignment.viewModel.PicViewModel;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.HashMap;
import java.util.List;

/**
 * @Author: Qibin Liang
 * @Time: 2020-12-30
 * @version: 1.0
 * @ClassName: MapMarkersGenerator
 * @Description: This class is used to read pictures' details from SQLite and put the information into the markers.
 * **/
public class MapMarkersGeneratorImpl implements MapMarkersGenerator{

    MarkerOptions markerOptions = new MarkerOptions();

    /**
     * @methodName: addMarkers
     * @param googleMap
     * @return void
     * @Description: bind markers to the googleMap.
     * **/
    @Override
    public void addMarkers(GoogleMap googleMap) {
        List<PicBean> beanlist = new PicViewModel().requestDataNotlive();
        for (PicBean pic : beanlist) {
            LatLng piclocation = new LatLng(pic.getLatitude(),pic.getLongitude());
            markerOptions.position(piclocation);
            markerOptions.title(pic.getUrl());
            Marker marker = googleMap.addMarker(markerOptions);
            HashMap<String, String> infoValues = new HashMap<>();
            infoValues.put("picUrl",pic.getUrl());
            infoValues.put("description", pic.getDes());
            marker.setTag(infoValues);
        }
    }
}
