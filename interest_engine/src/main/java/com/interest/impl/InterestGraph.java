package com.interest.impl;

import com.interest.enums.Status;
import com.interest.model.Input;
import com.interest.model.Interest;
import com.interest.model.User;
import com.interest.service.InterestApply;
import com.interest.service.InterestBuild;
import com.interest.service.InterestGather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by 431 on 2015/4/10.
 */
@Service("interestGraph")
public class InterestGraph {
    private Input input;
    @Resource(name = "interestGatherImpl")
    private InterestGather gather;

    public InterestGraph(){

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

    public List<Interest> gather(){
        return gather.gather(input);
    }

    public Status saveInterests(User user, List<Interest> interests){
        return gather.save(user,interests);
    }
}
