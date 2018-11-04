package com.example.asfand.weatherapp2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.asfand.weatherapp.R;

import org.json.JSONException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {


    //JSONWeatherTask task;
    ListView myListView;
    ArrayList<dayrowshow> myRowItems;
    static TextView cityview;
    static TextView dayview;
    static ImageView currentview;
    static TextView tempview;
    static TextView conditionview;
    String location;
    String unitset="c";
    boolean cityadded=true;
    ConstraintLayout root;



    String city= "city name";
    String unit = "unit set";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cityadded=true;
        SharedPreferences list = PreferenceManager.getDefaultSharedPreferences(this);

        if(list.getString(city,"").equals(""))
        {
            cityadded=false;
        }

        if(cityadded==false)
        {
            Intent i = new Intent(this, SettingsActivity.class);
            startActivity(i);
            finish();
        }
        else if(cityadded==true) {


            root = (ConstraintLayout) findViewById(R.id.mainactivitylayout);
            cityview = (TextView) findViewById(R.id.cityname);
            dayview = (TextView) findViewById(R.id.daytext);
            tempview = (TextView) findViewById(R.id.temperature);
            conditionview = (TextView) findViewById(R.id.condition);


            currentview = (ImageView) findViewById(R.id.currentimage);


            myListView = (ListView) findViewById(R.id.dayslistView);


            myRowItems = new ArrayList<dayrowshow>();


            fillArrayList();

            CustomAdapter myAdapter = new CustomAdapter(getApplicationContext(), myRowItems);

            myListView.setAdapter(myAdapter);


            location = list.getString(city, "");
            Log.d("city", location);
            unitset = list.getString(unit, "");


            this.onRefreshClick(findViewById(R.id.refreshbtn));
        }
    }

    public void onRefreshClick(View viewById) {
        new JSONWeatherTask().execute();
    }


    public void onSettingsclick(View view)
    {
        Intent b = new Intent(this, SettingsActivity.class);
        startActivity(b);
        finish();
    }



    private void fillArrayList() {

        dayrowshow row_one = new dayrowshow( );
        row_one.setDay("Monday");
        row_one.setConditionday("Cloudy");
        row_one.setTemperatureofday("33");
        row_one.setConditionimageofday(R.mipmap.ic_launcher);
        myRowItems.add( row_one );

        dayrowshow row_two = new dayrowshow( );
        row_two.setDay("Monday");
        row_two.setConditionday("Cloudy");
        row_two.setTemperatureofday("33");
        row_two.setConditionimageofday(R.mipmap.ic_launcher);
        myRowItems.add( row_two );

        dayrowshow row_three = new dayrowshow( );
        row_three.setDay("Monday");
        row_three.setConditionday("Cloudy");
        row_three.setTemperatureofday("33");
        row_three.setConditionimageofday(R.mipmap.ic_launcher);
        myRowItems.add( row_three );

        dayrowshow row_four = new dayrowshow( );
        row_four.setDay("Monday");
        row_four.setConditionday("Cloudy");
        row_four.setTemperatureofday("33");
        row_four.setConditionimageofday(R.mipmap.ic_launcher);
        myRowItems.add( row_four );

        dayrowshow row_five = new dayrowshow( );
        row_five.setDay("Monday");
        row_five.setConditionday("Cloudy1");
        row_five.setTemperatureofday("33");
        row_five.setConditionimageofday(R.mipmap.ic_launcher);
        myRowItems.add( row_five );

        dayrowshow row_six = new dayrowshow( );
        row_six.setDay("Monday");
        row_six.setConditionday("Cloudy");
        row_six.setTemperatureofday("33");
        row_six.setConditionimageofday(R.mipmap.ic_launcher);
        myRowItems.add( row_six );

        dayrowshow row_seven = new dayrowshow( );
        row_seven.setDay("Monday");
        row_seven.setConditionday("Cloudy");
        row_seven.setTemperatureofday("33");
        row_seven.setConditionimageofday(R.mipmap.ic_launcher);
        myRowItems.add( row_seven );


    }



    private class JSONWeatherTask extends AsyncTask<String, Void, Weather> {

        @Override
        protected Weather doInBackground(String... params) {
            Weather weather = new Weather();

            String data = ((new WeatherHttpClient()).getWeatherData(location));

            try {
                weather = JSONWeatherParser.getWeather(data);


            } catch (JSONException e) {
                e.printStackTrace();
            }
            return weather;

        }


        @Override
        protected void onPostExecute(Weather weather) {
            super.onPostExecute(weather);

            Calendar calender = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("EEEE, h:mm a");
            String formatted_date = sdf.format(calender.getTime());



            switch (unitset) {
                case "C":
                    tempview.setText(String.valueOf(weather.getTemperature()) + "°C");
                    break;
                case "F":
                    double f = (weather.getTemperature() * 1.8) + 32;
                    int fahr = (int) f;
                    tempview.setText(String.valueOf(fahr) + "°F");
                    break;
                default:
                    tempview.setText(String.valueOf(weather.getTemperature()) + "°C");

            }


            cityview.setText(weather.getName());
            conditionview.setText(weather.getCondition());
            dayview.setText(formatted_date);


            String iconview = weather.getIcon();
            Log.d("icon", iconview);
            char time = iconview.charAt(iconview.length() - 1);
            int conditionicon = weather.getMainCondition();

            if (conditionicon >= 300 && conditionicon <= 321)//rain
            {
                currentview.setImageResource(R.drawable.e);

            } else if (conditionicon >= 500 && conditionicon <= 531)//rain
            {
                currentview.setImageResource(R.drawable.e);
            } else if (conditionicon >= 600 && conditionicon <= 622)//snow
            {
                currentview.setImageResource(R.drawable.h);
            } else if (conditionicon >= 200 && conditionicon <= 232)//storm
            {
                currentview.setImageResource(R.drawable.i);
            } else if (conditionicon >= 900 && conditionicon <= 906)//extreme weather
            {
                currentview.setImageResource(R.drawable.j);
            } else if (conditionicon >= 801 && conditionicon <= 804)//clouds
            {
                if (weather.getCondition() == "few clouds" || weather.getCondition() == "broken clouds") {
                    if (time == 'd') {
                        currentview.setImageResource(R.drawable.c);
                    } else
                        currentview.setImageResource(R.drawable.g);
                } else {
                    currentview.setImageResource(R.drawable.a);
                }

            } else if (conditionicon >= 701 && conditionicon <= 781)//haze,dust,fog,smog etc
            {
                currentview.setImageResource(R.drawable.d);
            } else {//clear
                if (time == 'd') {
                    currentview.setImageResource(R.drawable.b);
                } else {
                    currentview.setImageResource(R.drawable.f);
                }
            }


            switch (time) {
                case 'd':
                    root.setBackgroundResource(R.drawable.day);
                    tempview.setTextColor(Color.BLACK);
                    cityview.setTextColor(Color.BLACK);
                    conditionview.setTextColor(Color.BLACK);
                    dayview.setTextColor(Color.BLACK);
                    break;
                case 'n':
                    root.setBackgroundResource(R.drawable.night);
                    tempview.setTextColor(Color.WHITE);
                    cityview.setTextColor(Color.WHITE);
                    conditionview.setTextColor(Color.WHITE);
                    dayview.setTextColor(Color.WHITE);
                    break;
                default:
                    root.setBackgroundResource(R.drawable.day);
                    tempview.setTextColor(Color.BLACK);
                    cityview.setTextColor(Color.BLACK);
                    conditionview.setTextColor(Color.BLACK);
                    dayview.setTextColor(Color.BLACK);


            }


        }









  }

}
