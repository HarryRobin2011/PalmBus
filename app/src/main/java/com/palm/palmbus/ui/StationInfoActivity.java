package com.palm.palmbus.ui;

import android.content.Intent;
import android.text.LoginFilter;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baidu.mapapi.search.busline.BusLineResult;
import com.baidu.mapapi.search.busline.BusLineSearch;
import com.baidu.mapapi.search.busline.BusLineSearchOption;
import com.baidu.mapapi.search.busline.OnGetBusLineSearchResultListener;
import com.baidu.mapapi.search.poi.PoiCitySearchOption;
import com.palm.palmbus.R;
import com.palm.palmbus.adapter.StationListAdapter;
import com.palm.palmbus.config.IntentFlag;
import com.palm.palmbus.config.LogUtil;
import com.palm.palmbus.ui.base.BasePalmActivity;

import butterknife.BindView;

/**
 * Created by Robin on 2016/11/1.
 */

public class StationInfoActivity extends BasePalmActivity implements OnGetBusLineSearchResultListener{
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.menu)
    ImageView menu;
    @BindView(R.id.title_layout)
    LinearLayout titleLayout;
    private StationListAdapter stationListAdapter;
    private BusLineSearch mBusLineSearch;
    private String busUid;

    @Override
    protected int initView() {
        return R.layout.station_inf_layout;
    }

    @Override
    protected void initData() {
        mBusLineSearch = BusLineSearch.newInstance();
        busUid = getIntent().getStringExtra(IntentFlag.KEY);
    }

    @Override
    protected void initOperation() {
        if(!isLocation()){
            alert(R.string.location_error);
            return;
        }
       BusLineSearchOption option = new BusLineSearchOption();
        option.city(bdLocation.getCity()).uid(busUid);
        mBusLineSearch.searchBusLine(option);
        mBusLineSearch.setOnGetBusLineSearchResultListener(this);
    }

    @Override
    public void onGetBusLineResult(BusLineResult busLineResult) {
       LogUtil.logOutPut(busLineResult.getBusLineName());
    }
}
