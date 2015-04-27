package com.interest.model;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 431 on 2015/4/14.
 */
public class InterestGraph implements Serializable{
    private List<InterestPoint> interestPoints;
    private  List<User> users;
    private List<UserInterest> userInterests;
    private int[][] edges;

    public InterestGraph(List<InterestPoint> interestPoints, List<User> users){
        this.interestPoints = interestPoints;
        this.users = users;
    }

    public InterestGraph(){
        this.interestPoints = new ArrayList<InterestPoint>();
        this.users = new ArrayList<User>();
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
