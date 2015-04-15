package com.interest.model;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.interest.impl.InterestBuildImpl;
import com.interest.impl.InterestGatherImpl;
import com.interest.service.InterestBuild;
import com.interest.service.InterestGather;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 431 on 2015/4/14.
 */
public class InterestGraph {
    private InterestBuild builder;
    private List<InterestPoint> interestPoints;
    private  List<User> users;
    private int[][] edges;

    public InterestGraph(InterestBuild builder,List<InterestPoint> interestPoints, List<User> users){
        this.builder = builder;
        this.interestPoints = interestPoints;
        this.users = users;
        this.edges = new int[interestPoints.size()][users.size()];
        this.initEdges(interestPoints,users);
    }
    public void initEdges(List<InterestPoint> interestPoints, List<User> users){
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

        List<UserInterest> userInterests = builder.getUserInterestList(interestIds, userIds);
        setEdges(interestPoints,users,userInterests);
    }

    private void setEdges(List<InterestPoint> interestPoints, List<User> users, List<UserInterest> userInterests){
        Map interestMap = new HashMap();
        for(int i = 0; i< interestPoints.size();i++){
           interestMap.put(interestPoints.get(i).getInterestId(), i);
        }
        Map userMap = new HashMap();
        for(int i = 0; i< users.size();i++){
            userMap.put(users.get(i).getId(),i);
        }
        for(UserInterest ui: userInterests){
            this.edges[(Integer)interestMap.get(ui.getInterestId())][(Integer)userMap.get(ui.getUserId())] = 1;
        }
    }

    public InterestBuild getBuilder() {
        return builder;
    }

    public void setBuilder(InterestBuild builder) {
        this.builder = builder;
    }

    public List<InterestPoint> getInterestPoints() {
        return interestPoints;
    }

    public void setInterestPoints(List<InterestPoint> interestPoints) {
        this.interestPoints = interestPoints;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public int[][] getEdges() {
        return edges;
    }

    public void setEdges(int[][] edges) {
        this.edges = edges;
    }
}
