package com.palm.palmbus.config;

import android.util.Log;

/**
 * Created by Robin on 2016/10/26.
 */

public class LogUtil {
    public static final String TAG = "HarryRobin";


    public static void logOutPut(String log){
        Log.i(TAG,log);
    }

    public static void log(String tag,String log){
        Log.i(tag,log);
    }
}
