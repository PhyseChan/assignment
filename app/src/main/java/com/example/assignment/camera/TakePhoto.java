package com.example.assignment.camera;

import android.content.Intent;

public interface TakePhoto {

    void onPickFromGalleryWithoutCrop();

    /**
     * Get pictures from album without clipping从相册中获取图片不裁剪
     * @param outPutUri Path saved after image clipping图片裁剪之后保存的路径
     */
    void onPickFromCaptureWithoutCrop();

    /**
     * Process the results of taking pictures or selecting pictures from albums or cropping them 处理拍照或从相册选择的图片或裁剪的结果
     * @param requestCode
     * @param resultCode
     * @param data
     */
    void onActivityResult(int requestCode, int resultCode, Intent data);

    /**
     * Photo result monitoring interface 拍照结果监听接口
     */
    interface TakeResultListener {
        void takeSuccess(String result);

        void takeFail(String msg);

        void takeCancel();

    }

}