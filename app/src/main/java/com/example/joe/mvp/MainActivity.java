package com.example.joe.mvp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.example.joe.mvp.Activity.BaseActivity;
import com.example.joe.mvp.Utils.BottomNavigationViewHelper;
import com.example.joe.mvp.fragment.News.FragmentNews;
import com.example.joe.mvp.fragment.Person.FragmentPerson;
import com.example.joe.mvp.fragment.Picture.FragmentPicture;
import com.example.joe.mvp.fragment.Weather.FragmentWeather;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity  implements BottomNavigationView.OnNavigationItemSelectedListener{

    @BindView(R.id.navigationview)
    BottomNavigationView navigationView;


    int currentIndex=-1;

    FragmentManager fragmentManager;
    FragmentNews newsFragment;
    FragmentPicture picturesFragment;
    FragmentWeather weatherFragment;
    FragmentPerson personFragment;



    @Override
    protected void checkNetWork(){

    }

    @Override
    protected void loadData(){

    }

    @Override
    protected void initViewAndData(){
        //默认 >3 的选中效果会影响ViewPager的滑动切换时的效果，故利用反射去掉
        BottomNavigationViewHelper.disableShiftMode(navigationView);
        navigationView.setOnNavigationItemSelectedListener(this);
        fragmentManager=getSupportFragmentManager();
        switch2Fragment(0);
    }

    @Override
    protected int getLayoutId(){
        return R.layout.activity_main;
    }



    @Override
    protected void butterBind(){
        ButterKnife.bind(this);
    }


    private void switch2Fragment(int index){
        if(index==currentIndex){
            return;
        }
        FragmentTransaction transaction=fragmentManager.beginTransaction();
        //先隐藏所有的fragment
        hidefragment(transaction);

        switch (index){
            case 0:
                if(newsFragment==null){
                    newsFragment=new FragmentNews();
                    transaction.add(R.id.framelayout_main,newsFragment);
                }else{
                    transaction.show(newsFragment);
                }
                currentIndex=0;
                break;

            case 1:
               if(picturesFragment==null){
                    picturesFragment=new FragmentPicture();
                    transaction.add(R.id.framelayout_main,picturesFragment);
                }else{
                    transaction.show(picturesFragment);
                }
                currentIndex=1;
                break;

            case 2:
               if(weatherFragment==null){
                    weatherFragment=new FragmentWeather();
                    transaction.add(R.id.framelayout_main,weatherFragment);
                }else{
                    transaction.show(weatherFragment);
                }
                currentIndex=2;
                break;

            case 3:
                if(personFragment==null){
                    personFragment=new FragmentPerson();
                    transaction.add(R.id.framelayout_main,personFragment);
                }else {
                    transaction.show(personFragment);
                }
                currentIndex=3;
                break;
        }
        transaction.commitAllowingStateLoss();
    }

    private void hidefragment(FragmentTransaction transaction){
        if(newsFragment!=null)
            transaction.hide(newsFragment);
        if(picturesFragment!=null)
           transaction.hide(picturesFragment);
        if(weatherFragment!=null)
            transaction.hide(weatherFragment);
        if(personFragment!=null)
            transaction.hide(personFragment);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem){
        switch(menuItem.getItemId()){
            case R.id.navigation_news:
                switch2Fragment(0);
                break;
            case R.id.navigation_fun:
                switch2Fragment(1);
                break;
            case R.id.navigation_weather:
                switch2Fragment(2);
                break;
            case  R.id.navigation_person:
                switch2Fragment(3);
                break;

        }

        return true;
    }







}
