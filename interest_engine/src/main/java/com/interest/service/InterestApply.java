package com.interest.service;

import com.interest.model.User;

import java.util.List;

/**
 * Created by 431 on 2015/4/9.
 */
public interface InterestApply {
    public List getRecommendInterests(User user);
    public List getRecommendUsers(User user);
}
