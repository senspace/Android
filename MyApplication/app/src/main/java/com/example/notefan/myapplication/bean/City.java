package com.example.notefan.myapplication.bean;

/**
 * Created by notefan on 15/4/2.
 */
public class City {

    private String province;
    private String city;
    private String number;
    private String firstPY;
    private String allPY;
    private String allFirstPY;

    public City(String province, String city, String number, String firstPY, String allPY, String allFirstPY){
        this.province = province;
        this.city = city;
        this.number = firstPY;
        this.allPY = allPY;
        this.allFirstPY = allFirstPY;
    }

    public void setProvince(String province){
        this.province = province;
    }

    public void setCity(String city){
        this.city = city;
    }

    public String getCity(){
        return city;
    }
}
