package com.interest.model;

/**
 * Created by 431 on 2015/4/15.
 */
public class Result {
    public Integer code;
    public Object info;

    public Result(){
        this.code = 200;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Object getInfo() {
        return info;
    }

    public void setInfo(Object info) {
        this.info = info;
    }
}
