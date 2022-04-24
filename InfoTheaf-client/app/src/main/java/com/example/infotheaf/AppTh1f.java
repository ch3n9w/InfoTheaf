package com.example.infotheaf;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

import java.util.ArrayList;
import java.util.List;

public class AppTh1f {
    private Context mContext;
    public AppTh1f(Context mContext) {
        this.mContext = mContext;
    }

    public List<String> getAppInfo() {
        final PackageManager pm = this.mContext.getPackageManager();
        //get a list of installed apps.
        List<ApplicationInfo> packages = pm.getInstalledApplications(PackageManager.GET_META_DATA);
        List<String> AppNames = new ArrayList<>();
        for (ApplicationInfo packageInfo : packages) {
            AppNames.add(packageInfo.packageName);
//            Log.d(TAG, "Installed package :" + packageInfo.packageName);
//            Log.d(TAG, "Source dir : " + packageInfo.sourceDir);
        }
        return AppNames;
    }
}
