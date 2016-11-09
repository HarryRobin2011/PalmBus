package com.palm.palmbus.bean.baidu;

import com.baidu.mapapi.search.busline.BusLineResult;
import com.baidu.mapapi.search.core.PoiInfo;
import com.palm.palmbus.bean.BaseEntrty;

import java.util.List;

/**
 * Created by Robin on 2016/11/9.
 * 首页数据模型
 */

public class StationListBean extends BaseEntrty {
    private PoiInfo station;
    private List<BusLineResult> busLineResultList;

    public PoiInfo getStation() {
        return station;
    }

    public void setStation(PoiInfo station) {
        this.station = station;
    }

    public List<BusLineResult> getBusLineResultList() {
        return busLineResultList;
    }

    public void setBusLineResultList(List<BusLineResult> busLineResultList) {
        this.busLineResultList = busLineResultList;
    }
}
