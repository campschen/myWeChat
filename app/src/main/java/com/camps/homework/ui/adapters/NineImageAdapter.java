package com.camps.homework.ui.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.camps.frame.utils.DisplayUtils;
import com.camps.frame.widgets.NineGridView;
import com.camps.homework.R;
import com.camps.homework.beans.ImageBean;

import java.util.List;

import androidx.core.content.ContextCompat;

/**
 * 九宫格推文视图
 */
public class NineImageAdapter implements NineGridView.NineGridAdapter<String> {

    private List<ImageBean> mImageBeans;

    private Context mContext;

    private RequestOptions mRequestOptions;

    private DrawableTransitionOptions mDrawableTransitionOptions;


    public NineImageAdapter(Context context, RequestOptions requestOptions, DrawableTransitionOptions drawableTransitionOptions, List<ImageBean> imageBeans) {
        this.mContext = context;
        this.mDrawableTransitionOptions = drawableTransitionOptions;
        this.mImageBeans = imageBeans;
        int itemSize = (DisplayUtils.getScreenWidth(context) - 2 * DisplayUtils.dp2px(context,4) - DisplayUtils.dp2px(context, 54)) / 3;
        this.mRequestOptions = requestOptions.override(itemSize, itemSize);
    }

    @Override
    public int getCount() {
        return mImageBeans == null ? 0 : mImageBeans.size();
    }

    @Override
    public String getItem(int position) {
        return mImageBeans == null ? null :
                position < mImageBeans.size() ? mImageBeans.get(position).getUrl() : null;
    }

    @Override
    public View getView(int position, View itemView) {
        ImageView imageView;
        if (itemView == null) {
            imageView = new ImageView(mContext);
            imageView.setBackgroundColor(ContextCompat.getColor(mContext, R.color.base_F2F2F2));
            imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        } else {
            imageView = (ImageView) itemView;
        }
        String url = mImageBeans.get(position).getUrl();
        Glide.with(mContext).load(url).apply(mRequestOptions).transition(mDrawableTransitionOptions).into(imageView);
        return imageView;
    }
}
