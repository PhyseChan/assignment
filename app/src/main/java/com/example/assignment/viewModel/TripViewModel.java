package com.example.assignment.viewModel;

import com.example.assignment.AppContext;
import com.example.assignment.TripBean;
import com.example.assignment.base.BaseViewModel;
import com.example.assignment.db.TripDataBase;

import java.util.List;

/**
 * @Author: Qibin Liang
 * @Time: 2020-1-4
 * @version: 1.0
 * @ClassName: TripViewModel
 * @Description: ViewModel of Trip class
 * **/
public class TripViewModel extends BaseViewModel {

    /**
     * @methodName: addData
     * @param tripBean
     * @return void
     * @Description: insert a tripBean object into table(TripBean)
     * **/
    public void addData(TripBean tripBean) {
        TripDataBase.getDatabase(AppContext.getInstance()).getTripDao().addTrip(tripBean);
    }

    /**
     * @methodName: requestAllTrip
     * @return List<TripBean>
     * @Description: get all trips data.
     * **/
    public List<TripBean> requestAllTrip() {
        return TripDataBase.getDatabase(AppContext.getInstance()).getTripDao().getAllTrip();
    }

    /**
     * @methodName: requestTripByTitle
     * @param title
     * @return List<TripBean>
     * @Description: search TripBean data by trip title.
     * **/
    public List<TripBean> requestTripByTitle(String title) {
        return TripDataBase.getDatabase(AppContext.getInstance()).getTripDao().getTripByTitle(title);
    }

    /**
     * @methodName: getLastTrip
     * @return TripBean
     * @Description: get the latest trip data.
     * **/
    public TripBean getLastTrip(){
        List<TripBean> triplist = requestAllTrip();
        return triplist.get(triplist.size()-1);
    }

}
