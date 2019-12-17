package com.camps.homework.presenter;

import android.text.SpannableStringBuilder;
import android.text.TextUtils;

import com.camps.frame.utils.log.Logger;
import com.camps.homework.Constants;
import com.camps.homework.MomentsActivity;
import com.camps.frame.base.BasePresenter;

import com.camps.homework.beans.ImageBean;
import com.camps.homework.beans.TweetNormalBean;
import com.camps.homework.beans.TweetWordBean;
import com.camps.homework.beans.UserBean;
import com.camps.homework.contract.MomentsContract;
import com.camps.frame.exception.ExceptionHandle;
import com.camps.frame.http.Observer;
import com.camps.frame.http.SubscriptionManager;

import com.camps.homework.model.MomentsModel;
import com.camps.frame.base.IModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class MomentsPresenter extends BasePresenter<MomentsActivity> implements MomentsContract.MomentsPresenter{

    private static final Logger LOG = Logger.getLogger(MomentsPresenter.class);

    private List<TweetNormalBean> allTweet = new ArrayList<>();

    @Override
    public HashMap<String, IModel> getiModelMap() {
        return loadModelMap(new MomentsModel());
    }

    @Override
    public HashMap<String, IModel> loadModelMap(IModel... models) {
        HashMap<String, IModel> map = new HashMap<>();
        map.put("moments", models[0]);
        return map;
    }


    @Override
    public void getTweetList(int currentPage, int pageSize) {
        if(currentPage == 1) {
            ((MomentsModel) getiModelMap().get("moments")).getTweetList(new NetworkObserver());
        }else {
            int startIndex = (currentPage - 1) * pageSize;
            int endIndex = currentPage * pageSize - startIndex < pageSize ? allTweet.size() : currentPage * pageSize;

            if(currentPage * pageSize > allTweet.size()){
                getIView().showNoMore(currentPage);
            }else{
                Observable.just(allTweet.subList(startIndex, endIndex)).observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(new LocalObserver());
            }

        }
    }

    @Override
    public void getCurrentUser() {
        ((MomentsModel) getiModelMap().get("moments")).getCurrenUser(new Observer<UserBean>() {
            @Override
            protected void OnDisposable(Disposable d) {
                SubscriptionManager.getInstance().add(d);
            }

            @Override
            protected void OnSuccess(UserBean testBean) {
                getIView().updateUserView(testBean);
            }

            @Override
            protected void OnFail(ExceptionHandle.ResponseException e) {
                LOG.e( e.getMessage());
            }

            @Override
            protected void OnCompleted() {

            }
        });
    }


    //网络请求观察者
    class NetworkObserver extends Observer<List<TweetNormalBean>> {
        @Override
        protected void OnDisposable(Disposable d) {
            SubscriptionManager.getInstance().add(d);
        }

        @Override
        protected void OnSuccess(List<TweetNormalBean> beanList) {
            allTweet = makeTweetList(beanList);
            getIView().updateTweetList(allTweet.subList(0, getIView().getPageSize()));
        }

        @Override
        protected void OnFail(ExceptionHandle.ResponseException e) {
            LOG.e(e.getMessage());
        }

        @Override
        protected void OnCompleted() {
            getIView().done();
        }

    }

    //模拟数据观察者
    class LocalObserver extends Observer<List<TweetNormalBean>> {
        @Override
        protected void OnDisposable(Disposable d) {
            SubscriptionManager.getInstance().add(d);
        }

        @Override
        protected void OnSuccess(List<TweetNormalBean> beanList) {
            getIView().appendTweetList(beanList);
        }

        @Override
        protected void OnFail(ExceptionHandle.ResponseException e) {
            LOG.e(e.getMessage());
        }

        @Override
        protected void OnCompleted() {
        }

    }

    /**
     * 整理原始数据
     * @param originalList 原始网络请求数据
     * @return
     */
    public List<TweetNormalBean> makeTweetList(List<TweetNormalBean> originalList){
        if(originalList == null || originalList.size() == 0){
            return originalList;
        }

        ArrayList<TweetNormalBean> newList = new ArrayList<>(originalList.size());
        for (int i = 0; i < originalList.size(); i++) {
            //去掉无效数据
            if (!TextUtils.isEmpty(originalList.get(i).getError()) ||
                    (TextUtils.isEmpty(originalList.get(i).getContent()) && (originalList.get(i).getImageUrls() == null || originalList.get(i).getImageUrls().size() == 0))) {
                continue;
            }

            TweetNormalBean tweetBean = originalList.get(i);

            //固定图片url
            if(Constants.LOCAL_IMG_URL) {
                if (tweetBean.getUserBean() != null) {
                    tweetBean.getUserBean().setUserAvatarUrl(Constants.IMAGE_USER_URL[(int) (Math.random() * 5)]);
                }

                if (tweetBean.getImageUrls() != null && tweetBean.getImageUrls().size() > 0) {
                    for (ImageBean imageBean : tweetBean.getImageUrls()) {
                        imageBean.setUrl(Constants.IMAGE_TWEET_URL[(int) (Math.random() * 5)]);
                    }
                }
            }

            tweetBean.build(getIView());

            //根据图片url的有无，选择数据模型
            if(tweetBean.getImageUrls() == null || tweetBean.getImageUrls().size() == 0){
                newList.add(new TweetWordBean(tweetBean));

            }else{
                newList.add(tweetBean);

            }

            if(!TextUtils.isEmpty(tweetBean.getContent())) {
                tweetBean.setContentSpan(new SpannableStringBuilder(tweetBean.getContent()));
            }

        }


        return newList;

    }


}
