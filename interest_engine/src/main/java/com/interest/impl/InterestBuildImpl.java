package com.interest.impl;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.interest.dao.InterestBuildDAO;
import com.interest.dao.InterestGatherDAO;
import com.interest.dao.UserDAO;
import com.interest.model.*;
import com.interest.model.InterestGraph;
import com.interest.service.InterestBuild;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 431 on 2015/4/14.
 */
@Service("interestBuildImpl")
public class InterestBuildImpl implements InterestBuild {

    @Autowired
    private UserDAO userDAO;
    @Autowired
    private InterestBuildDAO interestBuildDAO;

    @Override
    public List<InterestPoint> getInterestsByUserId(Integer userId) {
        return interestBuildDAO.getInterestsByUserId(userId);
    }

    @Override
    public List<User> getUsersByInterestIds(List interestIds) {
        return interestBuildDAO.getUsersByInterestIds(interestIds);
    }

    @Override
    public List<InterestPoint> getInterestsByUserIds(List userIds) {
        return interestBuildDAO.getInterestsByUserIds(userIds);
    }

    @Override
    public InterestGraph getInterestGraph(User user) {
        List<InterestPoint> interestPointList = getInterestsByUserId(user.getId());

        List<User> userList = getUsersByInterestIds(Lists.transform(interestPointList,new Function<InterestPoint,Integer>() {
            @Override
            public Integer apply(InterestPoint o) {
                return o.getInterestId();
            }
        }));

        List<InterestPoint> allInterests = getInterestsByUserIds(Lists.transform(userList,new Function<User,Integer>() {
            @Override
            public Integer apply(User o) {
                return o.getId();
            }
        }));
        InterestGraph interestGraph = new InterestGraph(allInterests, userList);
        initEdges(interestGraph);
        return interestGraph;
    }

    private void initEdges(InterestGraph interestGraph){
        List<InterestPoint> interestPoints = interestGraph.getInterestPoints();
        List<User> users = interestGraph.getUsers();
        List<Integer> interestIds = Lists.transform(interestPoints,new Function<InterestPoint,Integer>() {
            @Override
            public Integer apply(InterestPoint o) {
                return o.getInterestId();
            }
        });
        List<Integer> userIds = Lists.transform(users,new Function<User, Integer>() {
            @Override
            public Integer apply(User user) {
                return user.getId();
            }
        });

        List<UserInterest> userInterests = getUserInterestList(interestIds, userIds);
        interestGraph.setUserInterests(userInterests);
        setEdges(interestGraph,userInterests);
    }

    private void setEdges(InterestGraph interestGraph, List<UserInterest> userInterests){
        Map interestMap = new HashMap();
        List<InterestPoint> interestPoints = interestGraph.getInterestPoints();
        List<User> users = interestGraph.getUsers();
        int[][] edges = new int[interestPoints.size()][users.size()];
        for(int i = 0; i< interestPoints.size();i++){
            interestMap.put(interestPoints.get(i).getInterestId(), i);
        }
        Map userMap = new HashMap();
        for(int i = 0; i< users.size();i++){
            userMap.put(users.get(i).getId(),i);
        }
        for(UserInterest ui: userInterests){
            edges[(Integer)interestMap.get(ui.getInterestId())][(Integer)userMap.get(ui.getUserId())] = 1;
        }
        interestGraph.setEdges(edges);
    }

    public List<UserInterest> getUserInterestList(List interestIds, List userIds){
        if(interestIds.size() == 0 || userIds.size() == 0){
            return new ArrayList<UserInterest>();
        }else {
            Map params = new HashMap<String, Object>();
            params.put("interestIds",interestIds);
            params.put("userIds", userIds);
            return interestBuildDAO.getUserInterestList(params);
        }
    }
}
