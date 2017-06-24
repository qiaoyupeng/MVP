package com.example.joe.mvp.api.ShowApi;

import com.example.joe.mvp.Common.BizInterface;
import com.example.joe.mvp.Model.News.ShowApiNews;
import com.example.joe.mvp.Model.News.ShowApiResponse;


import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.Query;
import retrofit.Call;

/**
 * Created by joe on 2017/6/1.
 */

/**
 *  参数列表
 *  showapi_appid  易源新闻接口appId
 *  showapi_sign   易源新闻接口密钥
 *  page     页数
 *  channelId      新闻频道id
 *  channelName    新闻频道名称

 * @return
 */

public interface  ShowApi {

    @GET(BizInterface.NEWS_URL)
    @Headers("apikey: " + BizInterface.API_KEY)
    Call<ShowApiResponse<ShowApiNews>> getNewsList(@Query("showapi_appid") String appId,
                                                   @Query("showapi_sign") String key,//接口密钥
                                                   @Query("page") int page,
                                                   @Query("channelId") String channelId,
                                                   @Query("channelName") String channelName);


}
