package com.interest.model;

import com.interest.enums.InterestType;

import java.util.List;

/**
 * Created by 431 on 2015/4/10.
 */
public abstract class Input {
    private InterestType type;

    public InterestType getType() {
        return type;
    }

    public void setType(InterestType type) {

        this.type = type;
    }

    public abstract List<Type> getList();
}
