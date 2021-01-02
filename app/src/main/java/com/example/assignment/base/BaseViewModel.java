package com.example.assignment.base;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.example.assignment.dialog.DialogBean;
import com.example.assignment.dialog.DialogLiveData;



public abstract class BaseViewModel extends ViewModel {
    /**
     * Used to inform activity / fragment whether to display wait dialog
     * 用来通知 Activity／Fragment 是否显示等待Dialog
     */
    protected DialogLiveData<DialogBean> showDialog = new DialogLiveData<>();
    /**
     * When an error occurs in the ViewModel layer, the activity / fragment needs to be notified
     * 当ViewModel层出现错误需要通知到Activity／Fragment
     */
    protected MutableLiveData<Object> error = new MutableLiveData<>();

    public void getShowDialog(LifecycleOwner owner, Observer<DialogBean> observer) {
        showDialog.observe(owner, observer);
    }

    public void getError(LifecycleOwner owner, Observer<Object> observer) {
        error.observe(owner, observer);
    }

    /**
     * ViewModel destruction also cancels the request
     * ViewModel销毁同时也取消请求
     */
    @Override
    protected void onCleared() {
        super.onCleared();
        showDialog = null;
        error = null;
    }
}
