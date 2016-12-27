package com.palm.palmbus.config;

import android.content.Context;
import android.content.pm.ApplicationInfo;

/**
 * Created by Robin on 2016/12/27.
 */

public class BuildConfig {

    /**
     * 判断当前应用是否是debug状态
     */

    public static boolean isApkInDebug(Context context) {
        try {
            ApplicationInfo info = context.getApplicationInfo();
            return (info.flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
        } catch (Exception e) {
            return false;
        }
    }
}
