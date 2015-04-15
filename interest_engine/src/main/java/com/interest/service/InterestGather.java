package com.interest.service;

import com.interest.enums.Status;
import com.interest.model.Input;
import com.interest.model.InterestPoint;
import com.interest.model.User;

import java.util.List;

/**
 * Created by 431 on 2015/4/9.
 */
public interface InterestGather {

    public List<InterestPoint> gather(Input input);

    public Status save(User user,List<InterestPoint> interestPoints);
}
