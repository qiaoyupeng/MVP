package com.example.joe.mvp.fragment.News;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.baoyz.widget.PullRefreshLayout;
import com.example.joe.mvp.Activity.NewsDetailActivity;
import com.example.joe.mvp.Adapter.NewsListAdapter;
import com.example.joe.mvp.Model.News.NewsBody;
import com.example.joe.mvp.Model.OnNetRequestListener;
import com.example.joe.mvp.Presenter.NewsPresenterImp;
import com.example.joe.mvp.R;
import com.example.joe.mvp.View.INewsView;
import com.example.joe.mvp.fragment.BaseFragment;
import com.example.joe.mvp.widget.MyItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by joe on 2017/6/3.
 */

public class FragmentNewsItem extends BaseFragment implements PullRefreshLayout.OnRefreshListener,INewsView {

    @BindView(R.id.pullrefreshNews)
    PullRefreshLayout pullRefreshLayout;
    @BindView(R.id.recylerviewNews)
    RecyclerView recyclerView;

    private static String TAG="FragmentNewsItem";
    private static String CHANNEL_ID="channelId";
    private static String CHANNEL_NAME="channelName";
    private int pageNum=1;
    private String channel_id;
    private String channel_name;

    private boolean refresh;

    //新闻列表
    private List<NewsBody> newsList=new ArrayList<>();

    private NewsPresenterImp newsPresenter;

    //RecylerView的adapter
    private NewsListAdapter adapter;
    private LinearLayoutManager recylerviewLayoutManager;
    //recylerview的最后一项
    private int lastVisibleItemPosition;

    public static FragmentNewsItem newInstance(String channelId,String channelName){
        Bundle bundle=new Bundle();
        bundle.putString(CHANNEL_ID,channelId);
        bundle.putString(CHANNEL_NAME,channelName);
        FragmentNewsItem fragmentNewsItem=new FragmentNewsItem();
        fragmentNewsItem.setArguments(bundle);
        return fragmentNewsItem;
    }

    @Override
    public int getLayoutId(){
        return R.layout.fragment_news_item;
    }

    @Override
    public void loadData(){
       // newsResult(true);
        newsPresenter.getNewsList(channel_id,channel_name,pageNum);
    }

    @Override
    public void checkNetWork(){

    }

    @Override
    public void initViewAndData(){
        channel_id=getArguments().getString(CHANNEL_ID);
        channel_name=getArguments().getString(CHANNEL_NAME);
        newsPresenter=new NewsPresenterImp(this);
        //adapter=new NewsListAdapter(getActivity(),newsList);
        recylerviewLayoutManager=new LinearLayoutManager(getActivity());

        initRecylerView();
        pullRefreshLayout.setOnRefreshListener(this);
        pullRefreshLayout.setRefreshStyle(PullRefreshLayout.STYLE_MATERIAL);

    }

    @Override
    public void onRefresh(){
        pageNum++;
        loadData();
        //newsResult(true);
        refresh=true;
        pullRefreshLayout.setRefreshing(false);
    }

    private void initRecylerView(){
        recyclerView.addItemDecoration(new MyItemDecoration(getContext(), LinearLayout.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //如果每个item的高度固定，使用这个方法可以提高性能
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(recylerviewLayoutManager);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(RecyclerView recyclerView,int dx,int dy){
                super.onScrolled(recyclerView,dx,dy);
                lastVisibleItemPosition=recylerviewLayoutManager.findLastVisibleItemPosition();
                if(lastVisibleItemPosition+1>=adapter.getItemCount()){
                    pageNum++;
                    refresh=false;
                    loadData();
                }
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }
        });
    }

    public void newsResult(List<NewsBody> list){
        if (refresh) {
            if (!newsList.isEmpty()) {
                newsList.clear();
            }
        }
            newsList.addAll(list);
            Log.d(TAG,"获取到"+newsList.size()+"条数据");
            adapter=new NewsListAdapter(getActivity(),newsList);
            adapter.setOnItemClickListener(new newsItemClickListener());
        //    adapter.notifyDataSetChanged();
            recyclerView.setAdapter(adapter);

    }

    @Override
    public void butterknifebind(){
        ButterKnife.bind(this,getRootView());
    }

    @Override
    public String getLogTag(){
        return TAG;
    }

    public class newsItemClickListener implements NewsListAdapter.ItemClickListener{
        @Override
        public void onClick(View view, int position) {
            NewsBody item = newsList.get(position);
            Intent intent = new Intent(getActivity(), NewsDetailActivity.class);
            if((item.imageurls!= null && item.imageurls.size()>0)) {
                intent.putExtra(NewsDetailActivity.ARG_NEWS_PIC, item.imageurls.get(0).url);
            }
            intent.putExtra(NewsDetailActivity.ARG_NEWS_URL, item.link);
            intent.putExtra(NewsDetailActivity.ARG_NEWS_TITLE, item.title);
            startActivity(intent);
        }
    }
}
