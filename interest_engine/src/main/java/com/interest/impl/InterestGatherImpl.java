package com.interest.impl;

import com.interest.dao.InterestGatherDAO;
import com.interest.dao.UserDAO;
import com.interest.enums.Status;
import com.interest.impl.ExtractImpl.InterestExtractImpl;
import com.interest.model.*;
import com.interest.service.InterestExtract;
import com.interest.service.InterestGather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 431 on 2015/4/9.
 */
@Service("interestGatherImpl")
public class InterestGatherImpl implements InterestGather {
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private InterestGatherDAO interestGatherDAO;
    @Resource(name="interestExtractFromJsonImpl")
    InterestExtract interestExtract;
    @Override
    public List<InterestPoint> gather(Input input) {
        List<InterestPoint> interestPoints = new ArrayList<InterestPoint>();
        List<InterestPoint> sonInterests = new ArrayList<InterestPoint>();
        for(Type type:input.getList()){
            List<InterestPoint> temp = interestExtract.extract(type);
            for(InterestPoint interestPoint : temp){
               if(interestPoint.isLeaf()){
                   if(!sonInterests.contains(interestPoint)){
                       sonInterests.add(interestPoint);
                   }
               }else {
                   if(!interestPoints.contains(interestPoint)){
                       interestPoints.add(interestPoint);
                   }
               }
            }
        }
        interestPoints.addAll(sonInterests);
        return interestPoints;
    }


    @Override
    public Status save(User user, List<InterestPoint> interestPoints) {
        try {
            for(InterestPoint interestPoint : interestPoints){
                try {
                    InterestPoint tempPoint = interestPoint;
                    InterestPoint interestPointExist = interestGatherDAO.getInterestByName(interestPoint.getNodeName());
                    if(interestPointExist == null ){
                        InterestPoint parent = null;
                        if(interestPoint.getParentNode()!=null){
                            parent = interestGatherDAO.getInterestByName(interestPoint.getParentNode().getNodeName());
                        }

                        if(parent!=null){
                            interestPoint.setParentId(parent.getInterestId());
                        }
                        saveInterest(interestPoint);
                        int interestId = interestPoint.getInterestId();
                        interestPoint = interestGatherDAO.getInterestById(interestId);
                    }else {
                        interestPoint = interestPointExist;
                    }

                    interestPoint.setType(tempPoint.getType());
                    interestPoint.setParentNode(tempPoint.getParentNode());
                    UserInterest userInterest = new UserInterest(user, interestPoint);
                    if(interestPoint.isLeaf()){
                        saveUserInterest(userInterest);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                    continue;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return Status.FAILED;
        }
        return Status.SUCCESS;
    }

    private Status updateInterest(InterestPoint point){
        try{
            Type typeExist = interestGatherDAO.getTypeById(point.getTypeId());
            if(typeExist!=null){
                Type type = point.getType();
                type.setTypeId(typeExist.getTypeId());
                interestGatherDAO.updateType(type);
            }
        }catch (Exception e){
            e.printStackTrace();
            return Status.FAILED;
        }
        return Status.SUCCESS;
    }

    private Status saveInterest(InterestPoint point) throws Exception{
        Type type = point.getType();
        Map params = new HashMap();
        params.put("type", type.getType());
        params.put("name", type.getName());
        params.put("author", type.getAuthor());
        Type typeExist = interestGatherDAO.getTypeByName(params);
        if(typeExist==null){
            interestGatherDAO.insertType(type);
            point.setTypeId(type.getTypeId());
        }else {
            point.setTypeId(typeExist.getTypeId());
        }
            interestGatherDAO.insertInterest(point);
        return Status.SUCCESS;
    }

    private Status saveUserInterest(UserInterest userInterest) throws  Exception{
        UserInterest exist = getUserInterestExist(userInterest);
        if(exist==null){
            interestGatherDAO.insertUserInterest(userInterest);
        }else {
            exist.setWeight(userInterest.getWeight());
            interestGatherDAO.updateUserInterest(exist);
        }
        return Status.SUCCESS;
    }
    private UserInterest getUserInterestExist(UserInterest ui){
        if(ui==null) return null;
        if(ui.getInterestId()==null || ui.getUserId()==null) return null;
        Integer userId = ui.getUserId();
        Integer interestId = ui.getInterestId();
        Map params = new HashMap();
        params.put("userId", userId);
        params.put("interestId", interestId);
        return interestGatherDAO.getUserInterest(params);
    }
}
