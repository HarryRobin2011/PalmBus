package com.palm.palmbus.bean;

import java.util.List;

/**
 * Created by Robin on 2016/11/2.
 */

public class LineInfoBean extends BaseEntrty{
    private List<StationBean> lineList;
    private List<StationBean> stationList;
    private List<StationBean> busTimeList;

    public List<StationBean> getLineList() {
        return lineList;
    }

    public void setLineList(List<StationBean> lineList) {
        this.lineList = lineList;
    }

    public List<StationBean> getStationList() {
        return stationList;
    }

    public void setStationList(List<StationBean> stationList) {
        this.stationList = stationList;
    }

    public List<StationBean> getBusTimeList() {
        return busTimeList;
    }

    public void setBusTimeList(List<StationBean> busTimeList) {
        this.busTimeList = busTimeList;
    }
}
