package com.palm.palmbus.ui.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import butterknife.ButterKnife;

/**
 * Created by Robin on 2016/10/23.
 */

public abstract class BaseFragment extends Fragment{
    public LayoutInflater mInflater;
    public BasePalmActivity activity;
    public Context mContext;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.activity = (BasePalmActivity) getActivity();
        this.mContext = getActivity().getApplicationContext();
        mInflater = LayoutInflater.from(mContext);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(mContext).inflate(initResource(),null);
        ButterKnife.bind(this,view);
        initData();
        initOperation();
        return view;
    }


    protected abstract int initResource();

    protected abstract void initData();

    protected abstract void initOperation();

    public void showToast(int resId){
        Toast.makeText(mContext,resId,Toast.LENGTH_SHORT).show();
    }

}
