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
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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

    @RequestMapping("/index")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("/welcome");
        return mv;
    }
    @RequestMapping("/loginPage")
    public ModelAndView getLoginPage(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("/login");
        return mv;
    }
    @RequestMapping("/login")
    public ModelAndView login(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("/welcome");
        HttpSession session  = request.getSession();
        User user = userService.getUserById(1);
        session.setAttribute("login_user",user);
        mv.addObject("user",user);
        return mv;
    }
}
