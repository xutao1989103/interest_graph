package com.interest.impl;

import com.interest.enums.Status;
import com.interest.model.Input;
import com.interest.model.InterestGraph;
import com.interest.model.InterestPoint;
import com.interest.model.User;
import com.interest.service.InterestApply;
import com.interest.service.InterestBuild;
import com.interest.service.InterestGather;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by 431 on 2015/4/10.
 */
@Service("interestGraphImpl")
public class InterestGraphImpl {
    private Input input;
    @Resource(name = "interestGatherImpl")
    private InterestGather gather;
    @Resource(name = "interestBuildImpl")
    private InterestBuild builder;
    @Resource(name = "interestApplyImpl")
    private InterestApply applyer;

    public InterestGraphImpl(){

    }

    public Input getInput() {
        return input;
    }

    public void setInput(Input input) {
        this.input = input;
    }

    public InterestGather getGather() {
        return gather;
    }

    public void setGather(InterestGather gather) {
        this.gather = gather;
    }

    public List<InterestPoint> gather(){
        return gather.gather(input);
    }

    public Status saveInterests(User user, List<InterestPoint> interestPoints){
        return gather.save(user, interestPoints);
    }

    public InterestGraph buildGraph(User user){
        return builder.getInterestGraph(user);
    }

    public List<InterestPoint> getRecommendInterests(User user){
        return applyer.getRecommendInterests(user);
    }
    public List<User> getRecommendUsers(User user){
        return applyer.getRecommendUsers(user);
    }
}
