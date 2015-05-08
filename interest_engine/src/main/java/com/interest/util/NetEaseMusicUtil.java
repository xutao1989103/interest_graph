package com.interest.util;

import com.google.gson.Gson;
import org.apache.http.NameValuePair;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import java.util.*;

/**
 * Created by 431 on 2015/5/8.
 *
 *
 */
public class NetEaseMusicUtil {
    private static HttpClientUtil httpClientUtil = new HttpClientUtil();
    private static Gson gson = new Gson();
    private static final String USER_HOME_API = "http://music.163.com/user/home?";
    private static final String PLAYLIST_API = "http://music.163.com/api/playlist/detail?";
    private static final String USER_PLAYLIST_API = "http://music.163.com/api/user/playlist?uid=183089&wordwrap=7&offset=0&total=true&limit=36&csrf_token=c054d04647ec3492042e217d2bfea260";
    private static final String SEARCH_API = "http://music.163.com/api/search/pc";
    private static final String USER_SONGS_RECORD = "http://music.163.com/api/play/record?";
    private static final String SONG_DETAIL_API = "http://music.163.com/api/song/detail/?id=28377211&ids=%5B28377211%5D";
    private static final String AIRTIST_ALBUM_API = "http://music.163.com/api/artist/albums/166009?id=166009&offset=0&total=true&limit=5";
    private static final String ALBUM_DETAIL_API = "http://music.163.com/api/album/2457012?ext=true&id=2457012&offset=0&total=true&limit=10";
    private static final String LYIC_API = "http://music.163.com/api/song/lyric?os=pc&id=93920&lv=-1&kv=-1&tv=-1";
    private static final String MV_API = "http://music.163.com/api/mv/detail?id=319104&type=mp4";

    private static final Integer SONG_CODE = 1;
    private static final Integer AUBUM_CODE = 10;
    private static final Integer SONGER_CODE = 100;
    private static final Integer PLAYLIT_CODE = 1000;
    private static final Integer USER_CODE = 1002;
    private static final Integer MV_CODE = 1004;
    private static final Integer LCRY_CODE = 1;
    private static final Integer RANK_TYPE_ALL_WEEK = -1;
    private static final Integer RANK_TYPE_ALL = 0;
    private static final Integer RANK_TYPE_WEEK = 1;

    private static final Integer DEFAULT_OFFSET = 0;
    private static final Integer DEFAULT_LIMIT = 20;
    private static final Integer MIN_SONG_NUM =10;

    private static void initHttpClient(){
        httpClientUtil.setHttpclient(new DefaultHttpClient());
        httpClientUtil.initClient();
    }

    public static String getUserRecord(Integer userId, Integer type) throws Exception {
        String url = USER_SONGS_RECORD + "uid=" + userId + "&type=" +type;
        return httpClientUtil.getData(url,setHeader(userId));
    }

    public static String getUserHome(Integer userId) throws Exception {
        String url = USER_HOME_API + "id=" + userId;
        return httpClientUtil.getData(url);
    }

    public static String getPlayList(Integer playlistId, String updateTime) throws Exception {
        String url = PLAYLIST_API + "id=" + playlistId +"&updateTime="+ updateTime;
        return httpClientUtil.getData(url);
    }

    public static String getSearchResult(String keyword, Integer type) throws Exception {
        return getSearchResult(keyword, DEFAULT_OFFSET, DEFAULT_LIMIT, type);
    }

    public static String getSearchResult(String keyword, Integer offset, Integer limit, Integer type) throws Exception {
        String url = SEARCH_API;
        List<NameValuePair> params = getNameValuePairs(keyword, offset, limit, type);
        Map headers = getHeader();
        return httpClientUtil.postData(url, params, headers);
    }

    private static List<NameValuePair> getNameValuePairs(String keyword, Integer offset, Integer limit, Integer type) {
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("s",keyword));
        params.add(new BasicNameValuePair("offset", offset.toString()));
        params.add(new BasicNameValuePair("limit", limit.toString()));
        params.add(new BasicNameValuePair("type", type.toString()));
        return params;
    }

    private static Map setHeader(Integer id) {
        Map headers = new HashMap<String, String>();
        headers.put("Referer", "http://music.163.com/user/songs/rank?id=" + id);
        return headers;
    }
    private static Map getHeader() {
        Map headers = new HashMap<String, String>();
        headers.put("Cookie", "appver=1.5.0.75771");
        headers.put("Referer", "http://music.163.com/");
        return headers;
    }

    private static void convert(String content){
        if(content == null) return;
    }

    public static void main(String[] args){
        initHttpClient();
       try {
           print(getUsersAndRecords("x", 40));
       }catch (Exception e){
           e.printStackTrace();
       }
    }

    private static Map getUsersAndRecords(String keyword, Integer size) throws Exception {
        Map result = new HashMap<Integer, Map>();
        Integer userId;
        String users = getSearchResult(keyword, 0, size, NetEaseMusicUtil.USER_CODE);
        String[] strings = users.split("\"userId\":");
        for(int i = 1;i <strings.length;i++){
           try {
               userId = Integer.valueOf(strings[i].substring(0,strings[i].indexOf(',')));
               String s = getUserRecord(userId, NetEaseMusicUtil.RANK_TYPE_ALL);
               String[] songs = s.split("score\":");
               StringBuilder builder = new StringBuilder("user=").append(userId).append(": ");
               if(songs.length > MIN_SONG_NUM){
                   Map<String, Integer> songMap = new HashMap<String, Integer>();
                   for(int j = 1; j<songs.length; j++){
                       String score = songs[j].substring(0, songs[j].indexOf(','));
                       String song = songs[j].substring(songs[j].indexOf("name\":\"")+7, songs[j].indexOf("\",\"id"));
                       songMap.put(song,Integer.valueOf(score));
                   }
                   result.put(userId,songMap);
               }
           }catch (Exception e){
               e.printStackTrace();
               continue;
           }
        }
        return result;
    }


    public static <T> void print(T obj){
        System.out.print(obj);
    }

    public static <T> void println(T obj){
        System.out.println(obj);
    }
}
