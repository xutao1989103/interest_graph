package com.interest.model;

import com.interest.enums.InterestType;

import java.util.List;

/**
 * Created by 431 on 2015/4/9.
 */
public abstract class Type {
    private InterestType type;
    private String name;
    private List<String> tags;

    public Type(String name){
        this.name = name;
    }

    public InterestType getType() {
        return type;
    }

    public void setType(InterestType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}
