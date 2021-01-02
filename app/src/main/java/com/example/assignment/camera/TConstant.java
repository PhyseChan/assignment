package com.example.assignment.camera;

import android.content.Context;


public class TConstant {


    /**
     * request Code  Cut out photos裁剪照片
     **/
    public final static int RC_CROP = 1001;
    /**
     * request Code Take pictures from the camera and crop them从相机获取照片并裁剪
     **/
    public final static int RC_PICK_PICTURE_FROM_CAPTURE_CROP = 1002;
    /**
     * request Code Get photos from camera without clipping从相机获取照片不裁剪
     **/
    public final static int RC_PICK_PICTURE_FROM_CAPTURE = 1003;
    /**
     * request Code Choose photo from file从文件中选择照片
     **/
    public final static int RC_PICK_PICTURE_FROM_DOCUMENTS_ORIGINAL = 1004;
    /**
     * request Code Select a photo from the file and crop it从文件中选择照片并裁剪
     **/
    public final static int RC_PICK_PICTURE_FROM_DOCUMENTS_CROP = 1005;
    /**
     * request Code Select a photo from album从相册选择照
     **/
    public final static int RC_PICK_PICTURE_FROM_GALLERY_ORIGINAL = 1006;
    /**
     * request Code Select photos from album and crop从相册选择照片并裁剪
     **/
    public final static int RC_PICK_PICTURE_FROM_GALLERY_CROP = 1007;
    /**
     * request Code Select multiple photos选择多张照片
     **/
    public final static int RC_PICK_MULTIPLE = 1008;
    /**
     * request Code Take pictures from the camera and crop them从相机获取照片并裁剪
     **/
    public final static int RC_PICK_VIDEO_FROM_CAPTURE = 1009;

    /**
     * request Code Choose photos from album从相册选择照片
     **/
    public final static int RC_PICK_VIDEO_FROM_GALLERY_ORIGINAL = 1010;

    /**
     * requestCode    Request permission请求权限
     **/
    public final static int PERMISSION_REQUEST_TAKE_PHOTO = 2000;

    public final static String getFileProviderName(Context context){
        return context.getPackageName()+".fileprovider";
    }

    /**
     * Camera permission相机权限
     */
    public static final String IS_ALLOW_CAMERA_SCAN_QRCODE = "isAllowCameraScanQRCode";

    /**
     * album 相册
     */
    public static final String IS_ALLOW_ALBUM_SCAN_QRCODE = "isScanCodeAlbumShow";
 }