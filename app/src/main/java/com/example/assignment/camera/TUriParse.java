package com.example.assignment.camera;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;

import java.io.Closeable;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class TUriParse {
    private static final String TAG = IntentUtils.class.getName();

    /**
     * Convert the URI with scheme as file to the content URI provided by fileprovider
     * 将scheme为file的uri转成FileProvider 提供的content uri
     *
     * @param context
     * @param uri
     * @return
     */
    public static Uri convertFileUriToFileProviderUri(Context context, Uri uri) {
        if (uri == null) return null;
        if (ContentResolver.SCHEME_FILE.equals(uri.getScheme())) {
            return getUriForFile(context, new File(uri.getPath()));
        }
        return uri;

    }

    /**
     * 获取一个临时的Uri, 文件名随机生成
     *Get a temporary URI and generate the file name randomly
     * @param context
     * @return
     */

    public static File getFromMediaUri(Context context, ContentResolver resolver, Uri uri) {
        if (uri == null) return null;

        if (ContentResolver.SCHEME_FILE.equals(uri.getScheme())) {
            return new File(uri.getPath());
        } else if (ContentResolver.SCHEME_CONTENT.equals(uri.getScheme())) {
            final String[] filePathColumn = { MediaStore.MediaColumns.DATA, MediaStore.MediaColumns.DISPLAY_NAME };
            Cursor cursor = null;
            try {
                cursor = resolver.query(uri, filePathColumn, null, null, null);
                if (cursor != null && cursor.moveToFirst()) {
                    final int columnIndex = (uri.toString().startsWith("content://com.google.android.gallery3d")) ?
                            cursor.getColumnIndex(MediaStore.MediaColumns.DISPLAY_NAME) :
                            cursor.getColumnIndex(MediaStore.MediaColumns.DATA);
                    // Picasa images on API 13+
                    if (columnIndex != -1) {
                        String filePath = cursor.getString(columnIndex);
                        if (!TextUtils.isEmpty(filePath)) {
                            return new File(filePath);
                        }
                    }
                }
            } catch (IllegalArgumentException e) {
                // Google Drive images
                return getFromMediaUriPfd(context, resolver, uri);
            } catch (SecurityException ignored) {
                // Nothing we can do
            } finally {
                if (cursor != null) cursor.close();
            }
        }
        return null;
    }
    private static File getFromMediaUriPfd(Context context, ContentResolver resolver, Uri uri) {
        if (uri == null) return null;

        FileInputStream input = null;
        FileOutputStream output = null;
        try {
            ParcelFileDescriptor pfd = resolver.openFileDescriptor(uri, "r");
            FileDescriptor fd = pfd.getFileDescriptor();
            input = new FileInputStream(fd);

            String tempFilename = getTempFilename(context);
            output = new FileOutputStream(tempFilename);

            int read;
            byte[] bytes = new byte[4096];
            while ((read = input.read(bytes)) != -1) {
                output.write(bytes, 0, read);
            }
            return new File(tempFilename);
        } catch (IOException ignored) {
            // Nothing we can do
        } finally {
            closeSilently(input);
            closeSilently(output);
        }
        return null;
    }
    private static String getTempFilename(Context context) throws IOException {
        File outputDir = context.getCacheDir();
        File outputFile = File.createTempFile("image", "tmp", outputDir);
        return outputFile.getAbsolutePath();
    }

    public static void closeSilently(@Nullable Closeable c) {
        if (c == null) return;
        try {
            c.close();
        } catch (Throwable t) {
            // Do nothing
        }
    }
    /**
     * Get a temporary URI by passing in the string path
     * 获取一个临时的Uri, 通过传入字符串路径
     *
     * @param context
     * @param path
     * @return
     */
    public static Uri getTempUri(Context context, String path) {
        File file = new File(path);
        return getTempUri(context, file);
    }

    /**
     * 获取一个临时的Uri, 通过传入File对象
     *Get a temporary URI and pass in the file object
     * @param context
     * @return
     */
    public static Uri getTempUri(Context context, File file) {
        if (!file.getParentFile().exists()) file.getParentFile().mkdirs();
        return getUriForFile(context, file);
    }

    /**
     * 创建一个用于拍照图片输出路径的Uri (FileProvider)
     *Create a URI (fileprovider) for photo output path
     * @param context
     * @return
     */
    public static Uri getUriForFile(Context context, File file) {
        return FileProvider.getUriForFile(context, TConstant.getFileProviderName(context), file);
    }

    /**
     * 将TakePhoto 提供的Uri 解析出文件绝对路径
     *Resolve the URI provided by takephoto to the absolute path of the file
     * @param uri
     * @return
     */
    public static String parseOwnUri(Context context, Uri uri) {
        if (uri == null) return null;
        String path;
        if (TextUtils.equals(uri.getAuthority(), TConstant.getFileProviderName(context))) {
            path = new File(uri.getPath().replace("camera_photos/", "")).getAbsolutePath();
        } else {
            path = uri.getPath();
        }
        return path;
    }

    /**
     * 通过URI获取文件的路径
     *Get the path of the file through the URI
     * @param uri
     * @param activity
     * @return
     */
    public static String getFilePathWithUri(Uri uri, Activity activity) throws Exception {
        if (uri == null) {
            Log.w(TAG, "uri is null,activity may have been recovered?");
            throw new Exception();
        }
        File picture = getFileWithUri(uri, activity);
        String picturePath = picture == null ? null : picture.getPath();
        if (TextUtils.isEmpty(picturePath)) throw new Exception();
        if (!TImageFiles.checkMimeType(activity, TImageFiles.getMimeType(activity, uri)))
            throw new Exception();
        return picturePath;
    }

    /**
     * 通过URI获取文件
     *Get file through the URI
     * @param uri
     * @param activity
     * @return
     */
    public static File getFileWithUri(Uri uri, Activity activity) {
        String picturePath = null;
        String scheme = uri.getScheme();
        if (ContentResolver.SCHEME_CONTENT.equals(scheme)) {
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            Cursor cursor = activity.getContentResolver().query(uri,
                    filePathColumn, null, null, null);//从系统表中查询指定Uri对应的照片
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            if (columnIndex >= 0) {
                picturePath = cursor.getString(columnIndex);  //获取照片路径
            } else if (TextUtils.equals(uri.getAuthority(), TConstant.getFileProviderName(activity))) {
                picturePath = parseOwnUri(activity, uri);
            }
            cursor.close();
        } else if (ContentResolver.SCHEME_FILE.equals(scheme)) {
            picturePath = uri.getPath();
        }
        return TextUtils.isEmpty(picturePath) ? null : new File(picturePath);
    }

    /**
     * 通过从文件中得到的URI获取文件的路径
     *Get the path of the file by the URI obtained from the file
     * @param uri
     * @param activity

     */
    public static String getFilePathWithDocumentsUri(Uri uri, Activity activity) throws Exception {
        if (uri == null) {
            Log.e(TAG, "uri is null,activity may have been recovered?");
            return null;
        }
        if (ContentResolver.SCHEME_CONTENT.equals(uri.getScheme()) && uri.getPath().contains("document")) {
            File tempFile = TImageFiles.getTempFile(activity, uri);
            try {
                TImageFiles.inputStreamToFile(activity.getContentResolver().openInputStream(uri), tempFile);
                return tempFile.getPath();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                throw new Exception();
            }
        } else {
            return getFilePathWithUri(uri, activity);
        }
    }
}
