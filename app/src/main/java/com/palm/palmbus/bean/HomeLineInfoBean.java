package com.palm.palmbus.bean;

import java.util.List;

/**
 * Created by Robin on 2016/11/4.
 */

public class HomeLineInfoBean extends BaseEntrty {
    private LineInfoBean infoBean;// 线路详情
    private List<BusInfoBean> busInfoBeanList;// 当前线路公交车详情；
    private String Sxx;// 当前方向，默认0

    public LineInfoBean getInfoBean() {
        return infoBean;
    }

    public void setInfoBean(LineInfoBean infoBean) {
        this.infoBean = infoBean;
    }

    public List<BusInfoBean> getBusInfoBeanList() {
        return busInfoBeanList;
    }

    public void setBusInfoBeanList(List<BusInfoBean> busInfoBeanList) {
        this.busInfoBeanList = busInfoBeanList;
    }

    public String getSxx() {
        return Sxx;
    }

    public void setSxx(String sxx) {
        Sxx = sxx;
    }
}
