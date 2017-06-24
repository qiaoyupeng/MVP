package com.example.joe.mvp.api.WeatherApi;

import com.example.joe.mvp.Common.BizInterface;
import com.example.joe.mvp.Model.News.ShowApiResponse;
import com.example.joe.mvp.Model.Weather.ShowApiWeather;

import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by joe on 2017/6/20.
 *天气api
 * area，地名参数
 * needMoreDay,是否需要未来几天的天气，"1"为需要,"0"为不需要
 */

public interface WeatherApi {
    @GET(BizInterface.WEATHER_URL)
    @Headers("apikey: "+BizInterface.API_KEY)
    Observable<ShowApiResponse<ShowApiWeather>> getWeather(@Query("area") String area,
                                                            @Query("needMoreDay") String needMoreDay
                                                            );

    }

