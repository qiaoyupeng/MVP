package com.example.joe.mvp.fragment.Person;

import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.joe.mvp.R;
import com.example.joe.mvp.Utils.MyApplication;
import com.example.joe.mvp.fragment.BaseFragment;

import java.io.File;
import java.math.BigDecimal;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by joe on 2017/6/24.
 */

public class FragmentPerson  extends BaseFragment{
    @BindView(R.id.linearSetting)
    android.widget.LinearLayout linearSetting;
    @BindView(R.id.linearFeedback)
    LinearLayout linearFeedback;
    @BindView(R.id.tvSettingClear)
    TextView textView;

    private File cacheDir;
    private String cacheDirPath;
    private float mCacheSize=0;
    @Override
    public void loadData(){

    }

    @Override
    public void initViewAndData(){
        cacheDirPath= MyApplication.getAppContext().getCacheDir().getPath();
        cacheDir=new File(cacheDirPath + File.separator +"who 知");
        try {
            long size = getFolderSize(cacheDir);
            mCacheSize = size / 1024.0f;
            if (mCacheSize < 1024.0f) {
                BigDecimal b = new BigDecimal(mCacheSize);
                float f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
                textView.setText(f1 + "KB");
            } else {
                BigDecimal b = new BigDecimal(mCacheSize / 1024.0f);
                float f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
                textView.setText(f1 + "MB");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getLayoutId(){
        return R.layout.fragment_person;
    }

    @Override
     public void butterknifebind(){
        ButterKnife.bind(this,getRootView());
    }

    @Override
    public void checkNetWork(){

    }

    @Override
    public String getLogTag(){
        return null;
    }

    @OnClick(R.id.linearSetting)
    void SettingEvent(){
        deleteFilesByDirectory(cacheDir);
        textView.setText("0 MB");
        Toast.makeText(MyApplication.getAppContext(),"清楚成功",Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.linearFeedback)
    void toast(){
        Toast.makeText(MyApplication.getAppContext(),"已经是最新",Toast.LENGTH_SHORT).show();
    }

    public static long getFolderSize(File file) throws Exception {
        long size = 0;
        try {
            File[] fileList = file.listFiles();
            for (int i = 0; i < fileList.length; i++) {
                // 如果下面还有文件
                if (fileList[i].isDirectory()) {
                    size = size + getFolderSize(fileList[i]);
                } else {
                    size = size + fileList[i].length();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return size;
    }

    public static void deleteFilesByDirectory(File directory) {
        if (directory != null && directory.exists() && directory.isDirectory()) {
            for (File item : directory.listFiles()) {
                item.delete();
            }
        }
    }

}
