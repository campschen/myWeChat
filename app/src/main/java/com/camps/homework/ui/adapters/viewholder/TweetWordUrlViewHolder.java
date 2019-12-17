package com.camps.homework.ui.adapters.viewholder;

import com.camps.homework.R;
import com.camps.homework.beans.TweetWordUrlBean;
import com.camps.frame.widgets.recyclerview.TGRecyclerViewHolder;

/**
 * 包含分享url的视图
 */
public class TweetWordUrlViewHolder extends TGRecyclerViewHolder<TweetWordUrlBean> {
    @Override
    protected int getLayoutId() {
        return R.layout.item_recycler_firend_circle_word_and_url;
    }

    @Override
    public void fillData(TweetWordUrlBean itemData, int position, int viewType) {

    }
}
