package com.example.assignment.viewModel;

import com.example.assignment.AppContext;
import com.example.assignment.RouteBean;
import com.example.assignment.base.BaseViewModel;
import com.example.assignment.db.MapRouteDataBase;

import java.util.List;

/**
 * @Author: Qibin Liang
 * @Time: 2020-1-4
 * @version: 1.0
 * @ClassName: RouteViewModel
 * @Description: ViewModel of Route class
 * **/
public class RouteViewModel extends BaseViewModel {
    /**
     * @methodName: addData
     * @param routeBean
     * @return void
     * @Description: insert a RouteBean object into the table(RouteBean).
     * **/
    public void addData(RouteBean routeBean) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                MapRouteDataBase.getDatabase(AppContext.getInstance()).getMapRouteDao().addLocation(routeBean);
            }
        }).start();

    }
    /**
     * @methodName: addData
     * @param id
     * @return List<RouteBean>
     * @Description: search RouteBean data by trip id.
     * **/
    public List<RouteBean> requestDataNotlive(int id) {
        return MapRouteDataBase.getDatabase(AppContext.getInstance()).getMapRouteDao().getroutes(id);

    }
}
