package com.example.joe.mvp.Presenter;

import android.util.Log;

import com.example.joe.mvp.Adapter.WeatherListAdapter;
import com.example.joe.mvp.Model.News.ShowApiResponse;
import com.example.joe.mvp.Model.Weather.ShowApiWeather;
import com.example.joe.mvp.Model.Weather.WeatherByDate;
import com.example.joe.mvp.View.IWeatherView;
import com.example.joe.mvp.api.ServiceManager.RetrofitService;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action0;
import rx.schedulers.Schedulers;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by joe on 2017/6/19.
 */

public class WeatherPresenterImp {
    String TAG="WeatherPresenterImp";
    IWeatherView weatherView;
    List<WeatherByDate> list;
    public WeatherPresenterImp(IWeatherView weatherView){
        this.weatherView=weatherView;
    }
    public void getWeather(String area,String needMoreDay){

        final Observable<ShowApiResponse<ShowApiWeather>> observable= RetrofitService.getInstance().getWeatherApi()
                                         .getWeather(area,needMoreDay);
        observable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSubscribe(new Action0() {
                        @Override
                        public void call() {
                            //执行开始操作
                        }
                    })
                    .subscribe(new Subscriber<ShowApiResponse<ShowApiWeather>>() {
                        @Override
                        public void onCompleted() {
                            //执行成功时回调
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.d(TAG,"onError");
                        }

                        @Override
                        public void onNext(ShowApiResponse<ShowApiWeather> response) {
                            /*list.add(response.showapi_res_body.f1);
                            list.add(response.showapi_res_body.f2);
                            list.add(response.showapi_res_body.f3);
                            list.add(response.showapi_res_body.f4);
                            list.add(response.showapi_res_body.f5);*/
                            weatherView.weatherResult(response);
                            Log.d(TAG,response.showapi_res_code);
                        }
                    });
    }
}
