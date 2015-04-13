package com.interest.controller;

import com.interest.enums.Status;
import com.interest.impl.InterestGatherImpl;
import com.interest.impl.InterestGraph;
import com.interest.impl.UserServiceImpl;
import com.interest.impl.collectorImpl.FileCollector;
import com.interest.model.Input;
import com.interest.model.Interest;
import com.interest.model.User;
import com.interest.service.InterestGather;
import com.interest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.io.File;
import java.util.List;

/**
 * Created by 431 on 2015/4/9.
 */
@Controller
@RequestMapping("/use")
public class UserComtroller {

    @Resource(name = "userService")
    private UserService userService ;
    @Resource(name = "fileCollector")
    private FileCollector fileCollector;
    @Resource(name= "interestGatherImpl")
    private InterestGather gather;


    @RequestMapping("index")
    public String index(){
        File file =new File("D:\\files\\musics2.kgl");
        fileCollector.setFile(file);
        Input input = fileCollector.collect();

        List<Interest> interests =  gather.gather(input);
        print(interests);

        User user = userService.getUserById(1);
        System.out.println(user);

        interests.clear();
        Interest interest = new Interest.InterestBuilder("篮球").withIsLeaf(true).build();
        Interest interest1 = new Interest.InterestBuilder("足球").withIsLeaf(true).build();
        interests.add(interest);
        interests.add(interest1);
        gather.save(user, interests);
        //Status status = gather.save(user, interests);
        return "index";
    }

    private void print(List<Interest> list){
        for(Interest interest:list){
            System.out.println(interest.toString());
        }
    }
}
