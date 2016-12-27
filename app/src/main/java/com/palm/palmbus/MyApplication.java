package com.palm.palmbus;

import android.app.Application;
import android.app.Service;
import android.content.Context;
import android.content.res.Resources;
import android.os.Vibrator;
import android.support.multidex.MultiDex;

import com.baidu.mapapi.SDKInitializer;
import com.palm.palmbus.service.LocationService;

/**
 * Created by Robin on 2016/10/26.
 */

public class MyApplication extends Application {
    public static LocationService mLocationService;
    public Vibrator mVibrator;
    private static MyApplication myApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        getmLocationService();
        mVibrator =(Vibrator)getApplicationContext().getSystemService(Service.VIBRATOR_SERVICE);
        myApplication = this;
        //百度地图初始化
        SDKInitializer.initialize(getApplicationContext());
    }

    public static Context getAppContext(){
        return myApplication;
    }


    public static Resources getAppResources(){
        return myApplication.getResources();
    }

     public LocationService getmLocationService(){
        if(mLocationService == null){
            mLocationService = new LocationService(getApplicationContext());
        }
         return mLocationService;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        //分包
        MultiDex.install(base);
    }
}
