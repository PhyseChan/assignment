package com.example.assignment.viewModel;

import androidx.lifecycle.LiveData;

import com.example.assignment.AppContext;
import com.example.assignment.PicBean;
import com.example.assignment.RouteBean;
import com.example.assignment.base.BaseViewModel;
import com.example.assignment.db.MapRouteDataBase;
import com.example.assignment.db.PicDataBase;

import java.util.List;

public class RouteViewModel extends BaseViewModel {

    public void addData(RouteBean routeBean) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                MapRouteDataBase.getDatabase(AppContext.getInstance()).getMapRouteDao().addLocation(routeBean);
            }
        }).start();

    }

    public List<RouteBean> requestDataNotlive(int id) {
        return MapRouteDataBase.getDatabase(AppContext.getInstance()).getMapRouteDao().getroutes(id);

    }
}
