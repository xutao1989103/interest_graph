package com.interest.impl;

import com.interest.model.Input;
import com.interest.model.Interest;
import com.interest.service.InterestApply;
import com.interest.service.InterestBuild;
import com.interest.service.InterestGather;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 431 on 2015/4/10.
 */
@Service
public class InterestGraph {
    private Input input;
    private InterestGather gather;
    private InterestBuild build;
    private InterestApply apply;

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

    public InterestBuild getBuild() {
        return build;
    }

    public void setBuild(InterestBuild build) {
        this.build = build;
    }

    public InterestApply getApply() {
        return apply;
    }

    public void setApply(InterestApply apply) {
        this.apply = apply;
    }

    public List<Interest> gather(){
        return gather.gather(input);
    }
}
