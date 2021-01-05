package com.example.assignment;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "RouteBean")
public class RouteBean {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "primaryId")
    private Integer primaryId;

    @ColumnInfo(name = "Id")
    private Integer Id;

    @ColumnInfo(name = "Latitude")
    private double Latitude;

    @ColumnInfo(name = "Longitude")
    private double Longitude;

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public double getLatitude() {
        return Latitude;
    }

    public void setLatitude(double latitude) {
        Latitude = latitude;
    }

    public double getLongitude() {
        return Longitude;
    }

    public void setLongitude(double longitude) {
        Longitude = longitude;
    }

    @NonNull
    public Integer getPrimaryId() {
        return primaryId;
    }

    public void setPrimaryId(@NonNull Integer primaryId) {
        this.primaryId = primaryId;
    }

    @Override
    public String toString() {
        return "RouteBean{" +
                "Id=" + Id +
                ", Latitude=" + Latitude +
                ", Longitude=" + Longitude +
                '}';
    }
}
