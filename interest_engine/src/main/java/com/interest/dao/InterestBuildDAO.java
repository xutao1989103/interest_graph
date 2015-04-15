package com.interest.dao;

import java.util.List;
import java.util.Map;

/**
 * Created by 431 on 2015/4/14.
 */
public interface InterestBuildDAO {
    public List getInterestsByUserId(Integer userId);

    public List getUsersByInterestIds(List interestIds);

    public List getInterestsByUserIds(List userIds);

    public List getUserInterestList( Map params);
}
