package com.camps.homework.ui.adapters.viewholder;

import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.camps.homework.MomentsActivity;
import com.camps.homework.R;
import com.camps.homework.beans.UserBean;
import com.camps.frame.widgets.recyclerview.TGRecyclerViewHolder;

import butterknife.BindView;

/**
 * 个人头像和封面视图
 */
public class ProfileViewHolder extends TGRecyclerViewHolder<UserBean> {

    @BindView(R.id.iv_bg)
    ImageView mBgIV;

    @BindView(R.id.tv_user_name)
    TextView mUserNameTV;

    @BindView(R.id.iv_user_img)
    ImageView mUserImgIV;



    @Override
    protected int getLayoutId() {
        return R.layout.item_profile;
    }

    @Override
    public void fillData(UserBean itemData, int position, int viewType) {
        mUserNameTV.setText(itemData.getUserName());

            Glide.with(getContext())
                    .load(itemData.getUserAvatarUrl())
                    .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                    .into(mUserImgIV);

            Glide.with(getContext())
                    .load(itemData.getProfileImgUr())
                    .placeholder(getDrawable(R.mipmap.profile_bg))
                    .into(mBgIV);
    }

    @Override
    public void onViewAttachedToWindow() {
        super.onViewAttachedToWindow();
        ((MomentsActivity)getContext()).setNavbarTrans(true);
    }

    @Override
    public void onViewDetachedFromWindow() {
        super.onViewDetachedFromWindow();
        ((MomentsActivity)getContext()).setNavbarTrans(false);

    }
}
