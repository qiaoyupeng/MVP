package com.example.joe.mvp.widget;

/**
 * Created by joe on 2017/6/24.
 */

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * 保留长宽比的imageview
 * @author 刘阳
 * @version 1.0
 *          <p/>
 *          Create by 2016/2/29 17:43
 * @see https://github.com/liuyanggithub/SuperMvp
 */
public class RatioImageView extends ImageView {

    private int originalWidth;
    private int originalHeight;


    public RatioImageView(Context context) {
        super(context);
    }


    public RatioImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    public RatioImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public void setOriginalSize(int originalWidth, int originalHeight) {
        this.originalWidth = originalWidth;
        this.originalHeight = originalHeight;
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (originalWidth > 0 && originalHeight > 0) {
            float ratio = (float) originalWidth / (float) originalHeight;

            int width = MeasureSpec.getSize(widthMeasureSpec);
            int height = MeasureSpec.getSize(heightMeasureSpec);

            // TODO: 现在只支持固定宽度
            if (width > 0) {
                height = (int) ((float) width / ratio);
            }

            setMeasuredDimension(width, height);
        }
        else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }
}
