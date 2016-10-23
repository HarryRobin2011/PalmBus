package com.palm.palmbus.model;

import com.palm.palmbus.bean.BusBean;
import com.palm.palmbus.model.base.BaseModel;

import java.util.List;

/**
 * Created by Robin on 2016/10/23.
 */

public class BusListModel extends BaseModel{
    private List<BusBean> lineList;

    public List<BusBean> getLineList() {
        return lineList;
    }

    public void setLineList(List<BusBean> lineList) {
        this.lineList = lineList;
    }
}
