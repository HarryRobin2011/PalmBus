package com.palm.palmbus.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baidu.mapapi.search.core.PoiInfo;
import com.palm.palmbus.R;
import com.palm.palmbus.adapter.base.BaseMyAdapter;
import com.palm.palmbus.bean.HomeLineInfoBean;
import com.palm.palmbus.bean.HomeListBean;
import com.palm.palmbus.bean.LineInfoBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Robin on 2016/10/23.
 */

public class HomePagerAdapter extends BaseMyAdapter {
    private StationHolder stationHolder;
    private OnChildClickListener onChildClickListener;

    public HomePagerAdapter(Context context, List dataList) {
        super(context, dataList);
    }


    @Override
    protected View createCellView() {
        return mInflater.inflate(R.layout.home_pager_item_layout, null);
    }

    @Override
    public BusinessHolder createCellHolder(View cellView) {
        stationHolder = new StationHolder(cellView);
        return stationHolder;
    }

    @Override
    protected void buildData(int position, View cellView, BusinessHolder cellHolder) {
        StationHolder holder = (StationHolder) cellHolder;
        HomeListBean homeListBean = (HomeListBean) dataList.get(position);
        PoiInfo poiInfo = homeListBean.getStationInfo();
        holder.station.setText(poiInfo.name);
        holder.bus.setText("途径线路："+poiInfo.address);
        holder.homePageItemLayout.setTag(position);
        holder.homePageItemLayout.setOnClickListener(this);
        if(position == 0){
//            for (HomeLineInfoBean homeLineInfoBean:homeListBean.getHomeLineInfoBeanList()){
//                LineInfoBean lineInfoBean = homeLineInfoBean.getInfoBean();
//                View view = LayoutInflater.from(mContext).inflate(R.layout.bus_line_item_layout,null);
//                TextView busLine = (TextView) view.findViewById(R.id.bus_line);
//                TextView direction = (TextView) view.findViewById(R.id.direction);
//                TextView stationNext = (TextView) view.findViewById(R.id.station_next);
//                TextView busOne = (TextView) view.findViewById(R.id.bus_one);
//                TextView busOther = (TextView) view.findViewById(R.id.bus_other);
//                ImageView change = (ImageView) view.findViewById(R.id.change_direction);
//                busLine.setText(lineInfoBean.getLineList().get(0).getLineName());
//                direction.setText(lineInfoBean.getLineList().get(0).getEndStation());
//                holder.content.addView(view);
//            }
        }
    }

    public class StationHolder extends BusinessHolder {
        @BindView(R.id.location)
        Button location;
        @BindView(R.id.station)
        TextView station;
        @BindView(R.id.bus)
        TextView bus;
        @BindView(R.id.home_page_item_layout)
        LinearLayout homePageItemLayout;
        @BindView(R.id.content)
        LinearLayout content;

        StationHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
