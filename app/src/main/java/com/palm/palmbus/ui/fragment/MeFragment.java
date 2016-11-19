package com.palm.palmbus.ui.fragment;


import com.palm.palmbus.R;
import com.palm.palmbus.ui.base.BaseFragment;

/**
 * Created by Robin on 2016/11/19.
 */

public class MeFragment extends BaseFragment {
    @Override
    protected int initResource() {
        return R.layout.me_layout;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initOperation() {

    }

    public static MeFragment newInstance() {
        MeFragment meFragment = new MeFragment();
        return meFragment;
    }
}
