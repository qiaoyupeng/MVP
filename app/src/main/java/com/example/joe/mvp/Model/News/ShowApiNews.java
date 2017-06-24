package com.example.joe.mvp.Model.News;

import java.util.List;

/**
 * Created by joe on 2017/6/17.
 */
/*
*新闻列表实体类
* */

public class ShowApiNews {
    public PageBean pagebean;
    public String ret_code;
    public class PageBean {
        public String allNum;
        public String allPages;
        public String currentPage;
        public String maxResult;
        public List<NewsBody> contentlist;
    }
}