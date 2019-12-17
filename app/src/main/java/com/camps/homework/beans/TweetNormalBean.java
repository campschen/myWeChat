package com.camps.homework.beans;

import android.content.Context;
import android.text.SpannableStringBuilder;

import com.camps.homework.Constants;
import com.camps.homework.utils.SpanUtils;
import com.camps.homework.utils.Utils;
import com.camps.homework.others.enums.TranslationState;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * 推文实体类
 */
public class TweetNormalBean extends ErrorBean{

    private int viewType;

    @SerializedName("content")
    private String content;

    @SerializedName("comments")
    private List<CommentBean> commentBeans;

    private List<PraiseBean> praiseBeans;

    @SerializedName("images")
    private List<ImageBean> imageUrls;

    @SerializedName("sender")
    private UserBean userBean;

    private OtherInfoBean otherInfoBean;

    private boolean isShowPraise;

    private boolean isExpanded;

    public boolean isExpanded() {
        return isExpanded;
    }

    public void setExpanded(boolean expanded) {
        isExpanded = expanded;
    }

    private boolean isShowComment;;

    private boolean isShowCheckAll;

    public boolean isShowCheckAll() {
        return isShowCheckAll;
    }

    public void setShowCheckAll(boolean showCheckAll) {
        isShowCheckAll = showCheckAll;
    }

    private TranslationState translationState = TranslationState.START;

    public void setTranslationState(TranslationState translationState) {
        this.translationState = translationState;
    }

    public TranslationState getTranslationState() {
        return translationState;
    }

    public boolean isShowComment() {
        isShowComment  = commentBeans != null && commentBeans.size() > 0;
        return isShowComment;
    }

    public boolean isShowPraise() {
        return isShowPraise;
    }

    public OtherInfoBean getOtherInfoBean() {
        return otherInfoBean;
    }

    public void setOtherInfoBean(OtherInfoBean otherInfoBean) {
        this.otherInfoBean = otherInfoBean;
    }

    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
    }

    public UserBean getUserBean() {
        return userBean;
    }

    public int getViewType() {
        return viewType;
    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
    }

    public String getContent() {
        return content;
    }

    public SpannableStringBuilder getContentSpan() {
        return contentSpan;
    }

    public void setContentSpan(SpannableStringBuilder contentSpan) {
        this.contentSpan = contentSpan;
        this.isShowCheckAll = Utils.calculateShowCheckAllText(contentSpan.toString());
    }

    private SpannableStringBuilder contentSpan;


    public void setContent(String content) {
        this.content = content;
        setContentSpan(new SpannableStringBuilder(content));
    }

    public List<CommentBean> getCommentBeans() {
        return commentBeans;
    }

    public void setCommentBeans(List<CommentBean> commentBeans) {
        this.commentBeans = commentBeans;
    }

    public List<PraiseBean> getPraiseBeans() {
        return praiseBeans;
    }

    public void setPraiseBeans(List<PraiseBean> praiseBeans) {
        isShowPraise = praiseBeans != null && praiseBeans.size() > 0;
        this.praiseBeans = praiseBeans;
    }


    public List<ImageBean> getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(List<ImageBean> imageUrls) {
        this.imageUrls = imageUrls;
    }

    public void setPraiseSpan(SpannableStringBuilder praiseSpan) {
        this.praiseSpan = praiseSpan;
    }

    public SpannableStringBuilder getPraiseSpan() {
        return praiseSpan;
    }

    private SpannableStringBuilder praiseSpan;


    public void build(Context context) {

        if(commentBeans == null || commentBeans.size() == 0){
            return;
        }

        for (int i = 0; i < commentBeans.size(); i++) {
            CommentBean commentBean = commentBeans.get(i);

            if (commentBean.getCommentType() == Constants.CommentType.COMMENT_TYPE_SINGLE) {
                commentBean.setCommentContentSpan(SpanUtils.makeSingleCommentSpan(context, commentBean.getChildUserName(), commentBean.getCommentContent()));
            } else {
                commentBean.setCommentContentSpan(SpanUtils.makeReplyCommentSpan(context, commentBean.getParentUserName(), commentBean.getChildUserName(), commentBean.getCommentContent()));
            }

        }


    }



}
