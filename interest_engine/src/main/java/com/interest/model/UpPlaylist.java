package com.interest.model;

import java.util.List;

/**
 * Created by 431 on 2015/4/25.
 */
public class UpPlaylist {
    private long id;
    private String name;
    private List<UpPlaylistSong> songs;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<UpPlaylistSong> getSongs() {
        return songs;
    }

    public void setSongs(List<UpPlaylistSong> songs) {
        this.songs = songs;
    }
}
