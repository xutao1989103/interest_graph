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
    String keyword;
    Integer size;
    Map userInputs;
    User currentUser;
    public WebCollector(){
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Map getUserInputs() {
        return userInputs;
    }

    public void setUserInputs(Map userInputs) {
        this.userInputs = userInputs;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
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
            music.setArtist(song.getAr()[0].getName());
        music.setTimes(info.getScore());
        music.setType(InterestType.MUSIC.getVaule());
        music.setAuthor(song.getAr()[0].getName());
        return music;
    }

    public void initCollector(){
        Map<User, Input> result = new HashMap<User, Input>();
        Gson gson = new Gson();
        String users = NetEaseMusicUtil.getSearchResult(keyword, 0, size, NetEaseMusicUtil.USER_CODE);
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
