package com.interest.model;

import java.util.List;

/**
 * Created by 431 on 2015/4/9.
 */
public class MusicList implements Input {
    private List<Music> musics;
    @Override
    public List toList() {
        return musics;
    }
}
