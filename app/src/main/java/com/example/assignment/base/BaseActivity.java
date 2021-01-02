package com.example.assignment.base;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.Observer;

import com.example.assignment.dialog.DialogBean;


public abstract class BaseActivity<VM extends BaseViewModel, DB extends ViewDataBinding>
        extends BaseNoModelActivity<DB> {

    protected VM viewModel;

    /**
     * Permission request code权限请求码
     */
    public int PERMISSION_REQUEST_CODE = 0;


    @Override
    protected DB initDataBinding(int layoutId) {
        DB db = super.initDataBinding(layoutId);
        /**
         * Insert these two initialization functions into the{@link BaseActivity#initDataBinding}
         * 将这两个初始化函数插在{@link BaseActivity#initDataBinding}
         */
        viewModel = initViewModel();
        initObserve();
        return db;
    }

    /**
     * initialization ViewModel
     */
    protected abstract VM initViewModel();

    /**
     * Listen to the values of ShowDialog and error in the current ViewModel
     * 监听当前ViewModel中 showDialog和error的值
     */
    private void initObserve() {
        if (viewModel == null) return;
        viewModel.getShowDialog(this, new Observer<DialogBean>() {
            @Override
            public void onChanged(DialogBean bean) {
                if (bean.isShow()) {
                    showDialog(bean.getMsg());
                } else {
                    dismissDialog();
                }
            }
        });
        viewModel.getError(this, new Observer<Object>() {
            @Override
            public void onChanged(Object obj) {
                showError(obj);
            }
        });
    }

    /**
     *  error occurred in the ViewModel ( ViewModel层发生了错误)
     */
    protected abstract void showError(Object obj);

    /**
     * Check that all permissions are authorized检测所有的权限是否都已授权
     */
    public boolean checkPermissions(String[] permissions) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (verificationPermissions(grantResults)) {
                permissinSucceed(PERMISSION_REQUEST_CODE);
            } else {
                for (int i = 0; i < grantResults.length; i++) {
                    if (!ActivityCompat.shouldShowRequestPermissionRationale(this, permissions[i])) {
                        permissionRefused(PERMISSION_REQUEST_CODE);
                        return;
                    }
                }
                permissionFailing(PERMISSION_REQUEST_CODE);
            }
        }
    }
    /**
     * Request permission 请求权限
     */
    public void requestPermission(final Activity activity, String[] permissions, int requestCode) {
        this.PERMISSION_REQUEST_CODE = requestCode;

        // Check whether the permission is authorized检查权限是否授权
        if (checkPermissions(permissions)) {
            permissinSucceed(PERMISSION_REQUEST_CODE);
        } else {
            ActivityCompat.requestPermissions(activity, permissions, PERMISSION_REQUEST_CODE);
        }
    }

    /**
     * Verify permissions 验证权限
     *
     * @param results
     * @return
     */
    private boolean verificationPermissions(int[] results) {
        for (int result : results) {
            if (result != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    /**
     * Failed to get permission获取权限失败
     */
    public void permissionFailing(int requestCode) {

    }

    /**
     * Permission is denied and the callback is no longer prompted权限被拒绝并不再提示回调
     */
    public void permissionRefused(int requestCode) {

    }

    /**
     * Permission obtained successfully获取权限成功
     */
    public void permissinSucceed(int requestCode) {

    }
}

