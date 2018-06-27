package com.example.wz1.myapplication.yuanxing;

/**
 * Created by Administrator on 2018-05-23.
 * <p>
 * by author wz
 * <p>
 * com.example.wz1.myapplication.yuanxing
 */

public class Address {
    public String city;

    public String district;

    public String street;

    @Override
    public String toString() {
        return "Address{" +
                "city='" + city + '\'' +
                ", district='" + district + '\'' +
                ", street='" + street + '\'' +
                '}';
    }

    public Address(String city, String district, String street) {
        this.city = city;
        this.district = district;
        this.street = street;
    }


}
