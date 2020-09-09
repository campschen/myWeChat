package com.camps.homework;


import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.camps.frame.base.BaseActivity;
import com.camps.frame.others.GlideSimpleTarget;
import com.camps.frame.widgets.emoji.EmojiPanelView;
import com.camps.homework.beans.ImageBean;
import com.camps.homework.beans.TweetNormalBean;
import com.camps.homework.beans.UserBean;
import com.camps.homework.contract.MomentsContract;
import com.camps.homework.others.DataCenter;
import com.camps.homework.presenter.MomentsPresenter;
import com.camps.homework.ui.adapters.MomentsAdapter;
import com.camps.homework.ui.listener.OnPraiseOrCommentClickListener;
import com.camps.homework.ui.widgets.TweetAdapterDivideLine;
import com.camps.frame.widgets.recyclerview.IPullToRefreshView;
import com.camps.frame.widgets.recyclerview.PullToRefreshRecyclerView;
import com.camps.homework.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import ch.ielse.view.imagewatcher.ImageWatcher;

/**
 * 朋友圈页面
 */
public class MomentsActivity extends BaseActivity<MomentsPresenter> implements MomentsContract.MomentsView, IPullToRefreshView.OnRefreshListener, OnPraiseOrCommentClickListener, ImageWatcher.OnPictureLongPressListener, ImageWatcher.Loader {

    public static final String TAG = MomentsActivity.class.getSimpleName();

    //当前页
    private int currentPage = 1;

    //页面大小
    private int pageSize = 5;

    //导航栏
    @BindView(R.id.ll_nav)
    LinearLayout mNavLayoutLL;

    //标题
    @BindView(R.id.tv_moments)
    TextView mTitleTV;

    //朋友圈列表（包含封面和个人头像）
    @BindView(R.id.rv_list)
    PullToRefreshRecyclerView mRecyclerView;

    //图片查看器
    @BindView(R.id.iv_img_wathcher)
    ImageWatcher mImageWatcher;

    //emoji表情
    @BindView(R.id.epv_emoji_panel_view)
    EmojiPanelView mEmojiPanel;

    private MomentsAdapter momentsAdapter;

    @Override
    protected MomentsPresenter loadPresenter() {
        return new MomentsPresenter();
    }


    @Override
    protected void initView() {
        ButterKnife.bind(this);
        ArrayList items = new ArrayList();
        MomentsApplication application = (MomentsApplication) getApplication();
        items.add(application.getCurrentUser());
        momentsAdapter = new MomentsAdapter(this, items, mImageWatcher);
        mRecyclerView.addItemDecoration(new TweetAdapterDivideLine());
        mRecyclerView.setAdapter(momentsAdapter);
        momentsAdapter.notifyDataSetChanged();

        mEmojiPanel.initEmojiPanel(DataCenter.emojiDataSources);

        mImageWatcher.setTranslucentStatus(Utils.calcStatusBarHeight(this));
        mImageWatcher.setErrorImageRes(R.mipmap.error_picture);
        mImageWatcher.setOnPictureLongPressListener(this);
        mImageWatcher.setLoader(this);

        mRecyclerView.setItemViewCacheSize(20);
        mTitleTV.setVisibility(View.GONE);
        //测试代码
        mTitleTV.setVisibility(View.GONE);
        //测试代码2
        mTitleTV.setVisibility(View.GONE);
    }

    @Override
    protected void initListener() {

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    Glide.with(MomentsActivity.this).resumeRequests();
                } else {
                    Glide.with(MomentsActivity.this).pauseRequests();
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });

        mRecyclerView.setOnRefreshListener(this);


    }

    @Override
    protected void initData() {
        dataRefresh(currentPage, pageSize, TYPE_UPDATE);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }


    /**
     * 刷新页面数据
     * @param currentPage 当前页
     * @param pageSize 每页大小
     * @param refreshType 列表更新类型
     */
    private void dataRefresh(int currentPage, int pageSize, int refreshType){
        if(refreshType == TYPE_UPDATE) {
            mPresenter.getCurrentUser();
            mRecyclerView.setMode(IPullToRefreshView.Mode.BOTH);
        }

        mPresenter.getTweetList(currentPage, pageSize);
    }



    @Override
    public void updateUserView(UserBean currentUser) {
        MomentsApplication application = (MomentsApplication) getApplication();
        application.getCurrentUser().setUserId(currentUser.getUserId());
        application.getCurrentUser().setUserName(currentUser.getUserName());
        application.getCurrentUser().setProfileImgUr(currentUser.getProfileImgUr());
        application.getCurrentUser().setUserAvatarUrl(currentUser.getUserAvatarUrl());
        momentsAdapter.notifyDataSetChanged();

    }

    @Override
    public void updateTweetList(List<TweetNormalBean> tweetList) {
        mRecyclerView.onRefreshComplete();
        List items = momentsAdapter.getListItems();
        if(items != null && items.size() > 1){
            for (int i = items.size() - 1; i > 0 ; i--) {
                items.remove(i);
            }
        }
        momentsAdapter.notifyDataSetChanged();
        momentsAdapter.appendData(tweetList);
        currentPage ++;
    }

    @Override
    public void appendTweetList(List<TweetNormalBean> tweetList) {
        mRecyclerView.onRefreshComplete();
        momentsAdapter.appendData(tweetList);
        currentPage ++;
    }

    @Override
    public void done() {
        mRecyclerView.onRefreshComplete();
    }

    @Override
    public void onPullDownToRefresh() {
        currentPage = 1;
        dataRefresh(currentPage, pageSize, TYPE_UPDATE);
    }

    @Override
    public void onPullUpToRefresh() {
        dataRefresh(currentPage, pageSize, TYPE_APPEND);
    }

    public void showNoMore(int currentPage) {
        Toast.makeText(this, "已经没有了",Toast.LENGTH_SHORT).show();
        this.currentPage = currentPage - 1;
        mRecyclerView.setMode(IPullToRefreshView.Mode.PULL_FROM_START);
    }

    public int getPageSize() {
        return pageSize;
    }

    @Override
    public void onPraiseClick(int position) {
        Toast.makeText(this, "You Click Praise!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCommentClick(int position) {
        mEmojiPanel.showEmojiPanel();
    }

    /**
     * 显示图片
     * @param view 当前被显示视图
     * @param imageViews 所有图片视图
     * @param imageUrls 图片url数据
     */
    public void show(ImageView view, List<ImageView> imageViews, List<ImageBean> imageUrls) {
        if(imageUrls == null || imageUrls.size() == 0){
            return;
        }

        List<String> urlList = new ArrayList<>(imageUrls.size());
        for (int i = 0; i < imageUrls.size(); i++) {
            urlList.add(imageUrls.get(i).getUrl());
        }

        mImageWatcher.show(view, imageViews, urlList);


    }

    @Override
    public void onPictureLongPress(ImageView v, String url, int pos) {

    }

    @Override
    public void load(Context context, String url, ImageWatcher.LoadCallback lc) {
        Glide.with(context).asBitmap().load(url).into(new GlideSimpleTarget(lc));
    }

    /**
     * 设置朋友圈导航栏是否透明
     * @param trans  true 为透明，fase为白色
     */
    public void setNavbarTrans(boolean trans) {
        if(trans){
            mNavLayoutLL.setBackgroundColor(Color.TRANSPARENT);
            mTitleTV.setVisibility(View.GONE);
            mTitleTV.setTextColor(getResources().getColor(R.color.base_333333));
        }else{
            mNavLayoutLL.setBackgroundColor(getResources().getColor(R.color.base_F2F2F2));
            mTitleTV.setVisibility(View.VISIBLE);
        }
    }
}
