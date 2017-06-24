package com.example.joe.mvp.Model;

/**
 * Created by joe on 2017/6/7.
 */

public interface OnNetRequestListener<T> {

    /*
    @param T 数据请求成功时返回的数据类型
    */
    void onStart();
    void onSuccess(T data);
    void onFailure(Throwable t );
    void onFinish();
}
