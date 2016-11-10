package com.palm.palmbus.adapter.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;


import java.util.HashMap;
import java.util.List;

/**
 * Created by Robin on 2015/1/28.
 */
public abstract class BaseMyAdapter extends BaseAdapter implements View.OnClickListener{
    public List dataList;
    public Context mContext;
    public LayoutInflater mInflater;
    public ViewType viewType = ViewType.SINGLE;
    private OnChildClickListener onChildClickListener;


    public BaseMyAdapter(Context context, List dataList){
        this.dataList = dataList;
        this.mContext = context;
        mInflater = LayoutInflater.from(mContext);
    }

    public void setOnChildClickListener(OnChildClickListener onChildClickListener) {
        this.onChildClickListener = onChildClickListener;
    }

    public void setMoreDataList(List dataList) {
        this.dataList.addAll(dataList);
        notifyDataSetChanged();
    }

    public void setDataList(List dataList) {
        this.dataList = dataList;
        notifyDataSetChanged();
    }

    public class BusinessHolder{
       public int position;
    }

    public void setViewType(ViewType viewType) {
        this.viewType = viewType;
    }

    protected abstract View createCellView();
    public abstract BusinessHolder createCellHolder(View cellView);
    protected abstract void buildData(int position, View cellView, BusinessHolder cellHolder);

    protected View createMoreCellView(int position){
        return null;
    }

    public interface OnChildClickListener{
         void OnChildClick(View view);
    }

    @Override
    public void onClick(View v) {
        if(onChildClickListener == null){
            throw new RuntimeException("onChildClickListener is null");
        }
        onChildClickListener.OnChildClick(v);
    }

    @Override
    public int getCount() {
        return dataList == null? 0:dataList.size();
    }

    @Override
    public Object getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View cellView, ViewGroup parent) {
        BusinessHolder holder = null;
        if(cellView == null){
            if(ViewType.SINGLE.equals(viewType)){
                cellView = createCellView();
            }else{
                cellView = createMoreCellView(position);
            }

            holder = createCellHolder(cellView);
            cellView.setTag(position,holder);
        }else{
            holder = (BusinessHolder) cellView.getTag(position);
        }
        holder.position = position;
        buildData(position,cellView,holder);
        return cellView;
    }


    public enum ViewType{
        SINGLE,
        MORE
    }

}
