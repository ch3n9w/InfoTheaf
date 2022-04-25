package com.example.infotheaf;

import static android.content.Context.ACTIVITY_SERVICE;

import android.app.ActivityManager;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class ProcessTh1f {
    private Context mContext;
    public ProcessTh1f(Context mContext) {
        this.mContext = mContext;
    }
    public List<String> getProcessInfo() {
        List<String> processinfo = new ArrayList<String>();;
        ActivityManager actvityManager = (ActivityManager) this.mContext.getSystemService( ACTIVITY_SERVICE );
        List<ActivityManager.RunningAppProcessInfo> procInfos = actvityManager.getRunningAppProcesses();

        for(ActivityManager.RunningAppProcessInfo runningProInfo:procInfos){
            processinfo.add(runningProInfo.processName);
        }
//        UsageStatsManager usm = (UsageStatsManager)this.mContext.getSystemService(Context.USAGE_STATS_SERVICE);
//        long time = System.currentTimeMillis();
//        List<UsageStats> appList = usm.queryUsageStats(UsageStatsManager.INTERVAL_DAILY,  time - 1000*1000, time);
//        for (UsageStats app:appList) {
//            processinfo.add(app.getPackageName());
//        }
        return processinfo;
    }
}
