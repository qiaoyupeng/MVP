package com.example.joe.mvp.fragment.Picture;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;

import com.baoyz.widget.PullRefreshLayout;
import com.example.joe.mvp.Adapter.PictureGridAdapter;
import com.example.joe.mvp.Model.Picture.PictureBody;
import com.example.joe.mvp.Presenter.PicturePresenterImp;
import com.example.joe.mvp.R;
import com.example.joe.mvp.Utils.GlideUtil;
import com.example.joe.mvp.View.IPctureView;
import com.example.joe.mvp.fragment.BaseFragment;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.Holder;
import com.orhanobut.dialogplus.ViewHolder;
import com.bm.library.PhotoView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by joe on 2017/6/3.
 */

public class FragmentPicture extends BaseFragment implements PullRefreshLayout.OnRefreshListener,IPctureView,FloatingActionButtonListener{

    private String TAG="FragmentPicture";
    @BindView(R.id.pullrefreshlayout)
    PullRefreshLayout pullRefreshLayout;
    @BindView(R.id.recyclerview_Picture)
    RecyclerView recyclerView;
    //悬浮菜单
    @BindView(R.id.floating_action_menu)
    FloatingActionMenu floating_action_menu;
    @BindView(R.id.floating_action_button1)
    FloatingActionButton floating_action_button1;
    @BindView(R.id.floating_action_button2)
    FloatingActionButton floating_action_button2;
    @BindView(R.id.floating_action_button3)
    FloatingActionButton floating_action_button3;
    @BindView(R.id.floating_action_button4)
    FloatingActionButton floating_action_button4;

    protected List<FloatingActionButton> mFloatingActionButtons;//悬浮菜单选项数组

    private int mPageNum = 1;
    String mPictureId = "4001";//类别 "清纯"
    private List<PictureBody> mList = new ArrayList<>();
    private boolean refresh;

    private PictureGridAdapter pictureGridAdapter;
    private static final int PRELOAD_SIZE = 6;
    private LinearLayout ll_dialog_holder;//弹窗的布局
    private DialogPlus mDialog;
    private StaggeredGridLayoutManager mGridViewLayoutManager;//recycleview视图样式管理器

    private PicturePresenterImp presenterImp;

    @Override
    public void loadData(){
        presenterImp.getPictureList(mPictureId,mPageNum);
    }

    @Override
    public void initViewAndData(){
        presenterImp=new PicturePresenterImp(this);
        pictureGridAdapter=new PictureGridAdapter(mList,getActivity());
        pictureGridAdapter.setOnImageClickListener(new PictureGridAdapter.OnImageClickListener() {
            @Override
            public void onImageClick(View view, int position) {
                showDialog(mList.get(position).list.get(0).big);
            }
        });
        initFloatingActionMenu();
        initRecyclerView();
        pullRefreshLayout.setOnRefreshListener(this);
        pullRefreshLayout.setRefreshStyle(PullRefreshLayout.STYLE_MATERIAL);
    }

    @Override
    public int getLayoutId(){
        return R.layout.fragment_picture;
    }

    @Override
    public void butterknifebind(){
        ButterKnife.bind(this,getRootView());
    }

    void initRecyclerView() {
        mGridViewLayoutManager = new StaggeredGridLayoutManager(2,
                StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setAdapter(pictureGridAdapter);
        recyclerView.setLayoutManager(mGridViewLayoutManager);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView,int dx,int dy){
                super.onScrolled(recyclerView,dx,dy);
                boolean isBottom = mGridViewLayoutManager.findLastCompletelyVisibleItemPositions(new int[2])[1]
                        >= pictureGridAdapter.getItemCount() - PRELOAD_SIZE;
                if(isBottom){
                    mPageNum++;
                    refresh=false;
                    loadData();
                }

            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }
        });
        setFloatingActionButtonListener(this);
    }


    @Override
    public void checkNetWork(){

    }

    @Override
    public String getLogTag(){
        return TAG;
    }

    @Override
    public void onRefresh(){
        mPageNum++;
        loadData();
        refresh=true;
        pullRefreshLayout.setRefreshing(false);
    }

    @Override
    public void pictureResult(List<PictureBody> list){
        if(refresh){
            if(!mList.isEmpty()){
                mList.clear();
            }
        }
        mList.addAll(list);
        pictureGridAdapter.notifyDataSetChanged();
    }

    public void showDialog(String imgUrl) {
        ll_dialog_holder = (LinearLayout) getActivity().getLayoutInflater().inflate(R.layout.dialog_image_preview, null);
        Holder holder = new ViewHolder(ll_dialog_holder);
        PhotoView photo_view = (PhotoView) holder.getInflatedView().findViewById(R.id.photo_view);
        photo_view.enable();//启动缩放功能
        GlideUtil.loadImage(getActivity(), imgUrl, photo_view);
        photo_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
            }
        });
        showOnlyContentDialog(holder, Gravity.TOP, false);
    }
    /**
     * 仅显示内容的dialog
     *
     * @param holder
     * @param gravity  显示位置（居中，底部，顶部）
     * @param expanded 是否支持展开（有列表时适用）
     */
    private void showOnlyContentDialog(Holder holder, int gravity,
                                       boolean expanded) {
        mDialog = DialogPlus.newDialog(getActivity())
                .setContentHolder(holder)
                .setGravity(gravity)
                .setExpanded(expanded)
                .setCancelable(true)
                .create();
        mDialog.show();
    }

    /**
     * 设置是否隐藏悬浮菜单选项卡
     * @param animate 是否动画
     */
    public void hideMenu(boolean animate){
        floating_action_menu.close(animate);
    }

    /**
     * 初始化悬浮菜单
     */
    private void initFloatingActionMenu() {
        floating_action_menu.setVisibility(View.VISIBLE);
        floating_action_menu.setClosedOnTouchOutside(true);
        mFloatingActionButtons = new ArrayList<>();
        mFloatingActionButtons.add(floating_action_button1);
        mFloatingActionButtons.add(floating_action_button2);
        mFloatingActionButtons.add(floating_action_button3);
        mFloatingActionButtons.add(floating_action_button4);
    }

    /**
     * 设置悬浮菜单项目点击事件
     */
    public void setFloatingActionButtonListener(final FloatingActionButtonListener listener){
        for(int i = 0; i < mFloatingActionButtons.size(); i++){
            String id = "";
            switch (i) {
                case 0 : id = "4001"; break;
                case 1 : id = "4002"; break;
                case 2 : id = "4003"; break;
                case 3 : id = "4004"; break;
                default: id = "4001"; break;
            }
            final String finalId = id;
            mFloatingActionButtons.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(finalId);
                    hideMenu(true);
                }
            });
        }
    }
    @Override
    public void onClick(String id) {
        mPictureId = id;
        refresh=true;
        loadData();
    }

}
