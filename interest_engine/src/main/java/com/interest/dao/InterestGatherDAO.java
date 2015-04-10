package com.interest.dao;

import com.interest.model.Interest;
import com.interest.model.UserInterest;

/**
 * Created by 431 on 2015/4/10.
 */
public interface InterestGatherDAO {
    public int insertInterest(Interest interest);
    public Interest getInterestById(Integer id);
    public int insertUserInterest(UserInterest userInterest);
}
