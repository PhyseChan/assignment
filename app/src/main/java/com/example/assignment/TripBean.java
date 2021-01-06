package com.example.assignment;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "TripBean")
public class TripBean {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "Id")
    private Integer id;

    @ColumnInfo(name = "tripTitle")
    private String tripTitle;

    @NonNull
    public Integer getId() {
        return id;
    }

    public TripBean(String tripTitle) {
        this.tripTitle = tripTitle;
    }

    public void setId(@NonNull Integer id) {
        this.id = id;
    }

    public String getTripTitle() {
        return tripTitle;
    }

    public void setTripTitle(String tripTitle) {
        this.tripTitle = tripTitle;
    }

    @Override
    public String toString() {
        return "TripBean{" +
                "id=" + id +
                ", tripTitle='" + tripTitle + '\'' +
                '}';
    }
}
