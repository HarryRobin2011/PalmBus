package com.palm.palmbus.ui.main.fragment;

import com.palm.palmbus.R;
import com.palm.palmbus.ui.base.BaseFragment;

/**
 * Created by Robin on 2016/12/27.
 */

public class FineFragment extends BaseFragment {

    @Override
    protected int getLayoutResource() {
        return R.layout.fine_fragment_layout;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected void initView() {

    }

    public static FineFragment newInstance(){
        FineFragment fineFragment = new FineFragment();
        return fineFragment;
    }
}
