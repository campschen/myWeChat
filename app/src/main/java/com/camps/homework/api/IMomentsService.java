package com.camps.homework.api;


import com.camps.homework.beans.TweetNormalBean;
import com.camps.homework.beans.UserBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * 朋友圈网络请求接口
 */

public interface IMomentsService {

    //获取当前用户信息
    @GET("/user/jsmith")
    Observable<UserBean> getCurrenUser();

    //获取推文列表
    @GET("/user/jsmith/tweets")
    Observable<List<TweetNormalBean>> getTweetList();
}
