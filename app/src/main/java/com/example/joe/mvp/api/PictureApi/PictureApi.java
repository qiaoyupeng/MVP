package com.example.joe.mvp.api.PictureApi;

import com.example.joe.mvp.Common.BizInterface;
import com.example.joe.mvp.Model.News.ShowApiResponse;
import com.example.joe.mvp.Model.Picture.ShowApiPicture;

import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.Headers;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by joe on 2017/6/24.
 */

public interface PictureApi {
    /**
     * 美图大全响应
     * @param type "id": 4001, //此id很重要，在【图片查询】接口里将使用此id进行分类查询
    "name": "清纯"
     * @param page 页数
     * @return
     */
    @GET(BizInterface.PICTURES_URL)
    @Headers("apikey: " + BizInterface.API_KEY)
    Observable<ShowApiResponse<ShowApiPicture>> getPictures(@Query("type") String type,
                                                            @Query("page") int page);
}
