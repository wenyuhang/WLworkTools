package com.wl.uilib.utils;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;

/**
 * Created by WYH
 * on2019/12/10
 */
public class CacheUtils {

    public static String getCacheDirPath(Context context){
        String path = null;
        //判断SD状态
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            //当前SD可用
            //有SD卡的情况：应用的缓存目录
            path = context.getExternalCacheDir().getAbsolutePath()+ File.separator;
        } else {
            //当前SD不可用
            //无SD卡的情况：应用的缓存目录
            path = context.getCacheDir().getAbsolutePath()+File.separator;
        }
        return path;
    }

    /**
     * 存储数据
     * @param content
     * @param fileName
     * @param isAppend
     * @return
     */
    public static boolean WriteStringToFile(String content, String fileName,
                                            boolean isAppend) {
        boolean bFlag = false;
        final int iLen = content.length();
        final File file = new File(fileName);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            final FileOutputStream fos = new FileOutputStream(file, isAppend);
            byte[] buffer = new byte[iLen];
            try {
                buffer = content.getBytes();
                fos.write(buffer);
                fos.flush();
                bFlag = true;
            } catch (IOException ioex) {
            } finally {
                fos.close();
            }
        } catch (Exception ex) {
            L.e("widget_WidgetProviderReciver_WriteStringToFile",
                    "存文件异常：" + ex.toString());
        }
        return bFlag;
    }

    /**
     * 读取数据
     * @param sFileName
     * @return
     */
    public static String ReadStringFromFile(String sFileName) {
        if (StringUtil.getInstance().isEmpty(sFileName))
            return null;
        String sDest = null;
        File f = new File(sFileName);
        if (!f.exists()) {
            return null;
        }
        try {
            FileInputStream is = new FileInputStream(f);
            ByteArrayOutputStream bais = new ByteArrayOutputStream();
            try {
                byte[] buffer = new byte[1];// [512];
                while (is.read(buffer) != -1) {
                    bais.write(buffer);
                }
                sDest = bais.toString().trim();
            } catch (IOException ioex) {
            } finally {
                is.close();
                bais.close();
            }
        } catch (Exception ex) {
        }
        return sDest;
    }




    /**
     * 获取缓存数据大小
     * @param context
     * @return
     * @throws Exception
     */
    public static String getTotalCacheSize(Context context) throws Exception {
        long cacheSize = getFolderSize(context.getCacheDir());
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            cacheSize += getFolderSize(context.getExternalCacheDir());
        }
        return getFormatSize(cacheSize);
    }

    //删除内外缓存
    public static void clearAllCache(Context context) {
        deleteDir(context.getCacheDir());
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            deleteDir(context.getExternalCacheDir());
        }
    }

    //删除文件
    private static boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        return dir.delete();
    }

    // 获取文件
    //Context.getExternalFilesDir() --> SDCard/Android/data/你的应用的包名/files/ 目录，一般放一些长时间保存的数据
    //Context.getExternalCacheDir() --> SDCard/Android/data/你的应用包名/cache/目录，一般存放临时缓存数据
    public static long getFolderSize(File file){
        long size = 0;
        try {
            File[] fileList = file.listFiles();
            for (int i = 0; i < fileList.length; i++) {
                // 如果下面还有文件
                if (fileList[i].isDirectory()) {
                    size = size + getFolderSize(fileList[i]);
                } else {
                    size = size + fileList[i].length();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return size;
    }

    /**
     * 格式化单位
     *
     * @param size
     * @return
     */
    public static String getFormatSize(long size) {
        double kiloByte = size/1024;
        if (kiloByte < 1) {
            return size/1024 + "kB";
        }
        double megaByte = kiloByte/1024;
        if (megaByte < 1) {
            BigDecimal result1 = new BigDecimal(Double.toString(kiloByte));
            return result1.setScale(1, BigDecimal.ROUND_HALF_UP)
                    .toPlainString() + "KB";
        }
        double gigaByte = megaByte / 1024;
        if (gigaByte < 1) {
            BigDecimal result2 = new BigDecimal(Double.toString(megaByte));
            return result2.setScale(1, BigDecimal.ROUND_HALF_UP)
                    .toPlainString() + "MB";
        }
        double teraBytes = gigaByte / 1024;
        if (teraBytes < 1) {
            BigDecimal result3 = new BigDecimal(Double.toString(gigaByte));
            return result3.setScale(2, BigDecimal.ROUND_HALF_UP)
                    .toPlainString() + "GB";
        }
        BigDecimal result4 = new BigDecimal(teraBytes);
        return result4.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString()
                + "TB";
    }
}
