package com.yan.www.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/12/2 0002.
 */
public class Address implements Serializable {
    private static final long serialVersionUID = 2529135899547199617L;
    private String province;
    private String city;
    private String detail;

    public Address() {
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    @Override
    public String toString() {
        return "Address{" +
                "province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", detail='" + detail + '\'' +
                '}';
    }
}
