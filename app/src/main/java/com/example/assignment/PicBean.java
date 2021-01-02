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

    public PicBean(String url, String des) {
        this.url = url;
        this.des = des;
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
