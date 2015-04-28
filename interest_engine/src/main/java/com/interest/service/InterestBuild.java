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

    List<InterestPoint> getInterestsByUserId(Integer userId);

    List<User> getUsersByInterestIds(List interestIds);

    List<InterestPoint> getInterestsByUserIds(List userIds);

    List<InterestPoint> getInterestsByInterestIds(List interestIds);

    InterestGraph getInterestGraph(User user);

    List<UserInterest> getUserInterestList(List interestIds, List userIds);
}
