package com.palm.palmbus.ui.base;

import android.content.Context;

import com.palm.palmbus.ui.baserx.RxManager;

/**
 * Created by Robin on 2016/12/27.
 */

public abstract class BasePresenter<T, E> {
    public T mView;
    public E mModel;
    public Context mContext;
    public RxManager mRxManage = new RxManager();

    public void SetVM(T v, E m) {
        this.mView = v;
        this.mModel = m;
        start();
    }

    public abstract void start();

    public void onDestroy() {
        mRxManage.clear();
    }
}
