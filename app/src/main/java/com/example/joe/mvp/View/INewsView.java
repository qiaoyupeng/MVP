package com.example.joe.mvp.View;

import com.example.joe.mvp.Model.News.NewsBody;
import com.example.joe.mvp.Presenter.NewsPresenterImp;

import java.util.List;

/**
 * Created by joe on 2017/6/13.
 */

public interface INewsView {
    void newsResult(List<NewsBody> list);
}
