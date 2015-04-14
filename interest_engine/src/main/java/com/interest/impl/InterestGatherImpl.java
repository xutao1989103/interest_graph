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
    public List<InterestPoint> gather(Input input) {
        List<InterestPoint> interestPoints = new ArrayList<InterestPoint>();
        for(Type type:input.getList()){
            List<InterestPoint> temp = interestExtract.extract(type);
            for(InterestPoint interestPoint : temp){
               if(!interestPoints.contains(interestPoint)){
                   interestPoints.add(interestPoint);
               }
            }
        }
        return interestPoints;
    }


    @Override
    public Status save(User user, List<InterestPoint> interestPoints) {

        try {
            for(InterestPoint interestPoint : interestPoints){
                InterestPoint interestPointExist = interestGatherDAO.getInterestByName(interestPoint.getNodeName());
                if(interestPointExist == null){
                    int count = interestGatherDAO.insertInterest(interestPoint);
                    int interestId = interestPoint.getInterestId();
                    interestPoint = interestGatherDAO.getInterestById(interestId);
                }else {
                    interestPoint = interestPointExist;
                }

                UserInterest userInterest = new UserInterest(user, interestPoint);
                if(interestPoint.isLeaf()){
                    interestGatherDAO.insertUserInterest(userInterest);
                }

            }
        }catch (Exception e){
            e.printStackTrace();
            return Status.FAILED;
        }
        return Status.SUCCESS;
    }
}
