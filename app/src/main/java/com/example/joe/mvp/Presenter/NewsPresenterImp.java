package com.example.joe.mvp.Presenter;

import android.util.Log;

import com.example.joe.mvp.Common.BizInterface;
import com.example.joe.mvp.Model.News.ShowApiNews;
import com.example.joe.mvp.Model.News.ShowApiResponse;
import com.example.joe.mvp.View.INewsView;
import com.example.joe.mvp.api.ServiceManager.RetrofitService;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by joe on 2017/6/7.
 */

public class NewsPresenterImp {
    private String TAG="NewsPresenterImp";
    private INewsView newsView;

    public NewsPresenterImp(INewsView view){
        newsView=view;
    }

    public void getNewsList(String channelId,String channelName,int page){
        //Retrofit官方响应方式
        Call<ShowApiResponse<ShowApiNews>> call= RetrofitService.getInstance().getShowApi()
                                                    .getNewsList(BizInterface.SHOW_API_APPID,BizInterface.SHOW_API_KEY,
                                                                 page,channelId,channelName);
        //异步请求
        call.enqueue(new Callback<ShowApiResponse<ShowApiNews>>() {
            @Override
            public void onResponse(Response<ShowApiResponse<ShowApiNews>> response, Retrofit retrofit) {

                if(response.body()!=null ){
                    Log.d(TAG,response.message() + response.code() + response.body().showapi_res_code
                            + response.body().showapi_res_error);
                    newsView.newsResult(response.body().showapi_res_body.pagebean.contentlist);
                }
                else {
                    Log.i(TAG,"api请求错误!");
                }
            }

            @Override
            public void onFailure(Throwable t) {
              //  listener.onFailure(t);
            }
        });
    }
}
