package com.camps.homework.beans;

import android.text.SpannableStringBuilder;

import com.camps.homework.others.enums.TranslationState;

import java.util.List;

public class TweetWordBean extends TweetNormalBean {
    private TweetNormalBean tweetBean;

    public TweetWordBean(TweetNormalBean tweetBean)
    {
        this.tweetBean = tweetBean;
    }

    @Override
    public boolean isExpanded() {
        return tweetBean.isExpanded();
    }

    @Override
    public void setExpanded(boolean expanded) {
        tweetBean.setExpanded(expanded);
    }

    @Override
    public boolean isShowCheckAll() {
        return tweetBean.isShowCheckAll();
    }

    @Override
    public void setShowCheckAll(boolean showCheckAll) {
        tweetBean.setShowCheckAll(showCheckAll);
    }

    @Override
    public void setTranslationState(TranslationState translationState) {
        tweetBean.setTranslationState(translationState);
    }

    @Override
    public TranslationState getTranslationState() {
        return tweetBean.getTranslationState();
    }

    @Override
    public boolean isShowComment() {
        return tweetBean.isShowComment();
    }

    @Override
    public boolean isShowPraise() {
        return tweetBean.isShowPraise();
    }

    @Override
    public OtherInfoBean getOtherInfoBean() {
        return tweetBean.getOtherInfoBean();
    }

    @Override
    public void setOtherInfoBean(OtherInfoBean otherInfoBean) {
        tweetBean.setOtherInfoBean(otherInfoBean);
    }

    @Override
    public void setUserBean(UserBean userBean) {
        tweetBean.setUserBean(userBean);
    }

    @Override
    public UserBean getUserBean() {
        return tweetBean.getUserBean();
    }

    @Override
    public int getViewType() {
        return tweetBean.getViewType();
    }

    @Override
    public void setViewType(int viewType) {
        tweetBean.setViewType(viewType);
    }

    @Override
    public String getContent() {
        return tweetBean.getContent();
    }

    @Override
    public SpannableStringBuilder getContentSpan() {
        return tweetBean.getContentSpan();
    }

    @Override
    public void setContentSpan(SpannableStringBuilder contentSpan) {
        tweetBean.setContentSpan(contentSpan);
    }

    @Override
    public void setContent(String content) {
        tweetBean.setContent(content);
    }

    @Override
    public List<CommentBean> getCommentBeans() {
        return tweetBean.getCommentBeans();
    }

    @Override
    public void setCommentBeans(List<CommentBean> commentBeans) {
        tweetBean.setCommentBeans(commentBeans);
    }

    @Override
    public List<PraiseBean> getPraiseBeans() {
        return tweetBean.getPraiseBeans();
    }

    @Override
    public void setPraiseBeans(List<PraiseBean> praiseBeans) {
        tweetBean.setPraiseBeans(praiseBeans);
    }

    @Override
    public List<ImageBean> getImageUrls() {
        throw new UnsupportedOperationException("");
    }

    @Override
    public void setImageUrls(List<ImageBean> imageUrls) {
        throw new UnsupportedOperationException("");
    }

    @Override
    public void setPraiseSpan(SpannableStringBuilder praiseSpan) {
        tweetBean.setPraiseSpan(praiseSpan);
    }

    @Override
    public SpannableStringBuilder getPraiseSpan() {
        return tweetBean.getPraiseSpan();
    }
}
