package com.interest.model.netEase;

/**
 * Created by 431 on 2015/5/13.
 */
public class SongDetailArtist {
    private Integer id;
    private String name;
    private String picUrl;
    private String briefDesc;

    public SongDetailArtist() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getBriefDesc() {
        return briefDesc;
    }

    public void setBriefDesc(String briefDesc) {
        this.briefDesc = briefDesc;
    }
}
