package com.palm.palmbus.bean;

/**
 * Created by Robin on 2016/11/4.
 *   "Speed": "0.00",
 "busCode": 2678,
 "cph": "2678",
 "isTravel": 2,
 "lat": "37.371277",
 "lineCode": 1,
 "lon": "118.056095",
 "passStation": 10,
 "sxx": 0
 */
public class BusInfoBean extends BaseEntrty{
    private String Speed;
    private int busCode;
    private String cph;
    private int isTravel;
    private String lat;
    private int lineCode;
    private String lon;
    private int passStation;
    private int sxx;


    public String getSpeed() {
        return Speed;
    }

    public void setSpeed(String speed) {
        Speed = speed;
    }

    public int getBusCode() {
        return busCode;
    }

    public void setBusCode(int busCode) {
        this.busCode = busCode;
    }

    public String getCph() {
        return cph;
    }

    public void setCph(String cph) {
        this.cph = cph;
    }

    public int getIsTravel() {
        return isTravel;
    }

    public void setIsTravel(int isTravel) {
        this.isTravel = isTravel;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public int getLineCode() {
        return lineCode;
    }

    public void setLineCode(int lineCode) {
        this.lineCode = lineCode;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public int getPassStation() {
        return passStation;
    }

    public void setPassStation(int passStation) {
        this.passStation = passStation;
    }

    public int getSxx() {
        return sxx;
    }

    public void setSxx(int sxx) {
        this.sxx = sxx;
    }
}
