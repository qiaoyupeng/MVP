package com.example.joe.mvp.View;

import com.example.joe.mvp.Model.News.ShowApiResponse;
import com.example.joe.mvp.Model.Weather.ShowApiWeather;
import com.example.joe.mvp.Model.Weather.WeatherByDate;

import java.util.List;

/**
 * Created by joe on 2017/6/20.
 */

public interface IWeatherView {
    void weatherResult(ShowApiResponse<ShowApiWeather> response);
}
