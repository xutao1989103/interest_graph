package com.interest.controller;

import com.interest.enums.Status;
import com.interest.impl.InterestGraphImpl;
import com.interest.impl.collectorImpl.FileCollector;
import com.interest.impl.collectorImpl.JsonCollector;
import com.interest.model.*;
import com.interest.service.UserService;
import com.interest.util.EhcacheUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by 431 on 2015/4/15.
 */
@Controller
@RequestMapping("/interest")
public class InterestGraphController {
    @Resource(name = "userService")
    private UserService userService ;
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
//        File file1 =new File("D:\\files\\user1.kgl");
//        File file2 =new File("D:\\files\\user2.kgl");
//        File file3 =new File("D:\\files\\user3.kgl");
//        File file4 =new File("D:\\files\\user4.kgl");
//        File file5 =new File("D:\\files\\user5.kgl");
//        User user1 = userService.getUserById(6);
//        User user2 = userService.getUserById(7);
//        User user3 = userService.getUserById(8);
//        User user4 = userService.getUserById(9);
//        User user5 = userService.getUserById(10);
//        save(file1,user1);
//        save(file2,user2);
//        save(file3,user3);
//        save(file4,user4);
//        save(file5,user5);
//        InterestGraph interestGraph = graph.buildGraph(user1);
        return "gatherInterestPoints";
    }

    @ResponseBody
    @RequestMapping(value = "/gather/{userId}", method = RequestMethod.POST)
    public Result gatherPlayList(@PathVariable Integer userId,HttpServletRequest request){
        Result result = new Result();
        String palyListString = request.getParameter("playList");
        jsonCollector.setJsonString(palyListString);
        Input input = jsonCollector.collect();
        Status status = save(input, userService.getUserById(userId));
        result.setInfo(status);
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
        result.setInfo(list);
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
        List<User> list = graph.getRecommendUsers(user,k);
        result.setInfo(list);
        return result;
    }

    private Status save(Input input, User user){
        graph.setInput(input);
        List<InterestPoint> interestPoints =  graph.gather();
        Status status = graph.saveInterests(user, interestPoints);
        return status;
    }


    private void save(File file, User user){
        fileCollector.setFile(file);
        Input input = fileCollector.collect();
        graph.setInput(input);
        List<InterestPoint> interestPoints =  graph.gather();
        Status status = graph.saveInterests(user, interestPoints);
    }
}
