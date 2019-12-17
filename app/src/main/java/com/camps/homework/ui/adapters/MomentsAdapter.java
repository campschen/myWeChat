package com.camps.homework.ui.adapters;


import android.content.Context;

import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.camps.homework.ui.adapters.viewholder.ProfileViewHolder;
import com.camps.homework.ui.adapters.viewholder.TweetWordImgViewHolder;
import com.camps.homework.ui.adapters.viewholder.TweetWordUrlViewHolder;
import com.camps.homework.ui.adapters.viewholder.TweetWordViewHolder;
import com.camps.frame.widgets.recyclerview.TGRecyclerViewAdapter;

import java.util.List;

import ch.ielse.view.imagewatcher.ImageWatcher;

/**
 * 朋友圈页面列表适配器
 */
public class MomentsAdapter extends TGRecyclerViewAdapter {

    //推文用户图片配置
    private RequestOptions requestOptions;

    private DrawableTransitionOptions mDrawableTransitionOptions;


    public MomentsAdapter(Context context, List items, ImageWatcher mImageWatcher) {
        super(context, items, ProfileViewHolder.class, TweetWordViewHolder.class, TweetWordImgViewHolder.class, TweetWordUrlViewHolder.class);
        requestOptions = new RequestOptions().centerCrop();
        mDrawableTransitionOptions = DrawableTransitionOptions.withCrossFade();
    }

    public RequestOptions getRequestOptions() {
        return requestOptions;
    }

    public DrawableTransitionOptions getDrawableTransitionOptions() {
        return mDrawableTransitionOptions;
    }
}
