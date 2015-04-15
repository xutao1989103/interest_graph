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
    private List<InterestPoint> interestPoints;
    private  List<User> users;
    private List<UserInterest> userInterests;
    private int[][] edges;

    public InterestGraph(List<InterestPoint> interestPoints, List<User> users){
        this.interestPoints = interestPoints;
        this.users = users;
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

    public List<UserInterest> getUserInterests() {
        return userInterests;
    }

    public void setUserInterests(List<UserInterest> userInterests) {
        this.userInterests = userInterests;
    }
}
