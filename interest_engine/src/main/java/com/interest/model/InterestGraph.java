package com.interest.model;


import java.io.Serializable;
import java.util.*;

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

    public Map<Integer,GraphItem> getRowCount(int column){
        int index = 0;
        Map<Integer,GraphItem> result = new LinkedHashMap<Integer, GraphItem>();
        if(column<0 || column> users.size()-1) return result;
        for(int i = 0; i< interestPoints.size(); i++){
            if(edges[i][column]>0){
                result.put(index, new GraphItem(i,edges[i][column]));
                index++;
            }
        }
        return result;
    }

    public Map<Integer,GraphItem> gerColumnCount(int row){
        int index = 0;
        Map<Integer,GraphItem> result = new LinkedHashMap<Integer, GraphItem>();
        if(row<0 || row> interestPoints.size()-1) return result;
        for(int i=0; i<users.size(); i++){
            if(edges[row][i]>0){
                result.put(index, new GraphItem(i,edges[row][i]));
                index++;
            }
        }
        return result;
    }
}
