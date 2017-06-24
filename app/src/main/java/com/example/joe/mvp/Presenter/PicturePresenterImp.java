package com.example.joe.mvp.Presenter;

import android.support.v7.widget.LinearLayoutCompat;

import com.example.joe.mvp.Model.News.ShowApiResponse;
import com.example.joe.mvp.Model.Picture.ShowApiPicture;
import com.example.joe.mvp.View.IPctureView;
import com.example.joe.mvp.api.ServiceManager.RetrofitService;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.schedulers.Schedulers;

/**
 * Created by joe on 2017/6/23.
 */

public class PicturePresenterImp {

    private IPctureView pctureView;
    public PicturePresenterImp(IPctureView pctureView){
        this.pctureView=pctureView;
    }


    public void getPictureList(String type,int page){
        Observable<ShowApiResponse<ShowApiPicture>> observable= RetrofitService.getInstance()
                                                                .createPictureApi()
                                                                .getPictures(type,page);

        observable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSubscribe(new Action0() {
                        @Override
                        public void call() {

                        }
                    })
                .subscribe(new Subscriber<ShowApiResponse<ShowApiPicture>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(ShowApiResponse<ShowApiPicture> response) {
                        if(response!=null){
                            pctureView.pictureResult(response.showapi_res_body.pagebean.contentlist);
                        }
                    }
                });

    }

}
