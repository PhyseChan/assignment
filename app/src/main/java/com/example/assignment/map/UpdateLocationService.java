package com.example.assignment.map;


import android.Manifest;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;

import com.example.assignment.RouteBean;
import com.example.assignment.viewModel.RouteViewModel;

/**
 * @Author: Qibin Liang
 * @Time: 2020-1-4
 * @version: 1.0
 * @ClassName: UpdateLocationService
 * @Description: this service will address the location of user.
 **/
public class UpdateLocationService extends Service {
    private LocationManager mLocationManager;
    private int tripid;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        tripid = intent.getIntExtra("tripId",1);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Location location = mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        //update the location every 2 seconds or 50 meters.
        mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000, 50, new LocationListener() {
            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderDisabled(@NonNull String provider) {

            }

            @Override
            public void onLocationChanged(@NonNull Location location) {
                // if user's location is changed, it will record the current location.
                RouteBean routeBean = new RouteBean();
                routeBean.setId(tripid);
                routeBean.setLatitude(location.getLatitude());
                routeBean.setLongitude(location.getLongitude());
                new RouteViewModel().addData(routeBean);
            }
        });

    }
}
