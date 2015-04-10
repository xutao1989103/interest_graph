package com.interest.impl;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.interest.enums.Status;
import com.interest.model.Input;
import com.interest.model.Interest;
import com.interest.model.Type;
import com.interest.model.User;
import com.interest.service.InterestExtract;
import com.interest.service.InterestGather;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 431 on 2015/4/9.
 */
@Service
public class InterestGatherImpl implements InterestGather {
    InterestExtract interestExtract = new InterestExtractImpl();
    @Override
    public List<Interest> gather(Input input) {
        List<Interest> interests = new ArrayList<Interest>();
        for(Type type:input.getList()){
            List<Interest> temp = interestExtract.extract(type);
            for(Interest interest : temp){
               if(!interests.contains(interest)){
                   interests.add(interest);
               }
            }
        }
        return interests;
    }


    @Override
    public Status save(User user, List interests) {
        return null;
    }
}
