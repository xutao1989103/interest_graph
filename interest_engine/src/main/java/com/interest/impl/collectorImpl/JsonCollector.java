package com.interest.impl.collectorImpl;

import com.google.gson.Gson;
import com.interest.model.*;
import com.interest.service.Collector;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 431 on 2015/4/23.
 */
@Service("jsonCollector")
public class JsonCollector implements Collector {
    String jsonString;
    Input input;

    public String getJsonString() {
        return jsonString;
    }

    public void setJsonString(String jsonString) {
        this.jsonString = jsonString;
    }

    public Input getInput() {
        return input;
    }

    public void setInput(Input input) {
        this.input = input;
    }

    @Override
    public Input collect() {
        UpPlaylist upPlaylist = jsonToObj(jsonString);
        List<UpPlaylistSong> list = upPlaylist.getSongs();
        MusicList musicList = new MusicList();
        List<Music> musics = new ArrayList<Music>();
        for(UpPlaylistSong song:list){
            Music music = new Music(song);
            musics.add(music);
        }
        musicList.setMusics(musics);
        return musicList;
    }

    private UpPlaylist jsonToObj(String string){
        Gson gson = new Gson();
        UpPlaylist upPlaylist = gson.fromJson(string, UpPlaylist.class);
        return upPlaylist==null? new UpPlaylist():upPlaylist;
    }

}
