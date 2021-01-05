package com.example.assignment.db;



import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.assignment.PicBean;

import java.util.List;


/**
 * @Author: Qibin Liang, YINGYUE WU
 * @Time: 2020-12-30
 * @version: 2.0
 * @ClassName: PicDao
 * @Description: This PicDao was created by YINGYUE WU. More database operation functions were appended by Qibin Liang
 * **/
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
     * @methodName: getcolorbyid
     * @param Id
     * @return PicBean
     * @Description: select the data by id
     * **/
    @Query("Select * from Pic where Id = (:Id)")
    PicBean getPicbyid(int Id);

    /**
     * @Author: Qibin Liang
     * @methodName: getallPics
     * @return List<PicBean>
     * @Description: select all pictures
     * **/
    @Query("SELECT * FROM Pic")
    List<PicBean> getallPics();
}
