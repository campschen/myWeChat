package com.camps.homework.contract;

import com.camps.homework.beans.TweetNormalBean;
import com.camps.homework.beans.UserBean;

import java.util.List;

/**
 * 契约类,定义朋友圈用到的一些接口方法
 */

public class MomentsContract {

    public interface MomentsView {
        int TYPE_UPDATE = 1;
        int TYPE_APPEND = 2;

        void updateUserView(UserBean currentUser);
        void updateTweetList(List<TweetNormalBean> tweetList);
    }


    public interface MomentsPresenter {
        void getTweetList(int currentPage, int pageSize);
        void getCurrentUser();
    }
}
