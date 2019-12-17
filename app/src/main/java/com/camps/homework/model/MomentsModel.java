package com.camps.homework.model;

import com.camps.homework.api.IMomentsService;

import com.camps.homework.beans.TweetNormalBean;
import com.camps.homework.beans.UserBean;
import com.camps.homework.http.MomentsRetrofit;
import com.camps.frame.base.IModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class MomentsModel implements IModel {
    private List<TweetNormalBean> tweetBeans = new ArrayList<>();

    /**
     * 获取当前用户信息
     */
    public void getCurrenUser(Observer<UserBean> observer){
        MomentsRetrofit.getTargetService(IMomentsService.class).getCurrenUser()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(observer);
    }

    /**
     * 获取当前用户朋友圈推文列表
     */
    public void getTweetList(Observer<List<TweetNormalBean>> observer){
            MomentsRetrofit.getTargetService(IMomentsService.class).getTweetList()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(observer);
    }


}
