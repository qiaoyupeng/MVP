package com.example.joe.mvp.Utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by joe on 2017/6/22.
 */

public class NetWork {

    /**
     * 判断设备是否接入网络
     *
     */

    public static boolean isConnected(Context context){
        ConnectivityManager connectivityManager=(ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if(null!=connectivityManager){
            NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();
            if(null!=networkInfo && networkInfo.isConnected()){
                if(networkInfo.getState()==NetworkInfo.State.CONNECTED){
                    return true;
                }
            }
        }
        return false;
    }
}
