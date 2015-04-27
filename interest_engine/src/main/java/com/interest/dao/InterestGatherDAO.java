package com.interest.dao;

import com.interest.model.InterestPoint;
import com.interest.model.UserInterest;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by 431 on 2015/4/10.
 */
public interface InterestGatherDAO {
    int insertInterest(InterestPoint interestPoint);

    InterestPoint getInterestById(Integer id);

    InterestPoint getInterestByName(String name);

    UserInterest getUserInterest(Map params);

    int insertUserInterest(UserInterest userInterest);

    void updateUserInterest(UserInterest userInterest);
}
