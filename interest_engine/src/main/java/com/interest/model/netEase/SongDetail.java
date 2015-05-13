package com.interest.model.netEase;

/**
 * Created by 431 on 2015/5/13.
 */
public class SongDetail {
    private Integer id;
    private String name;
    private Integer popularity;
    private Integer playedNum;
    private Integer hearTime;
    private Integer starredNum;
    private Long duration;
    private String mp3Url;
    private SongDetailArtist[] artists;
    private SongDetailAlbum album;

    public SongDetail() {
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

    public Integer getPopularity() {
        return popularity;
    }

    public void setPopularity(Integer popularity) {
        this.popularity = popularity;
    }

    public Integer getPlayedNum() {
        return playedNum;
    }

    public void setPlayedNum(Integer playedNum) {
        this.playedNum = playedNum;
    }

    public Integer getHearTime() {
        return hearTime;
    }

    public void setHearTime(Integer hearTime) {
        this.hearTime = hearTime;
    }

    public Integer getStarredNum() {
        return starredNum;
    }

    public void setStarredNum(Integer starredNum) {
        this.starredNum = starredNum;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public String getMp3Url() {
        return mp3Url;
    }

    public void setMp3Url(String mp3Url) {
        this.mp3Url = mp3Url;
    }

    public SongDetailArtist[] getArtists() {
        return artists;
    }

    public void setArtists(SongDetailArtist[] artists) {
        this.artists = artists;
    }

    public SongDetailAlbum getAlbum() {
        return album;
    }

    public void setAlbum(SongDetailAlbum album) {
        this.album = album;
    }
}
