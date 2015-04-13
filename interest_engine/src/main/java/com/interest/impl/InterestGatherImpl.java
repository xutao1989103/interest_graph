package com.interest.impl;

import com.interest.dao.InterestGatherDAO;
import com.interest.dao.UserDAO;
import com.interest.enums.Status;
import com.interest.model.*;
import com.interest.service.InterestExtract;
import com.interest.service.InterestGather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 431 on 2015/4/9.
 */
@Service("interestGatherImpl")
public class InterestGatherImpl implements InterestGather {
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private InterestGatherDAO interestGatherDAO;
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
    public Status save(User user, List<Interest> interests) {

        try {
            for(Interest interest: interests){
                int count = interestGatherDAO.insertInterest(interest);
                int interestId = interest.getInterestId();
                interest = interestGatherDAO.getInterestById(interestId);
                UserInterest userInterest = new UserInterest(user,interest);
                interestGatherDAO.insertUserInterest(userInterest);
            }
        }catch (Exception e){
            e.printStackTrace();
            return Status.FAILED;
        }
        return Status.SUCCESS;
    }
}
