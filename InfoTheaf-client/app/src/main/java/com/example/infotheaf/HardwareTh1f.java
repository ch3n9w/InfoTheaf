package com.example.infotheaf;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.util.Base64;
import android.util.DisplayMetrics;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;

public class HardwareTh1f {
    private Context mContext;
    public HardwareTh1f(Context mContext){
        this.mContext = mContext;
    }

    public JSONObject getHardwareInfo() throws JSONException {
        JSONObject hardwareInfo = new JSONObject();
        hardwareInfo.put("model", Build.MODEL);
        hardwareInfo.put("id", Build.ID);
        hardwareInfo.put("manufacture", Build.MANUFACTURER);
        hardwareInfo.put("brand", Build.BRAND);
        hardwareInfo.put("type", Build.TYPE);
        hardwareInfo.put("user", Build.USER);
//        hardwareInfo.put("base", Build.VERSION_CODES.BASE);
//        hardwareInfo.put("incremental", Build.VERSION.INCREMENTAL);
//        hardwareInfo.put("BASE_OS", Build.VERSION.BASE_OS);
        hardwareInfo.put("Release", Build.VERSION.RELEASE);
        hardwareInfo.put("SDK", Build.VERSION.SDK_INT);
        hardwareInfo.put("host", Build.HOST);

        hardwareInfo.put("ram", getRAMInfo());

        JSONObject romInfo = new JSONObject();
        romInfo.put("totalrom", getTotalInternalMemorySize());
        romInfo.put("availrom", getAvailableInternalMemorySize());
        hardwareInfo.put("rom", romInfo);

        hardwareInfo.put("screen", getScreenSize());
//        hardwareInfo.put("CPU", getCPUInfo());
        hardwareInfo.put("CPU", Runtime.getRuntime().availableProcessors());
        return hardwareInfo;
    }

    public String getCPUInfo() {
        ProcessBuilder processBuilder;
        String[] DATA = {"/system/bin/cat", "/proc/cpuinfo"};
        Process process ;
        InputStream inputStream;
        byte[] byteArry = new byte[0];
        String Holder = "";
        try{
            processBuilder = new ProcessBuilder(DATA);
            process = processBuilder.start();
            inputStream = process.getInputStream();
            while(inputStream.read(byteArry) != -1){
                Holder = Holder + new String(byteArry);
            }
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(Holder);
            objectOutputStream.close();

            inputStream.close();
            return Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT);
        } catch(IOException ex){
            ex.printStackTrace();
            return null;
        }

    }
    public String getScreenSize() throws JSONException {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity)this.mContext).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;
        return Integer.toString(height) + "x" + Integer.toString(width);
    }
    public JSONObject getRAMInfo() throws JSONException {
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        ActivityManager activityManager = (ActivityManager) this.mContext.getSystemService(Context.ACTIVITY_SERVICE);
        activityManager.getMemoryInfo(memoryInfo);
        JSONObject ramInfo = new JSONObject();
        ramInfo.put("totalram", formatSize(memoryInfo.totalMem));
        ramInfo.put("availram", formatSize(memoryInfo.availMem));
        return ramInfo;
    }

    public static String getAvailableInternalMemorySize() {
        File path = Environment.getDataDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSizeLong();
        long availableBlocks = stat.getAvailableBlocksLong();
        return formatSize(availableBlocks * blockSize);
    }

    public static String getTotalInternalMemorySize() {
        File path = Environment.getDataDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSizeLong();
        long totalBlocks = stat.getBlockCountLong();
        return formatSize(totalBlocks * blockSize);
    }

    public static String formatSize(long size) {
        String suffix = null;

        if (size >= 1024) {
            suffix = "KB";
            size /= 1024;
            if (size >= 1024) {
                suffix = "MB";
                size /= 1024;
            }
        }
        StringBuilder resultBuffer = new StringBuilder(Long.toString(size));

        int commaOffset = resultBuffer.length() - 3;
        while (commaOffset > 0) {
            resultBuffer.insert(commaOffset, ',');
            commaOffset -= 3;
        }
        if (suffix != null) resultBuffer.append(suffix);
        return resultBuffer.toString();
    }

}
