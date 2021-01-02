package com.example.assignment.camera;

import android.app.Activity;
import android.content.Intent;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class TakePhotoImp implements TakePhoto {
    private Activity contextWrap;
    private Uri tempUri;
    private File takePhotoFile;
    private TakeResultListener listener;
    private LocationManager locationManager;
    public TakePhotoImp(Activity activity, TakeResultListener listener) {
        contextWrap = activity;
        this.listener = listener;
    }

    @Override
    public void onPickFromGalleryWithoutCrop() {
        selectPicture();
    }

    @Override
    public void onPickFromCaptureWithoutCrop() {
        takePhotoFile = takePhotoPath();
        if (Build.VERSION.SDK_INT >= 23) {
            this.tempUri = TUriParse.getUriForFile(contextWrap, takePhotoFile);
        } else {
            this.tempUri = Uri.fromFile(takePhotoFile);
        }

        try {
            TIntentWap intentWap = new TIntentWap(IntentUtils.getCaptureIntent(this.tempUri), TConstant.RC_PICK_PICTURE_FROM_CAPTURE);
            contextWrap.startActivityForResult(intentWap.getIntent(), intentWap.getRequestCode());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void selectPicture() {
        TIntentWap intentWap = new TIntentWap(IntentUtils.getPickIntentWithGallery(), TConstant.RC_PICK_PICTURE_FROM_GALLERY_ORIGINAL);
        try {
            contextWrap.startActivityForResult(intentWap.getIntent(), intentWap.getRequestCode());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private File takePhotoPath() {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        File file = new File(Environment.getExternalStorageDirectory(), "/images/" + timeStamp + ".jpg");
        if (!file.getParentFile().exists()) {
           boolean flag= file.getParentFile().mkdirs();
            Log.d("Aliton","flag="+flag);
        }
        return file;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case TConstant.RC_PICK_PICTURE_FROM_CAPTURE:
                if (resultCode == Activity.RESULT_OK) {
                    try {
                        listener.takeSuccess(takePhotoFile.getPath());
                    } catch (Exception e) {
                        listener.takeFail(e.getMessage());
                        e.printStackTrace();
                    }
                } else {
                    listener.takeCancel();
                }
                break;

            case TConstant.RC_PICK_PICTURE_FROM_GALLERY_ORIGINAL:
                if (resultCode == Activity.RESULT_OK) {
                    try {
                        listener.takeSuccess(new File(TUriParse.getFilePathWithUri(data.getData(), contextWrap)).getPath());
                    } catch (Exception e) {
                        listener.takeFail(e.getMessage());
                        e.printStackTrace();
                    }
                } else {
                    listener.takeCancel();
                }
                break;
            default:
                break;
        }
    }
}
