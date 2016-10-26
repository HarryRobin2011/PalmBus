package com.palm.palmbus.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.palm.palmbus.R;
import com.palm.palmbus.adapter.base.BaseMyAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Robin on 2016/10/23.
 */

public class HomePagerAdapter extends BaseMyAdapter {
    private ViewHolder viewHolder;
    public HomePagerAdapter(Context context, List dataList) {
        super(context, dataList);
    }


    @Override
    protected View createCellView() {
        return mInflater.inflate(R.layout.home_pager_item_layout, null);
    }

    @Override
    public BusinessHolder createCellHolder(View cellView) {
        viewHolder = new ViewHolder(cellView);
        return viewHolder;
    }

    @Override
    protected View buildData(int position, View cellView, BusinessHolder cellHolder) {

        return null;
    }

    public class ViewHolder extends BusinessHolder{
        @BindView(R.id.location)
        Button location;
        @BindView(R.id.station)
        TextView station;
        @BindView(R.id.bus)
        TextView bus;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
