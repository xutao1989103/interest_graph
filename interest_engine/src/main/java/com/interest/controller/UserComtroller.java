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
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.io.File;
import java.io.UnsupportedEncodingException;
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
    @Resource(name= "interestGraphImpl")
    private InterestGraphImpl graph;


    @RequestMapping("index")
    public String index() {
        return "index";
    }
}
