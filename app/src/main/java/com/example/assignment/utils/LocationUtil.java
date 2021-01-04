package com.example.assignment.utils;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.media.ExifInterface;

import androidx.core.app.ActivityCompat;
import com.example.assignment.PicBean;

import java.io.IOException;

/**
 * @Author: Qibin Liang
 * @Time: 2020-1-2
 * @version: 1.0
 * @ClassName: LocationUtil
 * @Description: This class is used to request the current location and bind the location data to the PicBean.
 * **/
public class LocationUtil {
    private ExifInterface exif;
    private String tag_gps_Latitude;
    private String tag_gps_Longitude;

    /**
     * @methodName: getInfoContents
     * @param pic the picture taken in the moment.
     * @param context the Activity currently running
     * @return PicBean a PicBean contains location data.
     * @Description: bind the location to the picture taken in the moment and return it.
     * **/
    public PicBean setlocation(PicBean pic,Context context){
        try {
            exif = new ExifInterface(pic.getUrl());
            tag_gps_Latitude = exif.getAttribute(ExifInterface.TAG_GPS_ALTITUDE);
            tag_gps_Longitude = exif.getAttribute(ExifInterface.TAG_GPS_LONGITUDE);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return pic;
        }
        Location location = getlocationmanager(context).getLastKnownLocation(LocationManager.GPS_PROVIDER);
        pic.setLatitude(location.getLatitude());
        pic.setLongitude(location.getLongitude());
        return pic;
    }

    /**
     * @methodName: getlocationmanager
     * @param context the Activity currently running
     * @return LocationManager return the LocationManager so tha location data can be obtained from it.
     * @Description: get the current location
     * **/
    public static LocationManager getlocationmanager(Context context){
        String serviceName = Context.LOCATION_SERVICE;
        LocationManager locationManager = (LocationManager)context.getSystemService(serviceName);
        return locationManager;
    }
}