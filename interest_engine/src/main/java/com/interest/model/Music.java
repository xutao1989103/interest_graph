package com.interest.model;

import java.util.List;

/**
 * Created by 431 on 2015/4/9.
 */
public class Music  extends Type{
    private String singner;
    private String type;

    public String getSingner() {
        return singner;
    }

    public void setSingner(String singner) {
        this.singner = singner;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
