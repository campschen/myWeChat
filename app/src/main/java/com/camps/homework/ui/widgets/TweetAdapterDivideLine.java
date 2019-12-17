package com.camps.homework.ui.widgets;

import android.graphics.Rect;
import android.view.View;


import com.camps.frame.utils.DisplayUtils;
import com.camps.homework.MomentsApplication;
import com.camps.homework.utils.Utils;

import androidx.recyclerview.widget.RecyclerView;

/**
 * 推文间隔线
 */
public class TweetAdapterDivideLine extends RecyclerView.ItemDecoration {
    private int mDivideHeight;

    public TweetAdapterDivideLine() {
        mDivideHeight = DisplayUtils.dp2px(MomentsApplication.getContext(), 0.5f);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.set(0, 0, 0, mDivideHeight);
    }
}
