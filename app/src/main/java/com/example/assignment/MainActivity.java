package com.example.assignment;
/**
 * member1: YINGYUE WU(
 *In order to make it easier for the team members to understand the code, I will try to annotate the code in both Chinese and English
 *(为了便于小组成员理解代码，尽量同时用中文和英文同时注释)
 *  a. Implementation of picture taking and photo uploading (1.1.1)
 * b. Room database implementation (1.1.3)
 * c. MVVM and live data framework implementation
 **/

/**
 *  member2: Qibin Liang
 *  a. Implementing the visit tracker (background process) and sensor tracking including the interface (1.1.2)
 * **/

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignment.adapter.PicAdapter;
import com.example.assignment.base.BaseActivity;
import com.example.assignment.base.adapter.OnItemClickListener;
import com.example.assignment.camera.TakePhoto;
import com.example.assignment.camera.TakePhotoImp;
import com.example.assignment.databinding.ActivityMainBinding;
import com.example.assignment.utils.LocationUtil;
import com.example.assignment.viewModel.PicViewModel;
import com.example.assignment.weiget.CommonRefusedDialog;

import java.util.List;




public class MainActivity extends BaseActivity<PicViewModel, ActivityMainBinding> implements TakePhoto.TakeResultListener, OnItemClickListener<PicBean> {
    /**
     * Storage permission request code存储权限请求码
     */
    private static final int READ_EXTERNAL_STORAGE_CODE = 0x11;
    /**
     * Camera permission request code照相机权限请求码
     */
    private static final int CAMERA_PERMISSION_CODE = 0x22;
    /**
     * @Author Qibin Liang
     * location permission request code
     * **/
    private final int GET_LOCATION_WRITE_PERMISSION_REQUEST = 103;
    private PicAdapter adapter;

    private TakePhoto mPhoto;


    @Override
    protected PicViewModel initViewModel() {
        PicViewModel picViewModel = new ViewModelProvider(this).get(PicViewModel.class);
        return picViewModel;
    }

    @Override
    protected void showError(Object obj) {

    }

    @Override
    protected int onCreate() {
        checkLocationReadWritePermission();
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        mPhoto = new TakePhotoImp(this, this);
        RecyclerView rvPic = dataBinding.rvPic;
        LinearLayoutManager manager = new LinearLayoutManager(this);
        adapter = new PicAdapter();
        adapter.setOnItemListener(this);
        rvPic.setLayoutManager(manager);
        rvPic.setAdapter(adapter);
    }

    @Override
    protected void initData() {
        dataBinding.setModel(this);
        viewModel.requestData();
        //Notification of successful data request数据请求成功通知
        viewModel.requestData().observe(this, new Observer<List<PicBean>>() {
            @Override
            public void onChanged(List<PicBean> picBeans) {
                adapter.setNewData(picBeans);
            }
        });
    }


    @Override
    public void permissinSucceed(int code) {
        switch (code) {
            case READ_EXTERNAL_STORAGE_CODE:
                mPhoto.onPickFromGalleryWithoutCrop();
                break;
            case CAMERA_PERMISSION_CODE:
                mPhoto.onPickFromCaptureWithoutCrop();
                break;
            default:
                break;
        }
    }

    @Override
    public void permissionRefused(int requestCode) {
        switch (requestCode) {
            case READ_EXTERNAL_STORAGE_CODE:
                String title = getResources().getString(R.string.permission_storage_title);
                String message = getResources().getString(R.string.permission_storage_message);
                CommonRefusedDialog.showRefusedDialog(this, title, message, true);
                break;

            case CAMERA_PERMISSION_CODE:
                String mTitle = getResources().getString(R.string.permission_camera_title);
                String mMessage = getResources().getString(R.string.permission_camera_message);
                CommonRefusedDialog.showRefusedDialog(this, mTitle, mMessage, true);
                break;
            default:
                break;
        }
    }

    @Override
    public void permissionFailing(int code) {
        switch (code) {
            case READ_EXTERNAL_STORAGE_CODE:
                //Todo Users are not allowed not to process(用户不允许不处理)
                break;

            case CAMERA_PERMISSION_CODE:
                //Todo Users are not allowed to exit directly(用户不允许直接退出)
                break;

            default:
                break;
        }
    }

    /**
     * Button click event album 按钮点击事件 相册
     */
    public void pickByGallery(View view) {
        if (checkPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE})) {
            mPhoto.onPickFromGalleryWithoutCrop();
        } else {
            //Request storage permission申请存储权限
            requestPermission(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, READ_EXTERNAL_STORAGE_CODE);
        }
    }

    /**
     * Button click event photo按钮点击事件 拍照
     */
    public void pickByCapture(View view) {
        if (checkPermissions(new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION})) {
            mPhoto.onPickFromCaptureWithoutCrop();
        } else {
            //Request storage permission申请存储权限
            requestPermission(this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION}, CAMERA_PERMISSION_CODE);
        }
    }

    @Override
    public void takeSuccess(String result) {
        PicBean picBean = new PicBean(result, "");
        picBean = new LocationUtil().setlocation(picBean, MainActivity.this);
        viewModel.addData(picBean);
    }

    @Override
    public void takeFail(String msg) {
        //Todo Photo failed(拍照失败)

    }

    @Override
    public void takeCancel() {
        //Todo Photo cancellation(拍照取消)
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mPhoto.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onItemClick(PicBean picBean, int position) {
        Intent intent = new Intent(MainActivity.this, ShowImageActivity.class);
        intent.putExtra("path", picBean.getUrl());
        startActivity(intent);
    }

    @Override
    public boolean onItemLongClick(PicBean picBean, int position) {
        return true;
    }

    /**
     * @Author: Qibin Liang
     * @methodName: onCreateOptionsMenu
     * @param menu Menu
     * @return boolean
     * @Description: create a options menu. This options menu is the map button that shows on the right-top of the app.
     * **/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mian_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * @Author: Qibin Liang
     * @methodName: onOptionsItemSelected
     * @param item MenuItem
     * @return boolean
     * @Description: OptionsItem selection
     * **/
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
        return true;
    }

    /**
     * @Author: Qibin Liang
     * @methodName: checkLocationReadWritePermission
     * @return void
     * @Description: check whether the Activity has the permission of getting location
     * **/
    private void checkLocationReadWritePermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED &&
                    ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED&&
                    ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            } else {
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.ACCESS_FINE_LOCATION}, GET_LOCATION_WRITE_PERMISSION_REQUEST);
            }
        } else {

        }
    }
}