package com.interest.controller;

import com.interest.enums.Status;
import com.interest.impl.InterestGatherImpl;
import com.interest.impl.InterestGraph;
import com.interest.impl.UserServiceImpl;
import com.interest.impl.collectorImpl.FileCollector;
import com.interest.model.Input;
import com.interest.model.Interest;
import com.interest.model.User;
import com.interest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.util.List;

/**
 * Created by 431 on 2015/4/10.
 */
public class Comtroller {
//    @Autowired
//    private InterestGraph interestGraph = new InterestGraph();
//    @Autowired
//    private FileCollector fileCollector = new FileCollector();
//
//    private UserService userService = new UserServiceImpl();
//    public static void main(String[] args){
//        Comtroller comtroller = new Comtroller();
//        File file =new File("D:\\files\\musics2.kgl");
//        comtroller.fileCollector.setFile(file);
//        Input input = comtroller.fileCollector.collect();
//
//        comtroller.interestGraph.setGather(new InterestGatherImpl());
//        comtroller.interestGraph.setInput(input);
//        List<Interest> interests =  comtroller.interestGraph.gather();
//        comtroller.print(interests);
//
//        User user = comtroller.userService.getUserById(1);
//        Status status = comtroller.interestGraph.saveInterests(user, interests);
//        System.out.println(status);
//
//    }
//    private void print(List<Interest> list){
//        for(Interest interest:list){
//            System.out.println(interest.toString());
//        }
//    }
}
