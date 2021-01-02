package com.example.assignment.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.DisplayMetrics;



public class DensityUtil {

    /**
     * Get screen width (pixels) 获取屏幕宽度（像素）
     *
     * @param context 上下文
     * @return px
     */
    public static int getWith(Context context) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        return dm.widthPixels;
    }

    /**
     * Get screen height (pixels)获取屏幕高度（像素）
     *
     * @param context 上下文
     * @return px
     */
    public static int getHeight(Context context) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        return dm.heightPixels;
    }

    /**
     * Gets the height of the status bar 获取状态栏的高度
     *
     * @param context 上下文
     * @return px
     */
    public static int getStatusBarHeight(Context context) {
        int statusBarHeight = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            statusBarHeight = context.getResources().getDimensionPixelSize(resourceId);
        }
        return statusBarHeight;
    }

    /**
     * Gets the height of the actionbar 获取标题栏（ActionBar）的高度
     *
     * @param context 上下文
     * @return px
     */
    public static int getActionBarHeight(Context context) {
        TypedArray values = context.getTheme().obtainStyledAttributes(new int[]{android.R.attr.actionBarSize});
        int actionBarHeight = values.getDimensionPixelSize(0, 0);
        values.recycle();
        return actionBarHeight;
    }

    /**
     * Conversion from DP units to PX (pixels) according to the resolution of the phone 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * Changes from PX (pixels) to DP according to the resolution of the phone根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
}
