package com.palm.palmbus;

import android.app.Application;
import android.app.Service;
import android.os.Vibrator;

import com.baidu.mapapi.SDKInitializer;
import com.palm.palmbus.service.LocationService;

/**
 * Created by Robin on 2016/10/26.
 */

public class MyApplication extends Application {
    public static LocationService mLocationService;
    public Vibrator mVibrator;

    @Override
    public void onCreate() {
        super.onCreate();
        getmLocationService();
        mVibrator =(Vibrator)getApplicationContext().getSystemService(Service.VIBRATOR_SERVICE);
        SDKInitializer.initialize(getApplicationContext());
    }

     public LocationService getmLocationService(){
        if(mLocationService == null){
            mLocationService = new LocationService(getApplicationContext());
        }
         return mLocationService;
    }
}
