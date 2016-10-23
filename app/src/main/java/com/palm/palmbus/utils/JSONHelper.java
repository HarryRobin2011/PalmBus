package com.palm.palmbus.utils;


import com.google.gson.Gson;

import java.lang.reflect.Type;

/**
 * Created with IntelliJ IDEA.
 * User: Robin
 * Date: 14-4-24
 * Time: 上午9:54
 * To change this template use File | Settings | File Templates.
 */
public class JSONHelper {
    public final static Gson gson = new Gson();


    public static String toJSONString(Object o){
       return gson.toJson(o);
    }

    public static <T> T fromJSONObject(String json, Type type){
      return gson.fromJson(json,type);
    }

    public static <T> T fromJSONObject(String json, Class<T> cls){
      return gson.fromJson(json,cls);
    }


}
