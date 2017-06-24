package com.example.joe.mvp.Model.Weather;

/**
 * Created by joe on 2017/6/20.
 */

public class ShowApiWeather {
    public WeatherByDate f1;
    public WeatherByDate f2;
    public WeatherByDate f3;
    public WeatherByDate f4;
    public WeatherByDate f5;
    public ShowApiWeatherNow now;//现在的天气预报
    public class ShowApiWeatherNow{
        public String temperature;//实时气温,26℃
        public AqiDetail aqiDetail;
    }

}
