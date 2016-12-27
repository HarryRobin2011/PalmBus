package com.palm.palmbus.bean;

import java.io.Serializable;

/**
 * Created by Robin on 2016/12/27.
 */

public class BaseRespose<T> implements Serializable {
    public String msg;
    public int code;
    public T data;


    @Override
    public String toString() {
      return "BaseRespose{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }

    public boolean success(){
        return "200".equals(code);
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
