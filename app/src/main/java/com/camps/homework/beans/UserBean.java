package com.camps.homework.beans;

import com.google.gson.annotations.SerializedName;

public class UserBean {

    /**
     * {
     *   "profile-image": "http://img2.findthebest.com/sites/default/files/688/media/images/Mingle_159902_i0.png",
     *   "avatar": "http://info.thoughtworks.com/rs/thoughtworks2/images/glyph_badge.png",
     *   "nick": "John Smith",
     *   "username": "jsmith"
     * }
     */



    @SerializedName("profile-image")
    private String profileImgUr;

    @SerializedName("avatar")
    private String userAvatarUrl;

    @SerializedName("nick")
    private String userName;

    @SerializedName("username")
    private String userId;

    public String getUserAvatarUrl() {
        return userAvatarUrl;
    }

    public void setUserAvatarUrl(String userAvatarUrl) {
        this.userAvatarUrl = userAvatarUrl;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserId() {
        return userId;
    }

    public String getProfileImgUr() {
        return profileImgUr;
    }

    public void setProfileImgUr(String profileImgUr) {
        this.profileImgUr = profileImgUr;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
