package com.interest.model;


import com.interest.enums.InterestType;

import java.util.List;

/**
 * Created by 431 on 2015/4/9.
 */
public class Music  extends Type{
    private String musicType;
    private String album;
    private Long duration;
    private String url;
    private String picUrl;

    public Music(){
        super();
    }

    public Music(String name){
        super(name);
    }

    public Music(UpPlaylistSong song){
        super(InterestType.MUSIC.getVaule(), song.getTitle(), song.getArtist());
        this.album = song.getAlbum();
        this.times = song.getPlayTimes();
        this.duration = song.getDuration();
        this.like = song.isLike();
        this.dislike = song.isDislike();
    }

    public String getMusicType() {
        return musicType;
    }

    public void setMusicType(String musicType) {
        this.musicType = musicType;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }
}
