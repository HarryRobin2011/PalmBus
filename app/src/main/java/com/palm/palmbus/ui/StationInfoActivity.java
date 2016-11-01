package com.palm.palmbus.ui;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.palm.palmbus.R;
import com.palm.palmbus.adapter.StationListAdapter;
import com.palm.palmbus.ui.base.BasePalmActivity;

import butterknife.BindView;

/**
 * Created by Robin on 2016/11/1.
 */

public class StationInfoActivity extends BasePalmActivity {
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.menu)
    ImageView menu;
    @BindView(R.id.title_layout)
    LinearLayout titleLayout;
    private StationListAdapter stationListAdapter;

    @Override
    protected int initView() {
        return R.layout.station_inf_layout;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initOperation() {

    }

}
