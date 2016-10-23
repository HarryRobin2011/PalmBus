package com.palm.palmbus.adapter;

import android.content.Context;
import android.view.View;

import com.palm.palmbus.adapter.base.BaseMyAdapter;

import java.util.List;

/**
 * Created by Robin on 2016/10/23.
 */

public class HomePagerAdapter extends BaseMyAdapter {
    public HomePagerAdapter(Context context, List dataList) {
        super(context, dataList);
    }

    @Override
    protected View createCellView() {
        return null;
    }

    @Override
    public BusinessHolder createCellHolder(View cellView) {
        return null;
    }

    @Override
    protected View buildData(int position, View cellView, BusinessHolder cellHolder) {
        return null;
    }
}
