package com.example.assignment.db;

import androidx.room.ColumnInfo;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.assignment.PicBean;
import com.example.assignment.TripBean;

import java.util.List;

@Dao
public interface TripDao {

    @Query("Select * from TripBean where tripTitle = (:tripTitle)")
    List<TripBean> getTripByTitle(String tripTitle);

    @Query("Select * from TripBean")
    List<TripBean> getAllTrip();

    @Insert
    void addTrip(TripBean tripBean);
}
