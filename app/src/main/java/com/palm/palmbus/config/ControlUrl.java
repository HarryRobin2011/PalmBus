package com.palm.palmbus.config;

/**
 * Created by Robin on 2016/10/23.
 */
public class ControlUrl {
    //获取公交车线路
    public static final String PALM_GET_BUS_LIST = "http://60.215.8.42:4001/sdhyschedule/PhoneQueryAction!getLineInfo.shtml";

    //获取线路站点
    public static final String PALM_GET_LINE_STATION = "http://60.215.8.42:4001/sdhyschedule/PhoneQueryAction!getLineStation.shtml";
}
