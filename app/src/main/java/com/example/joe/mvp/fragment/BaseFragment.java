package com.example.joe.mvp.fragment;

import android.support.v4.app.Fragment;

import android.os.Bundle;

import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by joe on 2017/5/28.
 */

public abstract class BaseFragment  extends Fragment{

    public View rootView;
    public abstract int getLayoutId();
    public abstract void loadData();
    public abstract void checkNetWork();
    public abstract void initViewAndData();
    public abstract void butterknifebind();
    public abstract String getLogTag();



    public View getRootView(){
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState){
        Log.d(getLogTag(),"onActivityCreated");
        super.onActivityCreated(savedInstanceState);
        checkNetWork();
        initViewAndData();
        loadData();
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){

        rootView=inflater.inflate(getLayoutId(), container,false);
        Log.i("H","onCreateView");
        butterknifebind();
        return rootView;

    }

    @Override
    public void onStart(){
        Log.d(getLogTag(),"onStart");
        super.onStart();
    }

    @Override
    public void onResume(){
        Log.d(getLogTag(),"onResume");
        super.onResume();
    }

    @Override
    public void onPause(){
        Log.d(getLogTag(),"onPause");
        super.onPause();
    }

    @Override
    public void onStop(){
        Log.d(getLogTag(),"onStop");
        super.onStop();
    }

    @Override
    public void onDestroyView(){
        Log.d(getLogTag(),"onDestroyView");
        super.onDestroyView();
    }

    @Override
    public void onDestroy(){
        Log.d(getLogTag(),"onDestroy");
        super.onDestroy();
    }

    @Override
    public void onDetach(){
        Log.d(getLogTag(),"onDetach");
        super.onDetach();
    }
}
