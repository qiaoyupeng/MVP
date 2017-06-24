package com.example.joe.mvp.Model.News;

/**
 * Created by joe on 2017/6/1.
 */

//易源api通用响应数据

public class ShowApiResponse<T> {
    public String showapi_res_code;
    public String showapi_res_error;
    public T showapi_res_body;
}