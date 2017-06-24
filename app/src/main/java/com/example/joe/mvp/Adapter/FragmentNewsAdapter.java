package com.example.joe.mvp.Adapter;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.Fragment;

import java.util.List;

/**
 * Created by joe on 2017/5/28.
 */

public class FragmentNewsAdapter extends FragmentStatePagerAdapter{

    List<String> mTitles;
    List<Fragment> mFragments;
    public FragmentNewsAdapter(FragmentManager fm, List<Fragment> mFragments,List<String> mTitles){
        super(fm);
        this.mTitles=mTitles;
        this.mFragments=mFragments;
    }

    @Override
    public Fragment getItem(int position){
        return mFragments.get(position);
    }

    @Override
    public int getCount(){
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position){
        return mTitles.get(position);
    }
}
