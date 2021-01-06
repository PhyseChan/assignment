package com.example.assignment.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.assignment.PicBean;
import com.example.assignment.RouteBean;

import java.util.List;
/**
 * @Author: Qibin Liang
 * @methodName: MapRouteDao
 * @Description: MapRoute Dao
 * **/
@Dao
public interface MapRouteDao {

    @Query("SELECT * FROM RouteBean where id = (:id) ")
    List<RouteBean> getroutes(int id);

    @Insert
    void addLocation(RouteBean routeBean);
}
