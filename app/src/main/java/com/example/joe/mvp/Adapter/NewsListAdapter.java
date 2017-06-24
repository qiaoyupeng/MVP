package com.example.joe.mvp.Adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.joe.mvp.Model.News.NewsBody;
import com.example.joe.mvp.R;
import com.example.joe.mvp.Utils.GlideUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by joe on 2017/5/29.
 */

public class NewsListAdapter extends RecyclerView.Adapter {

    private static final int TYPE_ITEM=0x00;//viewtype为内容时,根据不同的类型加载不同的ViewHolder
    private static final int TYPE_FOOT=0x01;//viewtype为脚部，加载更多

    private Activity context;
    private List<NewsBody> newsBodies;
    private ItemClickListener itemClickListener;

    public NewsListAdapter(Activity context,List<NewsBody> newsBodies){
        this.context=context;
        this.newsBodies=newsBodies;
    }


    @Override
    public int getItemViewType(int position){

        if(position+1==getItemCount()){
            return TYPE_FOOT;
        }else {
            return TYPE_ITEM;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent,int viewtype){

        if (viewtype==TYPE_ITEM){
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item,parent,false);
            ItemViewHolder vh=new ItemViewHolder(view);
            return vh;
        }else {
            View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item_foot,parent,false);
            return new FootViewHolder(view);

        }

    }

    public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        @BindView(R.id.iv_desc)
        ImageView imageView;
        @BindView(R.id.tv_title)
        TextView textView_title;
        @BindView(R.id.tv_desc)
        TextView textView_desc;

        public ItemViewHolder(View itemView){
            super(itemView);
            ButterKnife.bind(this,itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v){
            if(itemClickListener!=null){
                itemClickListener.onClick(v,this.getPosition());
            }
        }
    }

    public class FootViewHolder extends RecyclerView.ViewHolder{

        public FootViewHolder(View view){
            super(view);
        }
    }

    @Override
    public int getItemCount(){
        return newsBodies.size();
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder,int position){
        //将数据与视图进行绑定

        if(viewHolder instanceof ItemViewHolder){
            NewsBody body=newsBodies.get(position);
            ItemViewHolder holder1=(ItemViewHolder) viewHolder;
            if (body.imageurls!=null && body.imageurls.size()>0 ){
                GlideUtil.loadImage(context,body.imageurls.get(0).url,holder1.imageView);
            }else {
                GlideUtil.loadImage(context,"",holder1.imageView);
            }
            holder1.textView_desc.setText(body.desc);
            holder1.textView_title.setText(body.title);
        }


    }

    public void setOnItemClickListener(ItemClickListener listener){
        itemClickListener=listener;
    }

    //自定义监听事件
    public interface ItemClickListener {
        public void onClick(View v,int position);
    }

}
