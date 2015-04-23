package com.interest.impl.collectorImpl;

import com.google.gson.Gson;
import com.interest.model.Input;
import com.interest.model.Music;
import com.interest.model.MusicList;
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
            Music music = new Music(song.getTitle());
            music.setSingner(song.getArtist());
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

    class UpPlaylist {
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


    class UpPlaylistSong {
        private String artist;
        private String title;
        private String album;
        private Integer playTimes;
        private Long duration;
        private boolean like;
        private boolean dislike;

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

        public boolean isLike() {
            return like;
        }

        public void setLike(boolean like) {
            this.like = like;
        }

        public boolean isDislike() {
            return dislike;
        }

        public void setDislike(boolean dislike) {
            this.dislike = dislike;
        }
    }
}
