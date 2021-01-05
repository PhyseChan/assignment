package com.example.assignment.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.assignment.PicBean;
import com.example.assignment.RouteBean;

import java.util.List;

@Dao
public interface MapRouteDao {
    /**
     * @Author: Qibin Liang
     * @methodName: getallPics
     * @return List<PicBean>
     * @Description: select all pictures
     * **/
    @Query("SELECT * FROM RouteBean where id = (:id) ")
    List<RouteBean> getroutes(int id);

    @Insert
    void addLocation(RouteBean routeBean);
}
