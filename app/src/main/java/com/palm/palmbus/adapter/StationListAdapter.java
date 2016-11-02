package com.palm.palmbus.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.palm.palmbus.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Robin on 2016/11/1.
 */
public class StationListAdapter extends RecyclerView.Adapter<StationListAdapter.StationListViewHolder> {
    private Context mContext;
    private List dataList;

    public StationListAdapter(Context mContext, List dataList) {
        this.mContext = mContext;
        this.dataList = dataList;
    }

    @Override
    public StationListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.station_list_item_layout, parent);
        StationListViewHolder holder = new StationListViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(StationListViewHolder holder, int position) {

    }


    @Override
    public int getItemCount() {
        return dataList == null?0:dataList.size();
    }

    static class StationListViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.bus)
        ImageView bus;
        @BindView(R.id.station_num)
        TextView stationNum;
        @BindView(R.id.station_name)
        TextView stationName;

        StationListViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
