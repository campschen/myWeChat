package com.camps.homework.beans;

import com.camps.homework.Constants;

/**
 * 图片信息
 */
public class ImageBean {
    private String url;

    public String getUrl() {
        url = Constants.IMAGE_TWEET_URL[(int)(Math.random()*5)];
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
