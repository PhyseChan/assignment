package com.example.assignment.viewModel;

import com.example.assignment.AppContext;
import com.example.assignment.RouteBean;
import com.example.assignment.TripBean;
import com.example.assignment.base.BaseViewModel;
import com.example.assignment.db.MapRouteDataBase;
import com.example.assignment.db.TripDataBase;

import java.util.ArrayList;
import java.util.List;

public class TripViewModel extends BaseViewModel {
    public void addData(TripBean tripBean) {
        TripDataBase.getDatabase(AppContext.getInstance()).getTripDao().addTrip(tripBean);
    }

    public List<TripBean> requestAllTrip() {
        return TripDataBase.getDatabase(AppContext.getInstance()).getTripDao().getAllTrip();
    }

    public List<TripBean> requestTripByTitle(String title) {
        return TripDataBase.getDatabase(AppContext.getInstance()).getTripDao().getTripByTitle(title);
    }
    public TripBean getLastTrip(){
        List<TripBean> triplist = requestAllTrip();
        return triplist.get(triplist.size()-1);
    }
}
