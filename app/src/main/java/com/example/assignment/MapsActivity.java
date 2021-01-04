package com.example.assignment;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LiveData;
import androidx.room.Room;

import android.os.Bundle;

import com.example.assignment.adapter.MapPicInfoAdapter;
import com.example.assignment.db.PicDao;
import com.example.assignment.db.PicDataBase;
import com.example.assignment.map.MapMarkersGenerator;
import com.example.assignment.map.MapMarkersGeneratorImpl;
import com.example.assignment.viewModel.PicViewModel;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

/**
 * @Author: Qibin Liang
 * @Time: 2020-12-30
 * @version: 1.0
 * @ClassName: MapsActivity
 * @Description: This is Map activity.
 * **/
public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener {

    private GoogleMap mMap;
    private PicDataBase picDataBase;
    private PicDao picDao;

    /**
     * @methodName: onCreate
     * @param savedInstanceState
     * @return void
     * @Description: it will request the PicBean data when the activity is created.
     * **/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        LiveData<List<PicBean>> piclist = new PicViewModel().requestData();
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */

    /**
     * @methodName: onMapReady
     * @param googleMap
     * @return void
     * @Description: both adding markers and create infoWindows are implement when the Map is ready.
     * **/
    @Override
    public void onMapReady(GoogleMap googleMap) throws SecurityException {
        mMap = googleMap;
        mMap.setMyLocationEnabled(true);
        mMap.setOnInfoWindowClickListener(this);
        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        // add markers to the googleMap
        new MapMarkersGeneratorImpl().addMarkers(mMap);
        // set the information window
        mMap.setInfoWindowAdapter(new MapPicInfoAdapter(this));
    }

    @Override
    public void onInfoWindowClick(Marker marker) {

    }
}