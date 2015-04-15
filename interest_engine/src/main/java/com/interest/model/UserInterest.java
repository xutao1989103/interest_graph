package com.interest.model;

/**
 * Created by 431 on 2015/4/10.
 */
public class UserInterest {
    private Integer id;
    private Integer userId;
    private Integer interestId;
    private User user;
    private InterestPoint interestPoint;

    public UserInterest(User user, InterestPoint interestPoint){
        this.interestPoint = interestPoint;
        this.user = user;
        this.interestId = interestPoint.getInterestId();
        this.userId = user.getId();
    }

    public UserInterest(){

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

    public InterestPoint getInterestPoint() {
        return interestPoint;
    }

    public void setInterestPoint(InterestPoint interestPoint) {
        this.interestPoint = interestPoint;
    }
}
