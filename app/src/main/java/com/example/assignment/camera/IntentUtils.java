package com.example.assignment.camera;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;

/**
 * The intent tool class is used to generate photos
 * Select the photo from the album and crop the intents required for the photo
 * Intent工具类用于生成拍照、
 * 从相册选择照片，裁剪照片所需的Intent

 */
public class IntentUtils {
    private static final String TAG = IntentUtils.class.getName();


    /**
     * getCaptureIntent 获取拍照的Intent
     * @return
     */
    public static Intent getCaptureIntent(Uri outPutUri) {
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);//Set action to photo 设置Action为拍照
        intent.putExtra(MediaStore.EXTRA_OUTPUT, outPutUri);//Save the taken photos to the specified URI 将拍取的照片保存到指定URI
        return intent;
    }

    /**
     * getCaptureVideoIntent 获取拍照的Intent
     * @return
     */
    public static Intent getCaptureVideoIntent(Uri outPutUri) {
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.setAction(MediaStore.ACTION_VIDEO_CAPTURE);//Set action to record设置Action为录制视频
        intent.putExtra(MediaStore.EXTRA_OUTPUT, outPutUri);//Save the taken photos to the specified URI 将拍取的照片保存到指定URI
        intent.putExtra(MediaStore.EXTRA_DURATION_LIMIT, 10);
        return intent;
    }

    /**
     * getPickVideoIntentWithGallery 获取选择照片的Intent
     * @return
     */
    public static Intent getPickVideoIntentWithGallery() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_PICK);//Pick an item from the data
        intent.setType("video/*");//Choose from all pictures 从所有图片中进行选择
        return intent;
    }

    /**
     * getPickIntentWithGallery 获取选择照片的Intent
     * @return
     */
    public static Intent getPickIntentWithGallery() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_PICK);//Pick an item from the data
        intent.setType("image/*");//Choose from all pictures 从所有图片中进行选择
        return intent;
    }
    /**
     * getPickIntentWithDocuments 获取从文件中选择照片的Intent
     * @return
     */
    public static Intent getPickIntentWithDocuments() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        return intent;
    }
}
