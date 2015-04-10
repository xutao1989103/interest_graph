package com.interest.model;

/**
 * Created by 431 on 2015/4/10.
 */
public class UserInterest {
    private Integer id;
    private Integer userId;
    private Integer interestId;
    private User user;
    private Interest interest;

    public UserInterest(User user, Interest interest){
        this.interest = interest;
        this.user = user;
        this.interestId = interest.getInterestId();
        this.userId = user.getId();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getInterestId() {
        return interestId;
    }

    public void setInterestId(Integer interestId) {
        this.interestId = interestId;
    }

    public Integer getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Interest getInterest() {
        return interest;
    }

    public void setInterest(Interest interest) {
        this.interest = interest;
    }
}
