package com.palm.palmbus.ui.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;

import butterknife.ButterKnife;

/**
 * Created by Robin on 2016/10/23.
 */

public abstract class BasePalmActivity extends AppCompatActivity {
    public LayoutInflater mLayoutInflater;
    public Context mContext;
    public BasePalmActivity mActivity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int layoutResID = initView();
        setContentView(layoutResID);
        ButterKnife.bind(this);
        initData();
        initOperation();
        this.mContext = getApplicationContext();
        this.mActivity = this;
        mLayoutInflater = LayoutInflater.from(mContext);
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
}
