package com.example.assignment;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;



@Entity(tableName = "Pic")
public class PicBean {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "Id")
    private Integer id;

    @ColumnInfo(name = "url")
    private String url;

    @ColumnInfo(name = "des")
    private String des;

    @ColumnInfo(name = "Latitude")
    private double Latitude;

    @ColumnInfo(name = "Longitude")
    private double Longitude;

    public PicBean(String url, String des) {
        this.url = url;
        this.des = des;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public void setId(@NonNull Integer id) {
        this.id = id;
    }

    @NonNull
    public Integer getId() {
        return id;
    }

    @Override
    public String toString() {
        return "PicBean{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", des='" + des + '\'' +
                '}';
    }
}
