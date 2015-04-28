package com.interest.dao;

import java.util.List;
import java.util.Map;

/**
 * Created by 431 on 2015/4/14.
 */
public interface InterestBuildDAO {
    List getInterestsByUserId(Integer userId);

    List getUsersByInterestIds(List interestIds);

    List getInterestsByUserIds(List userIds);

    List getInterestsByInterestIds(List interestIds);

    List getUserInterestList( Map params);
}
