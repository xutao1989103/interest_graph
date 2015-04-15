package com.interest.model;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.interest.impl.InterestBuildImpl;
import com.interest.impl.InterestGatherImpl;
import com.interest.service.InterestBuild;
import com.interest.service.InterestGather;

import javax.annotation.Resource;
import java.util.List;

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
