package com.example.joe.mvp.Adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.joe.mvp.Model.Weather.WeatherByDate;
import com.example.joe.mvp.R;
import com.example.joe.mvp.Utils.GlideUtil;

import org.w3c.dom.Text;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by joe on 2017/6/20.
 */

public class WeatherListAdapter extends RecyclerView.Adapter {

    public List<WeatherByDate> weather;
    private Activity context;
    private static String week[]={"","周一","周二","周三","周四","周五","周六","周日"};
    public WeatherListAdapter(Activity context,List<WeatherByDate> weather){
        this.context=context;
        this.weather=weather;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent,int viewtype){
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.weather_item,parent,false);
        WeatherItemViewHolder holder=new WeatherItemViewHolder(view);
        return holder;
    }

    @Override
    public int getItemCount(){
        return weather.size();
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder,int position){
        //将数据与视图绑定起来
        if(viewHolder instanceof WeatherItemViewHolder){
            WeatherByDate weatherByDate=weather.get(position);
            WeatherItemViewHolder holder=(WeatherItemViewHolder) viewHolder;
            if (position==0){
                holder.weekday.setText("今天");
            }else holder.weekday.setText(week[Integer.valueOf(weatherByDate.weekday)]);

            holder.weather.setText(weatherByDate.day_weather);
            holder.weather_temp.setText(weatherByDate.day_air_temperature+"℃");
            if(weatherByDate.day_weather_pic!=null){
                GlideUtil.loadImage(context,weatherByDate.day_weather_pic,holder.weather_pic);
            }
        }
    }

    public class WeatherItemViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.weekday)
        TextView weekday;
        @BindView(R.id.weather_pic)
        ImageView weather_pic;
        @BindView(R.id.weather)
        TextView weather;
        @BindView(R.id.weather_temp)
        TextView weather_temp;

        public WeatherItemViewHolder(View view){
            super(view);
            ButterKnife.bind(this,view);
        }
    }
}
