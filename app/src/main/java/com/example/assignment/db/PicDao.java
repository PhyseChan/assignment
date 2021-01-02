package com.example.assignment.db;



import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.assignment.PicBean;

import java.util.List;



@Dao
public interface PicDao {
    /* User */
    @Query("SELECT * FROM Pic")
    LiveData<List<PicBean>> getPics();

    @Insert
    void addUser(PicBean picBean);

    @Insert
    void addAll(List<PicBean> picList);

    @Delete
    int deleteUser(PicBean picBean);

    @Update
    int updateUser(PicBean picBean);
    /**
     * @Author: Qibin Liang
     * search the photo by id
     * **/
    @Query("Select * from Pic where Id > :Id")
    PicBean getcolorbyid(int Id);

    @Query("SELECT * FROM Pic")
    List<PicBean> getallPics();
}
