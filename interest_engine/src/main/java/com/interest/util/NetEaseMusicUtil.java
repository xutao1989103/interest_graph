package com.interest.util;

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
    private static final String USER_HOME_API = "http://music.163.com/user/home?";
    private static final String PLAYLIST_API = "http://music.163.com/api/playlist/detail?";
    private static final String USER_PLAYLIST_API = "http://music.163.com/api/user/playlist?uid=183089&wordwrap=7&offset=0&total=true&limit=36&csrf_token=c054d04647ec3492042e217d2bfea260";
    private static final String SEARCH_API = "http://music.163.com/api/search/pc";
    private static final String USER_SONGS_RECORD = "http://music.163.com/api/play/record?";
    private static final String SONG_DETAIL_API = "http://music.163.com/api/song/detail/?id=28377211&ids=%5B28377211%5D";
    private static final String AIRTIST_ALBUM_API = "http://music.163.com/api/artist/albums/166009?id=166009&offset=0&total=true&limit=5";

    public static final Integer SONG_CODE = 1;
    public static final Integer AUBUM_CODE = 10;
    public static final Integer SONGER_CODE = 100;
    public static final Integer PLAYLIT_CODE = 1000;
    public static final Integer USER_CODE = 1002;
    public static final Integer MV_CODE = 1004;
    public static final Integer LCRY_CODE = 1;
    public static final Integer RANK_TYPE_ALL_WEEK = -1;
    public static final Integer RANK_TYPE_ALL = 0;
    public static final Integer RANK_TYPE_WEEK = 1;

    private static final Integer DEFAULT_OFFSET = 0;
    private static final Integer DEFAULT_LIMIT = 20;
    public static final Integer MIN_SONG_NUM =10;

    static {
        initHttpClient();
    }

    private static void initHttpClient(){
        httpClientUtil.setHttpclient(new DefaultHttpClient());
        httpClientUtil.initClient();
    }

    public synchronized String getUserRecord(Integer userId, Integer type) throws Exception {
        String url = USER_SONGS_RECORD + "uid=" + userId + "&type=" +type;
        System.out.println(Thread.currentThread()+":"+ "user "+ userId+" in record url = "+ url);
        return httpClientUtil.getData(url, setHeader(userId));
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

    public static String getSearchResult(String keyword, Integer offset, Integer limit, Integer type) {
        String result = "";
        String url = SEARCH_API;
        List<NameValuePair> params = getNameValuePairs(keyword, offset, limit, type);
        Map headers = getHeader();
        try {
            result =  httpClientUtil.postData(url, params, headers);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
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

    public static void main(String[] args){
        try {
            NetEaseMusicUtil util = new NetEaseMusicUtil();
            println(util.getUserRecord(188304, NetEaseMusicUtil.RANK_TYPE_ALL));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static <T> void println(T t){
        System.out.println(t);
    }
}
