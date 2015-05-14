package com.interest.controller;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.interest.enums.Status;
import com.interest.impl.InterestGraphImpl;
import com.interest.impl.UserServiceImpl;
import com.interest.impl.collectorImpl.FileCollector;
import com.interest.impl.collectorImpl.JsonCollector;
import com.interest.impl.collectorImpl.WebCollector;
import com.interest.model.*;
import com.interest.model.netEase.SearchResult;
import com.interest.model.netEase.Userprofile;
import com.interest.service.UserService;
import com.interest.util.EhcacheUtil;
import com.interest.util.NetEaseMusicUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by 431 on 2015/4/15.
 */
@Controller
@RequestMapping("/interest")
public class InterestGraphController {
    @Resource(name = "userService")
    private UserServiceImpl userService ;
    @Resource(name = "fileCollector")
    private FileCollector fileCollector;
    @Resource(name = "jsonCollector")
    private JsonCollector jsonCollector;
    @Resource(name= "interestGraphImpl")
    private InterestGraphImpl graph;

    private static final String CACHE_NAME = "interestGraphCache";
    private static Integer k = Integer.MAX_VALUE;

    @RequestMapping("/gather")
    public String index() throws UnsupportedEncodingException {
        return "gatherInterestPoints";
    }

    @ResponseBody
    @RequestMapping(value = "/gather/{userId}", method = RequestMethod.POST)
    public Result gatherPlayList(@PathVariable Integer userId,HttpServletRequest request){
        Result result = new Result();
        String palyListString = request.getParameter("playList");
        jsonCollector.setJsonString(palyListString);
        Input input = jsonCollector.collect();
        Status status = userService.save(graph, input, userService.getUserById(userId));
        result.setInfo(status);
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/gather/net", method = RequestMethod.GET)
    public Result gatherPlayListFromNetEase(HttpServletRequest request){
        Result result = new Result();
        String keyword = request.getParameter("keyword");
        Integer start =  Integer.valueOf(request.getParameter("start"));
        Integer size = Integer.valueOf(request.getParameter("size"));
        WebCollector webCollector = new WebCollector(userService, graph,keyword,start,size);
        Userprofile[] userprofiles = webCollector.getUserprofiles();
        if(userprofiles!=null && userprofiles.length>0){
            for(Userprofile userprofile : userprofiles){
                new Thread(webCollector).start();
            }
        }
        result.setInfo("add "+userprofiles.length+ " users and "+ 0 +"interests" );
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/build/{userId}" , method = RequestMethod.GET)
    public Result build(@PathVariable String userId,HttpServletRequest request){
        Result result = new Result();
        User user = (User)userService.getUserById(Integer.valueOf(userId));
        if(user==null){
            result.setInfo("cannot find user");
            return result;
        }
        InterestGraph interestGraph = getInterestGraph(user);
        result.setInfo(interestGraph);
        return result;
    }

    private InterestGraph getInterestGraph(User user){
        InterestGraph interestGraph =null;
        EhcacheUtil ehcacheUtil = EhcacheUtil.getInstance();
        Object obj = ehcacheUtil.getObjectCached(user.toString(), CACHE_NAME);
//        if(obj!=null){
//            interestGraph = (InterestGraph)obj;
//        }else {
//            interestGraph = graph.buildGraph(user);
//            ehcacheUtil.put(user.toString(),CACHE_NAME,interestGraph);
//        }
        interestGraph = graph.buildGraph(user);

        return interestGraph;
    }

    @ResponseBody
    @RequestMapping(value = "/apply/{userId}/items" , method = RequestMethod.GET)
    public Result getInterests(@PathVariable String userId, HttpServletRequest request){
        Result result = new Result();
        User user = userService.getUserById(Integer.parseInt(userId));
        if(user==null){
            result.setInfo("cannot find user");
            return result;
        }
        List<InterestPoint> list = graph.getRecommendInterests(user, k);
        List<Music> musics = getMusicListFromInterests(list);
        MusicList musicList = new MusicList();
        musicList.setMusics(musics);
        result.setInfo(musicList);
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/apply/{userId}/users" , method = RequestMethod.GET)
    public Result getUsers(@PathVariable String userId, HttpServletRequest request){
        Result result = new Result();
        User user = userService.getUserById(Integer.parseInt(userId));
        if(user==null){
            result.setInfo("cannot find user");
            return result;
        }
        List<User> list = graph.getRecommendUsers(user, k);
        result.setInfo(list);
        return result;
    }

    private List<Music> getMusicListFromInterests(List<InterestPoint> points){
        List<Music> musics = Lists.transform(points, new Function<InterestPoint, Music>() {
            @Override
            public Music apply(InterestPoint interestPoint) {
                return (Music) interestPoint.getType();
            }
        });
        return musics;
    }
    private Userprofile[] getUserProfiles(String keyword, Integer start, Integer size){
        Gson gson = new Gson();
        String users = NetEaseMusicUtil.getSearchResult(keyword, start, size, NetEaseMusicUtil.USER_CODE);
        SearchResult searchResult = gson.fromJson(users, SearchResult.class);
        if(searchResult == null || searchResult.getResult()==null || searchResult.getResult().getUserprofiles().length==0) return null;
        return searchResult.getResult().getUserprofiles();
    }
}
