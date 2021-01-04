package com.example.assignment.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.assignment.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

import java.util.HashMap;
/**
 * @Author: Qibin Liang
 * @Time: 2020-12-30
 * @version: 1.0
 * @ClassName: MapPicInfoAdapter
 * @Description: This is infoWindowAdapter used to customize a information window of map markers to show details of markers
 * **/
public class MapPicInfoAdapter implements GoogleMap.InfoWindowAdapter {

    private View detailsInfoView;
    private Context context;

    /**
     * Class Constructor
     * @methodName: MapPicInfoAdapter
     * @param context  A context who shows the information window.
     * **/
    public MapPicInfoAdapter(Context context){
        this.context = context;
        detailsInfoView = ((Activity)context).getLayoutInflater().inflate(R.layout.map_pic_info, null);
    }

    @Override
    public View getInfoWindow(Marker marker){
        return null;
    }

    /**
     * @methodName: getInfoContents
     * @param marker
     * @return View
     * @Description: return a infoWindow View whose views are already set.
     * **/
    @Override
    public View getInfoContents(Marker marker) {
        HashMap<String, String> extraValues = (HashMap<String, String>) marker.getTag();

        ImageView detailsInfoPicPath = detailsInfoView.findViewById(R.id.map_pic_details);
        TextView detailsInfoTitle = detailsInfoView.findViewById(R.id.map_pic_title);
        TextView detailsInfoDes = detailsInfoView.findViewById(R.id.map_pic_description);

        detailsInfoPicPath.setImageBitmap(BitmapFactory.decodeFile(extraValues.get("picUrl")));
        detailsInfoTitle.setText("Title:" + extraValues.get("picUrl"));
        detailsInfoDes.setText("Description:" + extraValues.get("description"));

        return detailsInfoView;
    }
}
