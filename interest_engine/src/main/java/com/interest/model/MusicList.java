package com.interest.model;

import com.interest.enums.InterestType;

import java.util.List;

/**
 * Created by 431 on 2015/4/9.
 */
public class MusicList extends Input {
    private List<Music> musics;

    public MusicList(){
        this.setType(InterestType.MUSIC);
    }

    public void setMusics(List<Music> musics){
        this.musics = musics;
    }

    @Override
    public InterestType getType() {
        return null;
    }

    @Override
    public List getList() {
        return musics;
    }
}
