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

    @Resource(name = "interestBuildImpl")
    private InterestBuildImpl builder;
    private InterestPoint[] interestPoints;
    private User[] users;
    private int[][] edges;

    public InterestGraph(List<InterestPoint> interestPoints, List<User> users){
        this.interestPoints = (InterestPoint[])interestPoints.toArray();
        this.users = (User[])users.toArray();
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
        System.out.println(userInterests);
    }


}
