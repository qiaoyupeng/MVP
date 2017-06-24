package com.example.joe.mvp.api.ServiceManager;

import android.app.Application;
import android.util.Log;

import com.example.joe.mvp.Common.BizInterface;
import com.example.joe.mvp.Utils.MyApplication;
import com.example.joe.mvp.Utils.NetWork;
import com.example.joe.mvp.api.PictureApi.PictureApi;
import com.example.joe.mvp.api.ShowApi.ShowApi;
import com.example.joe.mvp.api.WeatherApi.WeatherApi;
import com.squareup.okhttp.Cache;
import com.squareup.okhttp.CacheControl;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.File;
import java.io.IOException;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;

/**
 * Created by joe on 2017/6/1.
 */

public class RetrofitService {

    //设缓存有效期为两天
    protected static final long CACHE_STALE_SEC = 60 * 60 * 24 * 2;

    private static String TAG="RetrofitService";
    private static OkHttpClient okHttpClient;
    private volatile static RetrofitService instance=null;
    public static RetrofitService getInstance(){
        if (instance==null){
            synchronized (RetrofitService.class){
                instance=new RetrofitService();
            }
        }
            return instance;
    }

    //新闻api
    private volatile static ShowApi showApi=null;
    public static ShowApi getShowApi(){
        if (showApi==null){
            initOkHttpClient();
            synchronized (RetrofitService.class){
                Retrofit retrofit=new Retrofit.Builder()
                                        .client(okHttpClient)
                                        .baseUrl(BizInterface.SHOW_API)
                                        .addConverterFactory(GsonConverterFactory.create())
                                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())//让service能够作为observable返回
                                        .build();
                showApi=retrofit.create(ShowApi.class);
            }
        }
        return showApi;
    }

    /**
     *美团大全Api
     */
    private volatile static PictureApi pictureApi = null;
    public static PictureApi createPictureApi() {
        if (pictureApi == null) {
            synchronized (RetrofitService.class) {
                if (pictureApi == null) {
                    initOkHttpClient();
                    pictureApi = new Retrofit.Builder()
                            .client(okHttpClient)
                            .baseUrl(BizInterface.API)
                            .addConverterFactory(GsonConverterFactory.create())
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                            .build().create(PictureApi.class);
                }
            }
        }
        return pictureApi;
    }


    //天气api
    public volatile static WeatherApi weatherApi=null;
    public static WeatherApi getWeatherApi(){
        if (weatherApi==null){
            synchronized (RetrofitService.class){
                Retrofit retrofit=new Retrofit.Builder()
                                        .baseUrl(BizInterface.API)
                                        .addConverterFactory(GsonConverterFactory.create())
                                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                                        .build();
                weatherApi=retrofit.create(WeatherApi.class);
            }
        }
        return weatherApi;
    }
    private static void initOkHttpClient() {

        if (okHttpClient == null) {
            File cacheFile=new File(MyApplication.getAppContext().getCacheDir(),"who 知");
            Cache cache=new Cache(cacheFile,1024*1024*100);//设置缓存目录100M
            Interceptor interceptor = new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request request = chain.request();
                    if (!NetWork.isConnected(MyApplication.getAppContext())) {
                        request = request.newBuilder()
                                .cacheControl(CacheControl.FORCE_CACHE)
                                .build();
                        Log.d(TAG, "no netWork");
                    }
                    Response response = chain.proceed(request);
                    if (NetWork.isConnected(MyApplication.getAppContext())) {
                        int maxAge = 0 * 60;//有网络时,设置网络超时时间0小时
                        Log.d(TAG, "hasnetWork" + maxAge);
                        response.newBuilder()
                                .header("Cache-Control", "public, max-age=" + maxAge)
                                .removeHeader("Pragma")//清除头信息，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效
                                .build();
                    } else {
                        Log.d(TAG, "netWork error");
                        response.newBuilder()
                                .header("Cache-Control", "public, only-if-cached, max-stale=" + CACHE_STALE_SEC)
                                .removeHeader("Pragma")
                                .build();
                    }
                    return response;
                }
            };
            okHttpClient=new OkHttpClient();
            okHttpClient.setCache(cache)                     ;
            okHttpClient.interceptors().add(interceptor);
            okHttpClient.networkInterceptors().add(interceptor);
        }

    }

}
