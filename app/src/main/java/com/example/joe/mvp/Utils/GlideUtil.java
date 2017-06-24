package com.example.joe.mvp.Utils;

import android.content.Context;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.example.joe.mvp.R;

/**
 * Created by joe on 2017/5/31.
 */

//图片加载工具类

public class GlideUtil {

    public static void loadImage(Context context, String url, ImageView imageView){
        Glide.with(context)
                .load(url)
                .placeholder(R.mipmap.ic_holding)
                .error(R.mipmap.ic_error)
                .into(imageView);
    }
}
