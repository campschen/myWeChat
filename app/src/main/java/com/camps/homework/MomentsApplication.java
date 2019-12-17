package com.camps.homework;

import android.app.Application;
import android.content.Context;

import com.camps.homework.beans.UserBean;
import com.camps.homework.others.DataCenter;


public class MomentsApplication extends Application {

    public static Context mContext;

    public UserBean currentUser = new UserBean();


    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        DataCenter.init();
    }

    public static Context getContext(){
        return mContext;
    }

    public UserBean getCurrentUser() {
        return currentUser;
    }
}
