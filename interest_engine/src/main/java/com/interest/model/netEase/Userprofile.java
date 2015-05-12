package com.interest.model.netEase;

/**
 * Created by 431 on 2015/5/11.
 */
public class Userprofile {
    private String signature;
    private String description;
    private Integer userId;
    private String nickname;
    private Integer gender;
    private Integer playlistCount;

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Integer getPlaylistCount() {
        return playlistCount;
    }

    public void setPlaylistCount(Integer playlistCount) {
        this.playlistCount = playlistCount;
    }

    public Userprofile(){

    }
}
