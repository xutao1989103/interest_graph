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
    private Integer times;
    private boolean like;
    private boolean dislike;

    public Type(String name){
        this.name = name;
    }
    public Type(String name, Integer times, boolean like, boolean dislike){
        this.name = name;
        this.times = times;
        this.like = like;
        this.dislike = dislike;
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

    public Integer getTimes() {
        return times;
    }

    public boolean isLike() {
        return like;
    }

    public boolean isDislike() {
        return dislike;
    }
}
