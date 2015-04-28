package com.interest.model;

/**
 * Created by 431 on 2015/4/28.
 */
public class GraphItem {
    private Integer position;
    private Integer weight;

    public GraphItem(Integer position, Integer weight){
        this.position = position;
        this.weight = weight;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }
}
