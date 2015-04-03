package com.example.notefan.myapplication.bean;

import android.view.VelocityTracker;

/**
 * Created by notefan on 15/3/26.
 */
public class TodayWeather {
    private String city;
    private String updatetime;
    private String wendu;
    private String shidu;
    private String pm25;
    private String quality;
    private String fengxiang;
    private String fengli;
    private String date;
    private String high;
    private String low;
    private String type;

    public void setCity(String City){
        city = City;
    }
    public void setUpdatetime(String Updatetime){
        updatetime = Updatetime;
    }
    public void setWendu(String Wendu){
        wendu = Wendu;
    }
    public void setShidu(String Shidu){
        shidu = Shidu;
    }
    public void setPm25(String Pm25){
        pm25 = Pm25;
    }
    public void setQuality(String Quality){
        quality = Quality;
    }
    public void setFengxiang(String Fengxiang){
        fengxiang = Fengxiang;
    }
    public void setFengli(String Fengli){
        fengli = Fengli;
    }
    public void setData(String Data){
        date = Data;
    }
    public void setHigh(String High){
        high = High;
    }
    public void setLow(String Low){
        low = Low;
    }
    public void setType(String Type){
        type = Type;
    }

    public String getCity(){
        return city;
    }
    public String getUpdatetime(){
        return updatetime;
    }
    public String getWendu(){
        return wendu;
    }
    public String getShidu(){
        return shidu;
    }
    public String getPm25(){
        return pm25;
    }
    public String getQuality(){
        return quality;
    }
    public String getFengxiang(){
        return fengxiang;
    }
    public String getFengli(){
        return fengli;
    }
    public String getData(){
        return date;
    }
    public String getHigh(){
        return high;
    }
    public String getLow(){
        return low;
    }
    public String getType(){
        return type;
    }

    @Override
    public String toString() {
        return "TodayWeather{" +
                "city='" + city + '\'' +
                ",updatetime='" + updatetime + '\'' +
                ",wendu='" + wendu + '\'' +
                ",shidu='" + shidu + '\'' +
                ",pm25='" + pm25 + '\'' +
                ",quality='" + quality + '\'' +
                ",fengxiang='" + fengxiang + '\'' +
                ",fengli='" + fengli + '\'' +
                ",date='" + date + '\'' +
                ",high='" + high + '\'' +
                ",low='" + low + '\'' +
                ",type='" + type + '\'' +
                '}';
    }
}


