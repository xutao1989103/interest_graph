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
    private Integer weight;
    private Integer times;
    private Integer stars;
    private boolean love;
    private boolean dislike;

    private final Integer LIKE_POINT = 10;
    private final Integer DISLIKE_POINT = -10;
    private final Integer MIN_POINT = 0;
    private final Integer MAX_POINT = 100;

    public UserInterest(User user, InterestPoint interestPoint){
        this.interestPoint = interestPoint;
        this.user = user;
        this.interestId = interestPoint.getInterestId();
        this.userId = user.getId();
        Type type = interestPoint.getType();
        this.times = type.getTimes();
        this.stars = type.getStars();
        this.love = type.isLike();
        this.dislike = type.isDislike();
        this.weight = countWeight();
    }

    public UserInterest(){

    }

    private Integer countWeight(){
        Integer result = 0;
        result += this.times;
        if(this.love) result += LIKE_POINT;
        if(this.dislike) result += DISLIKE_POINT;
        if(result<MIN_POINT) result = MIN_POINT;
        if(result>MAX_POINT) result = MAX_POINT;
        return result;
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

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getTimes() {
        return times;
    }

    public void setTimes(Integer times) {
        this.times = times;
    }

    public Integer getStars() {
        return stars;
    }

    public void setStars(Integer stars) {
        this.stars = stars;
    }

    public boolean isLove() {
        return love;
    }

    public void setLove(boolean love) {
        this.love = love;
    }

    public boolean isDislike() {
        return dislike;
    }

    public void setDislike(boolean dislike) {
        this.dislike = dislike;
    }
}
