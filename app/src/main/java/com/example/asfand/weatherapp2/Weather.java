package com.example.asfand.weatherapp2;

/**
 * Created by Asfand on 18-Nov-17.
 */

public class Weather {
    String name;
    String condition;
    int mainCondition;
    int temperature;
    String icon;
    String cityid;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getMainCondition() {
        return mainCondition;
    }

    public void setMainCondition(int mainCondition) {
        this.mainCondition = mainCondition;
    }

    public String getCityid() {
        return cityid;
    }

    public void setCityid(String cityid) {
        this.cityid = cityid;
    }
}
