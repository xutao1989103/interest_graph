package com.interest.model.netEase;

/**
 * Created by 431 on 2015/5/13.
 */
public class SongDetailResult {
    private Integer songCount;
    private SongDetail[] songs;

    public SongDetailResult(){

    }

    public Integer getSongCount() {
        return songCount;
    }

    public void setSongCount(Integer songCount) {
        this.songCount = songCount;
    }

    public SongDetail[] getSongs() {
        return songs;
    }

    public void setSongs(SongDetail[] songs) {
        this.songs = songs;
    }
}
