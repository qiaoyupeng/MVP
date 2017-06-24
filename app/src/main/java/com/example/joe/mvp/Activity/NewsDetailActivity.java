package com.example.joe.mvp.Activity;

import android.graphics.Bitmap;

import android.support.design.widget.CollapsingToolbarLayout;

import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import com.example.joe.mvp.R;
import com.example.joe.mvp.Utils.GlideUtil;
import com.example.joe.mvp.api.ShowApi.ShowApi;
import com.rey.material.widget.ProgressView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by joe on 2017/6/3.
 */

public class NewsDetailActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.progress)
    ProgressView processView;
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout mCollapsingToolBarLayout;
    @BindView(R.id.iv_detail)
    ImageView imageView;
    @BindView(R.id.webview)
    WebView webView;

    private static final String TAG="NewsDetailActivity";
    //点击新闻item传递过来的新闻链接
    public static final String ARG_NEWS_URL="arg_news_url";
    //传递过来的新闻图片
    public static final String ARG_NEWS_PIC="arg_news_pic";
    //传递过来的新闻标题
    public static final String ARG_NEWS_TITLE="arg_news_title";

    private String mUrl="";
    private String mPic="";
    private String mTitle="";



    @Override
    public int getLayoutId(){
        return R.layout.activity_news_detail;
    }

    @Override
    public void initViewAndData(){
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        Log.d(TAG,"mTitle->"+mTitle);
        mCollapsingToolBarLayout.setTitle(mTitle);
        Log.d(TAG,"Pic->"+mPic);
        GlideUtil.loadImage(this,mPic,imageView);
        initWebView();
        Log.d(TAG,"url->"+mUrl);
        webView.loadUrl(mUrl);
    }

    @Override
    public void loadData(){
        if(getIntent().getExtras()!=null){
            mUrl=getIntent().getStringExtra(ARG_NEWS_URL);
            mPic=getIntent().getStringExtra(ARG_NEWS_PIC);
            mTitle=getIntent().getStringExtra(ARG_NEWS_TITLE);
        }
        else {
            Toast.makeText(this,"参数有误",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void butterBind(){
        ButterKnife.bind(this);
    }



    @Override
    public void checkNetWork(){

    }

    private void initWebView(){
        WebSettings ws=webView.getSettings();
        ws.setJavaScriptEnabled(true);
        ws.setSupportZoom(false);
        ws.setUseWideViewPort(true);//设置可任意比例缩放
        ws.setJavaScriptCanOpenWindowsAutomatically(true);//允许js自动弹出对话框
        ws.setDomStorageEnabled(true);

        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView webView,String url){
                return false;
            }
            @Override
            public void onPageStarted(WebView webView,String url,Bitmap bitmap){
                super.onPageStarted(webView,url,bitmap);
                processView.setVisibility(View.VISIBLE);
            }
            @Override
            public void onPageFinished(WebView webView,String url){
                super.onPageFinished(webView,url);
                processView.setVisibility(View.GONE);
            }
        });
    }
}
