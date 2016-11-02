package com.palm.palmbus.ui.base;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.palm.palmbus.config.Config;
import com.palm.palmbus.config.SharedTag;
import com.palm.palmbus.utils.JSONHelper;

import butterknife.ButterKnife;

/**
 * Created by Robin on 2016/10/23.
 */

public abstract class BasePalmActivity extends AppCompatActivity {
    public LayoutInflater mLayoutInflater;
    public Context mContext;
    public BasePalmActivity mActivity;
    private SharedPreferences palmSp;
    public BDLocation bdLocation;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int layoutResID = initView();
        setContentView(layoutResID);
        ButterKnife.bind(this);
        this.mContext = getApplicationContext();
        this.mActivity = this;
        mLayoutInflater = LayoutInflater.from(mContext);
        palmSp = getSharedPreferences(Config.SP_TAG,Context.MODE_PRIVATE);
        isLocation();
        initData();
        initOperation();
    }

    public void saveBdLocation(BDLocation location){
        palmSp.edit().putString(SharedTag.BD, JSONHelper.toJSONString(location)).commit();
    }

    public boolean isLocation(){
       String s = palmSp.getString(SharedTag.BD,"");
        if(s == null || s.isEmpty()){
            return false;
        }else{
            bdLocation = JSONHelper.fromJSONObject(s,BDLocation.class);
            return true;
        }
    }

    protected abstract int initView();

    protected abstract void initData();

    protected abstract void initOperation();


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void alert(int resId){
        Toast.makeText(mContext,resId,Toast.LENGTH_SHORT).show();
    }
}
