package com.interest.dao;

import com.interest.model.InterestPoint;
import com.interest.model.UserInterest;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by 431 on 2015/4/10.
 */
public interface InterestGatherDAO {
    public int insertInterest(InterestPoint interestPoint);

    public InterestPoint getInterestById(Integer id);

    public InterestPoint getInterestByName(String name);

    public int insertUserInterest(UserInterest userInterest);
}
