package com.example.infotheaf;

import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileTh1f {
    private Context mContext;
    public FileTh1f(Context mContext) {
        mContext = mContext;
    }

    public List<String> getFileInfo() {
        List<String> paths = new ArrayList<String>();
        File Download = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
//        File directory = new File(path);
//        File[] files = directory.listFiles();
        File[] files = Download.listFiles();
        for (int i = 0; i < files.length; ++i) {
            paths.add(files[i].getName());
        }
        return paths;

//        String path = Environment.getExternalStorageDirectory().toString()+"/Pictures";
//        File directory = new File(path);
//        File[] files = directory.listFiles();
//        Log.d("Files", "Size: "+ files.length);
//        for (int i = 0; i < files.length; i++)
//        {
//            Log.d("Files", "FileName:" + files[i].getName());
//        }
    }
}
