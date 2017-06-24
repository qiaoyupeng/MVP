package com.example.joe.mvp.Utils;

import android.app.Application;
import android.content.Context;

/**
 * Created by joe on 2017/6/22.
 * 要在AndroidManifest.xml中将Application name属性改为.Utils.MyApplication
 */

public class MyApplication extends Application {
    private static MyApplication application;

    @Override
    public void onCreate(){
        super.onCreate();
        application=this;
    }

    public static Context getAppContext(){
        return application;
    }
}
