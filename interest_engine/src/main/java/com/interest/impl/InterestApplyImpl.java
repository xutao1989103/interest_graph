package com.interest.impl;

import com.interest.model.InterestGraph;
import com.interest.model.InterestPoint;
import com.interest.model.User;
import com.interest.service.InterestApply;
import com.interest.service.InterestBuild;
import com.interest.util.GraphUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by 431 on 2015/4/15.
 */
@Service("interestApplyImpl")
public class InterestApplyImpl implements InterestApply {
    @Resource(name= "interestBuildImpl")
    private InterestBuild builder;

    @Override
    public List getRecommendInterests(User user, Integer k) {
        if(k<1) return new ArrayList();
        InterestGraph graph = builder.getInterestGraph(user);
        List<InterestPoint> myInterests = builder.getInterestsByUserId(user.getId());
        Map<Integer,Integer> idMapCount =  GraphUtil.getTopKinterests(user, graph, Integer.MAX_VALUE);
        List<Integer> ids = new ArrayList<Integer>(idMapCount.keySet());
        List<InterestPoint> allInterests = builder.getInterestsByInterestIds(ids);
        allInterests.removeAll(myInterests);
        allInterests = allInterests.size()<k? allInterests:allInterests.subList(0,k-1);
        return allInterests;
    }

    @Override
    public List getRecommendUsers(User user, Integer k) {
        InterestGraph graph = builder.getInterestGraph(user);
        List<User> users = graph.getUsers();
        users.remove(user);
        return users;
    }
}
