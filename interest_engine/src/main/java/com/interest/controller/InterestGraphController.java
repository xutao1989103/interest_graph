package com.interest.controller;

import com.interest.enums.Status;
import com.interest.impl.InterestGraphImpl;
import com.interest.impl.collectorImpl.FileCollector;
import com.interest.model.Input;
import com.interest.model.InterestGraph;
import com.interest.model.InterestPoint;
import com.interest.model.User;
import com.interest.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
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
    @Resource(name= "interestGraphImpl")
    private InterestGraphImpl graph;

    @RequestMapping("/gather")
    public String index() throws UnsupportedEncodingException {
        File file1 =new File("D:\\files\\user1.kgl");
        File file2 =new File("D:\\files\\user2.kgl");
        File file3 =new File("D:\\files\\user3.kgl");
        File file4 =new File("D:\\files\\user4.kgl");
        File file5 =new File("D:\\files\\user5.kgl");
        User user1 = userService.getUserById(1);
        User user2 = userService.getUserById(2);
        User user3 = userService.getUserById(3);
        User user4 = userService.getUserById(4);
        User user5 = userService.getUserById(5);
        save(file1,user1);
        save(file2,user2);
        save(file3,user3);
        save(file4,user4);
        save(file5,user5);
        InterestGraph interestGraph = graph.buildGraph(user1);
        return "index";
    }

    @ResponseBody
    @RequestMapping(value = "/build/{userId}" , method = RequestMethod.GET)
    public int[][] build(@PathVariable String userId){
        User user = userService.getUserById(Integer.parseInt(userId));
        InterestGraph interestGraph = graph.buildGraph(user);
        return interestGraph.getEdges();
    }

    @ResponseBody
    @RequestMapping(value = "/apply/{userId}" , method = RequestMethod.GET)
    public User apply(@PathVariable String userId){
        User user = userService.getUserById(Integer.parseInt(userId));
        return user;
    }


    private void save(File file, User user){
        fileCollector.setFile(file);
        Input input = fileCollector.collect();
        graph.setInput(input);
        List<InterestPoint> interestPoints =  graph.gather();
        Status status = graph.saveInterests(user, interestPoints);
    }
}
