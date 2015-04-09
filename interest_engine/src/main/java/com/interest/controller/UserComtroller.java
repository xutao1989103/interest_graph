package com.interest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by 431 on 2015/4/9.
 */
@Controller
@RequestMapping("/use")
public class UserComtroller {
    @RequestMapping("index")
    public String index(){
        return "index";
    }
}
