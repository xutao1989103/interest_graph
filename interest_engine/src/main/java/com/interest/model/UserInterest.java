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
    private Integer typeId;

    private final Integer LIKE_POINT = 10;
    private final Integer DISLIKE_POINT = -10;
    private final Integer MIN_POINT = 0;
    private final Integer MAX_POINT = 100;

    public UserInterest(User user, InterestPoint interestPoint){
        this.interestPoint = interestPoint;
        this.user = user;
        this.interestId = interestPoint.getInterestId();
        this.userId = user.getId();
        this.weight = countWeight(interestPoint);
        this.typeId = interestPoint.getType().getTypeId();
    }

    public UserInterest(){

    }

    private Integer countWeight(InterestPoint interestPoint){
        Integer result = 0;
        Type type = interestPoint.getType();
        result += type.getTimes();
        if(type.isLike()) result += LIKE_POINT;
        if(type.isDislike()) result += DISLIKE_POINT;
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

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }
}
