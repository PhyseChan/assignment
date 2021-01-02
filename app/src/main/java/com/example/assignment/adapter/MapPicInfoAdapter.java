package com.example.assignment.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.assignment.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

import java.util.HashMap;
/**
 * @Author: Qibin Liang
 * **/
public class MapPicInfoAdapter implements GoogleMap.InfoWindowAdapter {

    private View detailsInfoView;
    private Context context;


    public MapPicInfoAdapter(Context context){
        this.context = context;
        detailsInfoView = ((Activity)context).getLayoutInflater().inflate(R.layout.map_pic_info, null);
    }

    @Override
    public View getInfoWindow(Marker marker){
        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {
        HashMap<String, String> extraValues = (HashMap<String, String>) marker.getTag();

        ImageView detailsInfoDetails = detailsInfoView.findViewById(R.id.map_pic_details);
        TextView detailsInfoTitle = detailsInfoView.findViewById(R.id.map_pic_title);
        TextView detailsInfoDesc = detailsInfoView.findViewById(R.id.map_pic_description);



        return detailsInfoView;
    }
}
