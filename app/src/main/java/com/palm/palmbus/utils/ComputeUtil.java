package com.palm.palmbus.utils;

import com.palm.palmbus.bean.BusInfoBean;
import com.palm.palmbus.bean.StationBean;
import com.palm.palmbus.config.MapFlag;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by Robin on 2016/11/4.
 */

public class ComputeUtil {
    /*
    * 检索当前站的当前线路信息
    * */
    public Map<String,Objects> getStationToBusInfo(String currentStation,String direction,List<StationBean> stationBeanList, List<BusInfoBean> busInfoBeanList){
        HashMap dataMap = new HashMap();
//        for (int i = 0; i<){
//            if(direction.equals(stationBean.getSxx()) && currentStation.equals(stationBean.getStationName())){// 当前方向,当前站
//               dataMap.put(MapFlag.STATION_NEXT,)
//            }
//        }
        return null;

    }
}
