package com.example.joe.mvp.Model.Picture;

import java.util.List;

/**
 * Created by joe on 2017/6/23.
 * 美团大全返回数据列表
 */

public class ShowApiPicture {
    public PageBean pagebean;
    public String ret_code;
    public class PageBean {
        public String allNum;
        public String allPages;
        public String currentPage;
        public String maxResult;
        public List<PictureBody> contentlist;
    }
}
