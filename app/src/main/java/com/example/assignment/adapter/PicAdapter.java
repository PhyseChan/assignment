package com.example.assignment.adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.example.assignment.BR;
import com.example.assignment.PicBean;
import com.example.assignment.R;
import com.example.assignment.base.adapter.BaseDBRVAdapter;
import com.example.assignment.databinding.ItemPicBinding;

import java.io.FileInputStream;
import java.io.FileNotFoundException;



public class PicAdapter extends BaseDBRVAdapter<PicBean, ItemPicBinding> {

    public PicAdapter() {
        super(R.layout.item_pic, BR.bean);
    }

    /**
     * ImageView binding adapter  绑定Adapter的ImageView
     *
     * @param imageView
     * @param url       Picture address图片地址
     */
    @BindingAdapter({"imageUrl"})
    public static void loadImage(ImageView imageView, String url) {
        imageView.setImageBitmap(getLoacalBitmap(url));
        new UploadSingleImageTask(imageView).execute(url);
    }

    /**
     * Load local pictures 加载本地图片
     *
     * @param url
     * @return
     */
    public static Bitmap getLoacalBitmap(String url) {
        try {
            FileInputStream fis = new FileInputStream(url);
            return BitmapFactory.decodeStream(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Bitmap decodeSampledBitmapFromResource(String filePath, int reqWidth, int reqHeight) { // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, options);
        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(filePath, options);
    }

    public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
        if (height > reqHeight || width > reqWidth) {
            final int halfHeight = height / 2;
            final int halfWidth = width / 2;
// Calculate the largest inSampleSize value that is a power of 2 and keeps both // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) >= reqHeight
                    && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2;
            }
        }
        return inSampleSize;
    }

    private static class UploadSingleImageTask extends AsyncTask<String, Void, Bitmap> {
        private ImageView imageView;

        public UploadSingleImageTask(ImageView imageView) {
            this.imageView = imageView;
        }

        @Override
        protected Bitmap doInBackground(String... url) {
            Bitmap myBitmap =
                    decodeSampledBitmapFromResource(url[0], 100, 100);
            return myBitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            imageView.setImageBitmap(bitmap);
        }
    }
}
