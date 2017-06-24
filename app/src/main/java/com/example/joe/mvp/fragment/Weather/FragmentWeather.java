package com.example.joe.mvp.fragment.Weather;

import android.content.Context;
import android.content.DialogInterface;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.joe.mvp.Adapter.WeatherListAdapter;
import com.example.joe.mvp.Model.News.ShowApiResponse;
import com.example.joe.mvp.Model.Weather.ShowApiWeather;
import com.example.joe.mvp.Model.Weather.WeatherByDate;
import com.example.joe.mvp.Presenter.WeatherPresenterImp;
import com.example.joe.mvp.R;
import com.example.joe.mvp.View.IWeatherView;
import com.example.joe.mvp.fragment.BaseFragment;
import com.example.joe.mvp.widget.MyItemDecoration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by joe on 2017/6/3.
 */

public class FragmentWeather extends BaseFragment implements View.OnClickListener,IWeatherView{
    private static String TAG="FragmentWeather";
    @BindView(R.id.nowWeather)
    TextView nowWeather;
    @BindView(R.id.city)
    TextView city;
    @BindView(R.id.pollution)
    TextView pollution;
    @BindView(R.id.location)
    ImageView location;
    @BindView(R.id.getCity)
    EditText getCity;
    @BindView(R.id.getWeather)
    Button getWeather;
    @BindView(R.id.weatherRecylerView)
    RecyclerView recyclerView;

    private LocationManager locationManager;
    private double latitude=0;//经度
    private double longitude=0;//纬度

    private String locationString;
    private List<WeatherByDate> list;
    private WeatherPresenterImp presenterImp;
    private WeatherListAdapter adapter;

    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            double[] data = (double[]) msg.obj;
            // showJW.setText("经度：" + data[0] + "\t纬度:" + data[1]);

            List<Address> addList = null;
            Geocoder ge = new Geocoder(getActivity().getApplicationContext());
            try {
                addList = ge.getFromLocation(data[0], data[1], 1);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            if (addList != null && addList.size() > 0) {
                for (int i = 0; i < addList.size(); i++) {
                    Address ad = addList.get(i);
                    locationString = ad.getLocality();
                }
            }
            getCity.setText(locationString);
            city.setText(locationString);
            Log.d(TAG,locationString);
        }

    };


    @Override
    public void initViewAndData(){
        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        location.setOnClickListener(this);
        getWeather.setOnClickListener(this);
        list=new ArrayList<>();
        presenterImp=new WeatherPresenterImp(this);
        initRecylerView();

    }

    public void initLocationData(){

        new Thread(){
           @Override
            public void run(){
               Location location=locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                /*latitude=location.getLatitude();
                longitude=location.getLongitude();
                double data[]={latitude,longitude};
                Message message=handler.obtainMessage();
                message.obj=data;
                handler.sendMessage(message);*/
               if (location != null) {
                   latitude = location.getLatitude(); // 经度
                   longitude = location.getLongitude(); // 纬度
                   double[] data = { latitude, longitude };
                   Message msg = handler.obtainMessage();
                   msg.obj = data;
                   handler.sendMessage(msg);
               }

           }
        }.start();
    }

    @Override
    public int getLayoutId(){
        return R.layout.fragment_weather;
    }

    @Override
    public String getLogTag(){
        return TAG;
    }

    public void butterknifebind(){
        ButterKnife.bind(this,getRootView());
    }

    @Override
    public void weatherResult(ShowApiResponse<ShowApiWeather> response){
        nowWeather.setText(response.showapi_res_body.now.temperature+"℃");//设置实时气温
        pollution.setText(response.showapi_res_body.now.aqiDetail.quality);//设置空气质量
        list.add(response.showapi_res_body.f1);
        list.add(response.showapi_res_body.f2);
        list.add(response.showapi_res_body.f3);
        list.add(response.showapi_res_body.f4);
        list.add(response.showapi_res_body.f5);
        adapter=new WeatherListAdapter(getActivity(),list);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void loadData(){
        if (getCity.getText()!=null){
            city.setText(getCity.getText());
            presenterImp.getWeather(getCity.getText().toString(),"1");
        }
    }

    @Override
    public void checkNetWork(){

    }
    @Override
    public void onClick(View v){
        if (v.getId()==R.id.location){
            initLocationData();
        }
        if (v.getId()==R.id.getWeather){
            loadData();
        }
    }

    private void initRecylerView(){
        recyclerView.addItemDecoration(new MyItemDecoration(getContext(), LinearLayout.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //如果每个item的高度固定，使用这个方法可以提高性能
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setDrawingCacheBackgroundColor(0);

    }
}
