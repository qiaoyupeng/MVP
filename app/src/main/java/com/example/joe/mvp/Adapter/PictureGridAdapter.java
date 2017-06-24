package com.example.joe.mvp.Adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.joe.mvp.R;
import com.example.joe.mvp.widget.RatioImageView;
import com.example.joe.mvp.Model.Picture.PictureBody;
import com.example.joe.mvp.Utils.GlideUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by joe on 2017/6/23.
 */

public class PictureGridAdapter extends RecyclerView.Adapter{
    private List<PictureBody> mList;
    private Activity context;

    private OnImageClickListener mOnImageClickListener;

    public PictureGridAdapter(List<PictureBody> mList, Activity context) {
        this.mList = mList;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_picture_grid, parent,false);
        ItemViewHolder viewHolder = new ItemViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof ItemViewHolder){
            ItemViewHolder viewHolder = (ItemViewHolder) holder;
            PictureBody pictureBody = mList.get(position);
            GlideUtil.loadImage(context, pictureBody.list.get(0).middle, viewHolder.iv_picture);
            viewHolder.tv_title.setText(pictureBody.title);
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
       @BindView(R.id.iv_picture)
        RatioImageView iv_picture;
        @BindView(R.id.tv_title)
        TextView tv_title;

        public ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            iv_picture.setOriginalSize(50, 50);
            iv_picture.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mOnImageClickListener.onImageClick(v, this.getPosition());
        }
    }

    public void setOnImageClickListener(OnImageClickListener onItemClickListener) {
        this.mOnImageClickListener = onItemClickListener;
    }

    /**
     * 点击条目图片接口
     */
    public interface OnImageClickListener {
        void onImageClick(View view, int position);
    }
}
