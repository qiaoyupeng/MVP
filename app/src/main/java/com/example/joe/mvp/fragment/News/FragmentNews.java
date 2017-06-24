package com.example.joe.mvp.fragment.News;

import android.support.v4.app.Fragment;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;


import com.example.joe.mvp.Adapter.FragmentNewsAdapter;
import com.example.joe.mvp.R;
import com.example.joe.mvp.Utils.Configs;
import com.example.joe.mvp.fragment.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by joe on 2017/5/28.
 */

public class FragmentNews  extends BaseFragment {
    public static String TAG="FragmentNews";
    @BindView(R.id.viewpager)
    ViewPager viewPager;
    @BindView(R.id.tablayout_news)
    TabLayout tabLayout;

    private FragmentNewsAdapter newsItemAdapter;
    private List<String> mTitles;
    private List<Fragment> mFragments;

    @Override
    public void initViewAndData(){
        initData();
        newsItemAdapter=new FragmentNewsAdapter(getFragmentManager(),mFragments,mTitles);
        viewPager.setAdapter(newsItemAdapter);
        viewPager.setOffscreenPageLimit(mFragments.size()-1);
        tabLayout.setupWithViewPager(viewPager);
    }

    public void initData(){
        mTitles=new ArrayList<>();
        mFragments=new ArrayList<>();

        mTitles.add(getString(R.string.news_item1));
        mTitles.add(getString(R.string.news_item2));
        mTitles.add(getString(R.string.news_item3));
        mTitles.add(getString(R.string.news_item4));
        mTitles.add(getString(R.string.news_item5));
        mTitles.add(getString(R.string.news_item6));

        //fragment
        FragmentNewsItem fragment1=FragmentNewsItem.newInstance(Configs.CHINA_ID,Configs.CHINA_NAME);
        FragmentNewsItem fragment2=FragmentNewsItem.newInstance(Configs.INTERNET_ID,Configs.INTERNET_NAME);
        FragmentNewsItem fragment3=FragmentNewsItem.newInstance(Configs.SPORTS_ID,Configs.SPORTS_NAME);
        FragmentNewsItem fragment4=FragmentNewsItem.newInstance(Configs.MOVIE_ID,Configs.MOVIE_NAME);
        FragmentNewsItem fragment5=FragmentNewsItem.newInstance(Configs.FINANCIAL_ID,Configs.FINANCIAL_NAME);
        FragmentNewsItem fragment6=FragmentNewsItem.newInstance(Configs.WORLD_ID,Configs.WORLD_NAME);

        mFragments.add(fragment1);
        mFragments.add(fragment2);
        mFragments.add(fragment3);
        mFragments.add(fragment4);
        mFragments.add(fragment5);
        mFragments.add(fragment6);
    }

    @Override
    public int getLayoutId(){
        return R.layout.fragment_news;
    }

    @Override
    public void butterknifebind(){
        ButterKnife.bind(this,getRootView());
    }

    @Override
    public String getLogTag(){
        return TAG;
    }

    @Override
    public void loadData(){}

    @Override
    public void checkNetWork(){}


}
