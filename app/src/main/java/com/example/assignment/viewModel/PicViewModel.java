package com.example.assignment.viewModel;

import androidx.lifecycle.LiveData;

import com.example.assignment.AppContext;
import com.example.assignment.PicBean;
import com.example.assignment.base.BaseViewModel;
import com.example.assignment.db.PicDataBase;

import java.util.List;




public class PicViewModel extends BaseViewModel {
    /**
     * When the data request is successful, call back 当数据请求成功回调
     */
    protected LiveData<List<PicBean>> pics;

    /**
     * get data 获取数据
     */
    public LiveData<List<PicBean>> requestData() {
//        showDialog.setValue(true, "loading");
        return PicDataBase.getDatabase(AppContext.getInstance()).getUserDao().getPics();

    }

    /**
     * add data添加数据
     */
    public void addData(PicBean picBean) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                PicDataBase.getDatabase(AppContext.getInstance()).getUserDao().addUser(picBean);
            }
        }).start();

    }


}
