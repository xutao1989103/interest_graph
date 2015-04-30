package com.interest.model;


import com.interest.enums.InterestType;

import java.util.List;

/**
 * Created by 431 on 2015/4/9.
 */
public class Music  extends Type{
    private String artist;
    private String musicType;
    private String title;
    private String album;
    private Integer playTimes;
    private Long duration;
    private List urls;

    public Music(String name){
        super(name);
    }

    public Music(UpPlaylistSong song){
        super(InterestType.MUSIC.getVaule(), song.getTitle(), song.getArtist());
        this.title = song.getTitle();
        this.artist = song.getArtist();
        this.album = song.getAlbum();
        this.times = song.getPlayTimes();
        this.playTimes = song.getPlayTimes();
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

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public Integer getPlayTimes() {
        return playTimes;
    }

    public void setPlayTimes(Integer playTimes) {
        this.playTimes = playTimes;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public List getUrls() {
        return urls;
    }

    public void setUrls(List urls) {
        this.urls = urls;
    }
}
