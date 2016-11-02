package com.palm.palmbus.model;

import com.palm.palmbus.bean.StationBean;
import com.palm.palmbus.model.base.BaseModel;

import java.util.List;

/**
 * Created by Robin on 2016/10/23.
 */

public class BusListModel extends BaseModel{
    private List<StationBean> lineList;

    public List<StationBean> getLineList() {
        return lineList;
    }

    public void setLineList(List<StationBean> lineList) {
        this.lineList = lineList;
    }
}
