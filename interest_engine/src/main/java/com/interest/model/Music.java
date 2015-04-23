package com.interest.model;


/**
 * Created by 431 on 2015/4/9.
 */
public class Music  extends Type{
    private String singner;
    private String musicType;

    public Music(String name){
        super(name);
    }

    public String getSingner() {
        return singner;
    }

    public void setSingner(String singner) {
        this.singner = singner;
    }

    public String getMusicType() {
        return musicType;
    }

    public void setMusicType(String musicType) {
        this.musicType = musicType;
    }
}
