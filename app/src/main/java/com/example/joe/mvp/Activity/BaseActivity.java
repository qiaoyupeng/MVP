package com.example.joe.mvp.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by joe on 2017/5/26.
 */

public abstract class BaseActivity extends AppCompatActivity {

    protected abstract void checkNetWork();
    protected abstract void loadData();
    protected abstract void initViewAndData();
    protected abstract int getLayoutId();
   // protected abstract View getRootView();
    protected abstract void butterBind();

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        butterBind();
        checkNetWork();
        loadData();
        initViewAndData();

    }
}
