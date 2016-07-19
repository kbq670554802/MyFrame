package com.kbq.myframe.model.bean;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.kbq.myframe.BR;

/**
 * Created by KBQ on 16/7/15.
 * IP信息实体类
 */
public class IpInfo extends BaseObservable{
    public String country;
    public String country_id;
    public String area;
    public String area_id;
    public String ip;

    @Bindable
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
        notifyPropertyChanged(BR.country);
    }
    @Bindable
    public String getCountry_id() {
        return country_id;
    }

    public void setCountry_id(String country_id) {
        this.country_id = country_id;
        notifyPropertyChanged(BR.country_id);
    }
    @Bindable
    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
        notifyPropertyChanged(BR.area);
    }
    @Bindable
    public String getArea_id() {
        return area_id;
    }

    public void setArea_id(String area_id) {
        this.area_id = area_id;
        notifyPropertyChanged(BR.area_id);
    }
    @Bindable
    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
        notifyPropertyChanged(BR.ip);
    }
}
