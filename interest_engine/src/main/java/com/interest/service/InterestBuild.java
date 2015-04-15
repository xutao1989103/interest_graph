package com.interest.service;

import com.interest.model.InterestPoint;
import com.interest.model.InterestGraph;
import com.interest.model.User;
import com.interest.model.UserInterest;

import java.util.List;

/**
 * Created by 431 on 2015/4/9.
 */
public interface InterestBuild {

    public List<InterestPoint> getInterestsByUserId(Integer userId);

    public List<User> getUsersByInterestIds(List interestIds);

    public List<InterestPoint> getInterestsByUserIds(List userIds);

    public InterestGraph getInterestGraph(User user);

    public List<UserInterest> getUserInterestList(List interestIds, List userIds);
}
