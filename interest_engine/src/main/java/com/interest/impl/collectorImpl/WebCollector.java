package com.interest.impl.collectorImpl;

import com.google.gson.Gson;
import com.interest.enums.InterestType;
import com.interest.model.Input;
import com.interest.model.Music;
import com.interest.model.MusicList;
import com.interest.model.User;
import com.interest.model.netEase.*;
import com.interest.service.Collector;
import com.interest.util.NetEaseMusicUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 431 on 2015/5/11.
 */
@Service("webCollector")
public class WebCollector implements Collector {
    Map userInputs;
    User currentUser;
    public WebCollector(){
    }

    public Map getUserInputs() {
        return userInputs;
    }

    private User extractUser(Userprofile up){
        User user = new User();
        user.setId(up.getUserId());
        user.setName(up.getNickname());
        user.setPassword("123456");
        return user;
    }

    private Music extractMusic(SongInfo info){
        Song song = info.getSong();
        Music music = new Music(song.getName());
        if(song.getAr()!=null&&song.getAr().length!=0)
            music.setAuthor(song.getAr()[0].getName());
        music.setTimes(info.getScore());
        music.setType(InterestType.MUSIC.getVaule());
        music.setAlbum(song.getAl().getName());
        enrichMusic(music);
        return music;
    }

    public void enrichMusic(Music music){
        Gson gson = new Gson();
        String detail = NetEaseMusicUtil.getSearchResult(music.getName()+" "+ music.getAuthor(), 0, 1, NetEaseMusicUtil.SONG_CODE);
        SongDetailReachResult result = gson.fromJson(detail, SongDetailReachResult.class);
    if(result!=null && result.getResult()!=null && result.getResult().getSongs()!=null && result.getResult().getSongs().length>0){
            SongDetail song = result.getResult().getSongs()[0];
            music.setUrl(song.getMp3Url());
            music.setPicUrl(song.getAlbum().getPicUrl());
            music.setDuration(song.getDuration());
            music.setTags(song.getName() + "," + song.getAlbum().getName() + "," + song.getAlbum().getTags());
        }
    }
    public void initCollector(String keyword, Integer start, Integer size){
        Map<User, Input> result = new HashMap<User, Input>();
        Gson gson = new Gson();
        String users = NetEaseMusicUtil.getSearchResult(keyword, start, size, NetEaseMusicUtil.USER_CODE);
        SearchResult searchResult = gson.fromJson(users, SearchResult.class);
        if(searchResult == null || searchResult.getResult()==null || searchResult.getResult().getUserprofiles().length==0) return;
        Userprofile[] userprofiles = searchResult.getResult().getUserprofiles();
        for(int i = 0;i <userprofiles.length;i++){
            try {
                User user = extractUser(userprofiles[i]);
                MusicList musicList = new MusicList();
                List<Music> musics = new ArrayList<Music>();
                String s = NetEaseMusicUtil.getUserRecord(user.getId(), NetEaseMusicUtil.RANK_TYPE_ALL);
                SongResult songResult = gson.fromJson(s, SongResult.class);
                SongInfo[] songInfos = songResult.getAllData();
                if(songInfos.length > NetEaseMusicUtil.MIN_SONG_NUM){
                    for(SongInfo info: songInfos){
                        musics.add(extractMusic(info));
                    }
                    musicList.setMusics(musics);
                    result.put(user,musicList);
                }
            }catch (Exception e){
                e.printStackTrace();
                continue;
            }
        }
        this.userInputs = result;
    }

    @Override
    public Input collect() {
        return getInputByUser(currentUser);
    }

    private Input getInputByUser(User user){
        return (Input)userInputs.get(user);
    }
}
