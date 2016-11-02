package com.palm.palmbus.bean;

import com.baidu.mapapi.search.core.PoiInfo;

import java.util.List;

/**
 * Created by Robin on 2016/11/2.
 */

public class HomeListBean extends BaseEntrty{
    private List<LineInfoBean> lineInfoBeen;// 站点包含的线路
    private PoiInfo stationInfo;// 站点信息

    public List<LineInfoBean> getLineInfoBeen() {
        return lineInfoBeen;
    }

    public void setLineInfoBeen(List<LineInfoBean> lineInfoBeen) {
        this.lineInfoBeen = lineInfoBeen;
    }

    public PoiInfo getStationInfo() {
        return stationInfo;
    }

    public void setStationInfo(PoiInfo stationInfo) {
        this.stationInfo = stationInfo;
    }
}
