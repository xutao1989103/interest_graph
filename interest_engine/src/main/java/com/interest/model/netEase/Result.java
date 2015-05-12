package com.interest.model.netEase;

/**
 * Created by 431 on 2015/5/11.
 */
public class Result {
    private Userprofile[] userprofiles;
    private Integer userprofileCount;

    public Userprofile[] getUserprofiles() {
        return userprofiles;
    }

    public void setUserprofiles(Userprofile[] userprofiles) {
        this.userprofiles = userprofiles;
    }

    public Integer getUserprofileCount() {
        return userprofileCount;
    }

    public void setUserprofileCount(Integer userprofileCount) {
        this.userprofileCount = userprofileCount;
    }

    public Result(){


    }
}
