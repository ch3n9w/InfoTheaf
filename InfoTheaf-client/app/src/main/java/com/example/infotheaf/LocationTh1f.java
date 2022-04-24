package com.example.infotheaf;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class LocationTh1f {
    private Context mContext;
    private LocationManager locationManager;
    private LocationListener locationListener;

    public class CustomListener implements LocationListener {
        public JSONObject locationInfo;
        @Override
        public void onLocationChanged(@NonNull Location location) {
            this.locationInfo = new JSONObject();
            try {
                this.locationInfo.put("latitude", location.getLatitude());
                this.locationInfo.put("longtitude", location.getLongitude());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        public JSONObject getLocationInfo() {
            return this.locationInfo;
        }
    }
    public LocationTh1f(Context mContext) {
        this.mContext = mContext;
        this.locationManager = (LocationManager) this.mContext.getSystemService(Context.LOCATION_SERVICE);
        this.locationListener = new CustomListener();
    }


    public JSONObject getLocationInfo() throws JSONException {
        if (
                ActivityCompat.checkSelfPermission(this.mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(this.mContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
        ) {
            return null;
        }
        JSONObject result = new JSONObject();
        try {
            Location location = this.locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            result.put("longtitude", location.getLongitude());
            result.put("latitude", location.getLatitude());
            return result;
        } catch (JSONException e) {
            Location location = this.locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            result.put("longtitude", location.getLongitude());
            result.put("latitude", location.getLatitude());
            return result;
        } catch (Exception e) {
            JSONObject k = new JSONObject();
            return k;
        }

//        this.locationManager.requestLocationUpdates(
//                LocationManager.NETWORK_PROVIDER,
//                0,
//                0,
//                this.locationListener
//        );
//
//        return ((CustomListener)this.locationListener).getLocationInfo();
    }


}
