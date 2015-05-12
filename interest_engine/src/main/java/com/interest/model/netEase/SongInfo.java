package com.interest.model.netEase;

/**
 * Created by 431 on 2015/5/11.
 */
public class SongInfo {
    private Song song;
    private Integer score;
    private Integer playCount;

    public SongInfo(){

    }

    public Song getSong() {
        return song;
    }

    public void setSong(Song song) {
        this.song = song;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getPlayCount() {
        return playCount;
    }

    public void setPlayCount(Integer playCount) {
        this.playCount = playCount;
    }
}
