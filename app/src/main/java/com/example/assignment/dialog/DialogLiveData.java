package com.example.assignment.dialog;

import androidx.lifecycle.MutableLiveData;


public final class DialogLiveData<T> extends MutableLiveData<T> {
    private DialogBean bean = new DialogBean();

    public void setValue(boolean isShow) {
        bean.setShow(isShow);
        bean.setMsg("");
        setValue((T) bean);
    }

    public void setValue(boolean isShow, String msg) {
        bean.setShow(isShow);
        bean.setMsg(msg);
        setValue((T) bean);
    }
}
