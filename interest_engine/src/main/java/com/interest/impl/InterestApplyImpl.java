package com.interest.impl;

import com.interest.dao.InterestGatherDAO;
import com.interest.enums.InterestType;
import com.interest.model.*;
import com.interest.service.InterestApply;
import com.interest.service.InterestBuild;
import com.interest.util.GraphUtil;
import com.interest.util.MusicUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 431 on 2015/4/15.
 */
@Service("interestApplyImpl")
public class InterestApplyImpl implements InterestApply {
    @Resource(name= "interestBuildImpl")
    private InterestBuild builder;
    @Autowired
    private InterestGatherDAO interestGatherDAO;

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
        enrichInterests(user, allInterests);
        return allInterests;
    }

    @Override
    public List getRecommendUsers(User user, Integer k) {
        InterestGraph graph = builder.getInterestGraph(user);
        List<User> users = graph.getUsers();
        users.remove(user);
        return users;
    }

    private void enrichInterests(User user, List<InterestPoint> points){
        for(InterestPoint point: points){
            Map params = new HashMap();
            params.put("userId", user.getId());
            params.put("interestId", point.getInterestId());
            UserInterest ui = interestGatherDAO.getUserInterest(params);
            if(ui!=null){
                Type type = interestGatherDAO.getTypeById(ui.getTypeId());
                Music music = new Music(type.getName());
                music.setTitle(type.getName());
                music.setAuthor(type.getAuthor());
                music.setTypeId(InterestType.MUSIC.getVaule());
                music.setArtist(type.getAuthor());
                music.setUrls(MusicUtil.handleXml(music.getName(),music.getAuthor()));
                point.setType(music);
            }
        }
    }
}
