package com.interest.impl.collectorImpl;

import com.google.gson.Gson;
import com.interest.enums.InterestType;
import com.interest.enums.Status;
import com.interest.impl.InterestGraphImpl;
import com.interest.impl.UserServiceImpl;
import com.interest.model.*;
import com.interest.model.netEase.*;
import com.interest.service.Collector;
import com.interest.service.UserService;
import com.interest.util.NetEaseMusicUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 431 on 2015/5/11.
 */
public class WebCollector implements Collector,Runnable {
    private Userprofile runProfile;
    private Integer interestNum;
    Map userInputs;
    User currentUser;
    private Gson gson = new Gson();
    private InterestGraphImpl graph;
    private UserServiceImpl userService ;
    private Userprofile[] userprofiles;
    private int index = 0;

    public WebCollector(UserServiceImpl userService,InterestGraphImpl graph,String keyword, Integer start, Integer size) {
        userprofiles = getUserProfiles(keyword, start, size);
        this.userService = userService;
        this.graph = graph;
        this.interestNum = 0;
    }

    public Userprofile[] getUserprofiles() {
        return userprofiles;
    }

    private synchronized void setRunProfile(){
        runProfile = userprofiles[index];
        index++;
    }

    public synchronized void addInterestNum(Integer amount){
        this.interestNum+=amount;
    }

    private synchronized User extractUser(Userprofile up){
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

    public Status collectAndSaveCurrentUser(){
        try {
            User user = getUser();
            Input input = collectInput(user);
            if(input.getList()!=null){
                addInterestNum(input.getList().size());
                userService.save(graph, input, user);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return Status.SUCCESS;
    }

    private synchronized User getUser() {
        setRunProfile();
        System.out.println(index+","+Thread.currentThread() + ":" + "user " + runProfile.getUserId() + " " + runProfile.getNickname() + " began to collect ");
        return extractUser(runProfile);
    }

    public synchronized Input collectInput(User user){
        MusicList musicList = new MusicList();
        try {
            List<Music> musics = new ArrayList<Music>();
            NetEaseMusicUtil util = new NetEaseMusicUtil();
            String s = util.getUserRecord(user.getId(), NetEaseMusicUtil.RANK_TYPE_ALL);
            SongResult songResult = gson.fromJson(s, SongResult.class);
            SongInfo[] songInfos = songResult.getAllData();
            if(songInfos.length > NetEaseMusicUtil.MIN_SONG_NUM){
                for(SongInfo info: songInfos){
                    musics.add(extractMusic(info));
                }
                musicList.setMusics(musics);
                return musicList;
            }
            return new MusicList();
        }catch (Exception e){
            e.printStackTrace();
        }
        return new MusicList();
    }

    private Userprofile[] getUserProfiles(String keyword, Integer start, Integer size){
        Gson gson = new Gson();
        String users = NetEaseMusicUtil.getSearchResult(keyword, start, size, NetEaseMusicUtil.USER_CODE);
        SearchResult searchResult = gson.fromJson(users, SearchResult.class);
        if(searchResult == null || searchResult.getResult()==null || searchResult.getResult().getUserprofiles().length==0) return null;
        return searchResult.getResult().getUserprofiles();
    }

    @Override
    public Input collect() {
        return getInputByUser(currentUser);
    }

    private Input getInputByUser(User user){
        return (Input)userInputs.get(user);
    }

    @Override
    public void run() {
        collectAndSaveCurrentUser();
    }
}
