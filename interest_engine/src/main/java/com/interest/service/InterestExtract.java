package com.interest.service;

import com.interest.model.Interest;
import com.interest.model.Type;

import java.util.List;

/**
 * Created by 431 on 2015/4/10.
 */
public interface InterestExtract {
    public List<Interest> extract(Type type);
}
