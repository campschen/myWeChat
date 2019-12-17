package com.camps.homework.ui.adapters.viewholder;

import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.camps.frame.utils.DisplayUtils;
import com.camps.frame.widgets.NineGridView;
import com.camps.homework.Constants;
import com.camps.homework.MomentsActivity;
import com.camps.homework.R;
import com.camps.homework.beans.TweetNormalBean;
import com.camps.homework.others.enums.TranslationState;
import com.camps.homework.ui.adapters.MomentsAdapter;
import com.camps.homework.ui.adapters.NineImageAdapter;
import com.camps.homework.ui.listener.OnItemClickPopupMenuListener;
import com.camps.homework.ui.widgets.CommentOrPraisePopupWindow;
import com.camps.homework.ui.widgets.VerticalCommentWidget;
import com.camps.frame.widgets.recyclerview.TGRecyclerViewHolder;
import com.camps.homework.utils.TimerUtils;
import com.camps.homework.utils.Utils;

import butterknife.BindView;

/**
 * 包含文字和图片的推文视图
 */
public class TweetWordImgViewHolder extends TGRecyclerViewHolder<TweetNormalBean> implements OnItemClickPopupMenuListener {

    //推文用户头像
    @BindView(R.id.img_avatar)
    ImageView mUserImgIV;

    //推文用户名
    @BindView(R.id.txt_user_name)
    TextView mUserNameTV;

    //推文内容
    @BindView(R.id.txt_content)
    TextView mContentTV;

    //内容全部按钮
    @BindView(R.id.txt_state)
    TextView mAllTV;

    //翻译布局容器
    @BindView(R.id.layout_translation)
    LinearLayout mTransLayoutLL;

    //翻译中图标
    @BindView(R.id.img_translating)
    ImageView mTransImgIV;

    //分割线
    @BindView(R.id.view_divide_line)
    View mTransLineV;

    //翻译中
    @BindView(R.id.txt_translation_desc)
    TextView mTransDesTV;

    //翻译内容
    @BindView(R.id.txt_translation_content)
    TextView mTransContentTV;

    //位置
    @BindView(R.id.txt_location)
    TextView mLocationTV;

    //发布时间
    @BindView(R.id.txt_publish_time)
    TextView mPushlishTimeTV;

    //推文来源
    @BindView(R.id.txt_source)
    TextView mSourceTV;

    //评论按钮
    @BindView(R.id.img_click_praise_or_comment)
    ImageView mCommentIV;

    //点赞评论布局容器
    @BindView(R.id.layout_praise_and_comment)
    LinearLayout mCommentLayoutLL;

    //点赞
    @BindView(R.id.praise_content)
    TextView mPraiseContent;

    //评论分割线
    @BindView(R.id.view_line)
    View mComentLineTV;

    //评论列表容器
    @BindView(R.id.vertical_comment_widget)
    VerticalCommentWidget mComentListVCW;

    //图片宫格容器
    @BindView(R.id.nine_grid_view)
    NineGridView mGridViewGV;

    private CommentOrPraisePopupWindow mCommentOrPraisePopupWindow;

    @Override
    protected int getLayoutId() {
        return R.layout.item_recycler_firend_circle_word_and_images;
    }

    @Override
    public void fillData(TweetNormalBean itemData, int position, int viewType) {
        //推文内容
        setContent(itemData, position);

        //推文用户头像
        MomentsAdapter adapter = (MomentsAdapter) getAdapter();
        if(itemData.getUserBean() != null) {
            itemData.getUserBean().setUserAvatarUrl(Constants.IMAGE_USER_URL[(int)(Math.random()*5)]);
            Glide.with(getContext())
                    .load(itemData.getUserBean().getUserAvatarUrl())
                    .apply(adapter.getRequestOptions().override(DisplayUtils.dp2px(getContext(), 44f), DisplayUtils.dp2px(getContext(), 44f)))
                    .into(mUserImgIV);
        }

        //翻译
        updateTransView(position, itemData.getTranslationState(), itemData.getContentSpan(), false);

        //来源和发布时间
        if (itemData.getOtherInfoBean() != null) {
            mSourceTV.setText(itemData.getOtherInfoBean().getSource());
            mPushlishTimeTV.setText(itemData.getOtherInfoBean().getTime());
        }else {
            mSourceTV.setText("");
            mPushlishTimeTV.setText("刚刚");
        }

        //显示点赞和评论
        if (itemData.isShowPraise() || itemData.isShowComment()) {
            mCommentLayoutLL.setVisibility(View.VISIBLE);
            if (itemData.isShowComment() && itemData.isShowPraise()) {
                mComentLineTV.setVisibility(View.VISIBLE);
            } else {
                mComentLineTV.setVisibility(View.GONE);
            }
            if (itemData.isShowPraise()) {
                mPraiseContent.setVisibility(View.VISIBLE);
                mPraiseContent.setText(itemData.getPraiseSpan());
            } else {
                mPraiseContent.setVisibility(View.GONE);
            }
            if (itemData.isShowComment()) {
                mComentListVCW.setVisibility(View.VISIBLE);
                mComentListVCW.addComments(itemData.getCommentBeans(), false);
            } else {
                mComentListVCW.setVisibility(View.GONE);
            }
        } else {
            mCommentLayoutLL.setVisibility(View.GONE);
        }

        mCommentIV.setOnClickListener(v -> {
                if (mCommentOrPraisePopupWindow == null) {
                    mCommentOrPraisePopupWindow = new CommentOrPraisePopupWindow(getContext());
                }
                mCommentOrPraisePopupWindow
                        .setOnPraiseOrCommentClickListener((MomentsActivity) getContext())
                        .setCurrentPosition(position);

                if (mCommentOrPraisePopupWindow.isShowing()) {
                    mCommentOrPraisePopupWindow.dismiss();
                } else {
                    mCommentOrPraisePopupWindow.showPopupWindow(v);
                }
        });

        mLocationTV.setOnClickListener(v -> Toast.makeText(getContext(), "You Click Location", Toast.LENGTH_SHORT).show());


        //图片

        mGridViewGV.setOnImageClickListener((position1, view) ->
                ((MomentsActivity)getContext()).show((ImageView) view, mGridViewGV.getImageViews(), itemData.getImageUrls()));
        mGridViewGV.setAdapter(new NineImageAdapter(getContext(), adapter.getRequestOptions(),
                adapter.getDrawableTransitionOptions(), itemData.getImageUrls()));
    }



    //设置推文
    private void setContent(TweetNormalBean itemData, int position){
        mContentTV.setText(itemData.getContentSpan());

        //全部显示
        if (itemData.isShowCheckAll()) {
            mAllTV.setVisibility(View.VISIBLE);
            changeContentState(itemData.isExpanded());
            mAllTV.setOnClickListener(v -> {
                if (itemData.isExpanded()) {
                    itemData.setExpanded(false);
                } else {
                    itemData.setExpanded(true);
                }
                changeContentState(itemData.isExpanded());
            });
        } else {
            mAllTV.setVisibility(View.GONE);
            mContentTV.setMaxLines(Integer.MAX_VALUE);
        }

        if (itemData.isExpanded()) {
            mContentTV.setMaxLines(Integer.MAX_VALUE);
            mAllTV.setText("收起");
        } else {
            mContentTV.setMaxLines(4);
            mAllTV.setText("全文");
        }

        //设置推文长按菜单
        mContentTV.setOnLongClickListener(v -> {
            TranslationState translationState = itemData.getTranslationState();
            if (translationState == TranslationState.END) {
                Utils.showPopupMenu(getContext(), this, position, v, TranslationState.END);
            } else {
                Utils.showPopupMenu(getContext(), this, position, v, TranslationState.START);
            }
            return true;
        });


    }

    //改变推文收起折叠
    private void changeContentState(boolean isExpanded){
        if (isExpanded) {
            mContentTV.setMaxLines(Integer.MAX_VALUE);
            mAllTV.setText("收起");
        } else {
            mContentTV.setMaxLines(4);
            mAllTV.setText("全文");
        }
    }

    @Override
    public void onItemClickCopy(int position) {
        Toast.makeText(getContext(), "已复制", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClickTranslation(int position) {
        TweetNormalBean TweetNormalBean = (TweetNormalBean) getAdapter().getItem(position);
        TweetNormalBean.setTranslationState(TranslationState.START);
        updateTransView(position, TranslationState.START, null, true);

        TimerUtils.timerTranslation(() -> {
            ((TweetNormalBean) getAdapter().getItem(position)).setTranslationState(TranslationState.END);
            updateTransView(position, TranslationState.END, ((TweetNormalBean) getAdapter().getItem(position)).getContentSpan(), true);
        });


    }

    @Override
    public void onItemClickHideTranslation(int position) {
        TweetNormalBean TweetNormalBean = (TweetNormalBean) getAdapter().getItem(position);
        TweetNormalBean.setTranslationState(TranslationState.START);
        updateTransView(position, TranslationState.START, null, true);

    }

    @Override
    public void onItemClickCollection(int position) {
        Toast.makeText(getContext(), "已收藏", Toast.LENGTH_SHORT).show();
    }

    //更新翻译相关视图
    private void updateTransView(int position, TranslationState translationState, SpannableStringBuilder translationResult, boolean isStartAnimation) {
        getAdapter().getItemViewType(position);

        if (translationState == TranslationState.START) {
            mTransLayoutLL.setVisibility(View.GONE);
        } else if (translationState == TranslationState.CENTER) {
            mTransLayoutLL.setVisibility(View.VISIBLE);
            mTransLineV.setVisibility(View.GONE);
            mTransImgIV.setVisibility(View.VISIBLE);
            mTransDesTV.setText(R.string.translating);
            mTransContentTV.setVisibility(View.GONE);
            Utils.startAlphaAnimation(mTransImgIV, isStartAnimation);
        } else {
            mTransLayoutLL.setVisibility(View.VISIBLE);
            mTransLineV.setVisibility(View.VISIBLE);
            mTransImgIV.setVisibility(View.GONE);
            mTransDesTV.setText(R.string.translated);
            mTransContentTV.setVisibility(View.VISIBLE);
            mTransContentTV.setText(translationResult);
            Utils.startAlphaAnimation(mTransContentTV, isStartAnimation);
            mTransContentTV.setOnLongClickListener(v -> {
                Utils.showPopupMenu(getContext(), this, position, v, TranslationState.END);
                return true;
            });
        }
    }

}
