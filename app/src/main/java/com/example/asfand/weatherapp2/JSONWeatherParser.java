package com.example.asfand.weatherapp2;

/**
 * Created by Asfand on 18-Nov-17.
 */


import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JSONWeatherParser {

    public static Weather getWeather(String data) throws JSONException {
        Weather weather = new Weather();


        JSONObject jsonObject = new JSONObject(data);


        Log.d("info", jsonObject.toString());
        String placeName = jsonObject.getString("name");
        JSONArray array = jsonObject.getJSONArray("weather");
        JSONObject conditionGet = array.getJSONObject(0);
        int mainCondition =Integer.parseInt(conditionGet.getString("id"));
        String condition = conditionGet.getString("description");
        String icon =conditionGet.getString("icon");
        JSONObject weatherData = new JSONObject(jsonObject.getString("main"));
        double temperature = Double.parseDouble(weatherData.getString("temp"));
        int temperatureInteger = (int) (temperature - 273.15);
        String cityI=jsonObject.getString("id");
        Log.d("ID",cityI);

        weather.setName(placeName);
        weather.setCondition(condition);
        weather.setMainCondition(mainCondition);
        weather.setTemperature(temperatureInteger);
        weather.setIcon(icon);
        weather.setCityid(cityI);


        return weather;

    }

   }
