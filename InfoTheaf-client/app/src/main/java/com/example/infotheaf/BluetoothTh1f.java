package com.example.infotheaf;

import android.Manifest;
import android.app.Activity;
import android.bluetooth.BluetoothA2dp;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothManager;
import android.bluetooth.BluetoothProfile;
import android.content.Context;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.Set;

public class BluetoothTh1f {
    private Context mContext;
    private BluetoothManager bluetoothManager;
    private BluetoothAdapter bluetoothAdapter;

    public BluetoothTh1f(Context mContext) {
        mContext = mContext;
        bluetoothManager = (BluetoothManager) mContext.getSystemService(Context.BLUETOOTH_SERVICE);
        bluetoothAdapter = bluetoothManager.getAdapter();
    }


    public JSONObject getBluetoothInfo() throws SecurityException, JSONException {
        if (!bluetoothAdapter.isEnabled()) return null;
        JSONObject bluetoothInfo = new JSONObject();
//        List<BluetoothDevice> bluetoothConnectedDevices = this.bluetoothManager.getConnectedDevices(BluetoothProfile.GATT);
        Set<BluetoothDevice> bluetoothPairedDevices = bluetoothAdapter.getBondedDevices();
        String bluetoothAdapterName = bluetoothAdapter.getName();
        bluetoothInfo.put("bluetoothName", bluetoothAdapterName);
        JSONArray pairedDevices = new JSONArray();
        if (bluetoothPairedDevices.size() > 0) {
            for (BluetoothDevice currentDevice : bluetoothPairedDevices) {
                JSONObject device = new JSONObject();
                device.put("name", currentDevice.getName());
                device.put("address", currentDevice.getAddress());
//                device.put("uuid",currentDevice.getUuids());
//                device.put("status", currentDevice.getBondState());
                pairedDevices.put(device);
            }
        }
        bluetoothInfo.put("bond devices", pairedDevices);
        return bluetoothInfo;
    }
}
