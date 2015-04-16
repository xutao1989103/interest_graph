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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.Response;
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
    public ModelAndView index(HttpServletRequest request) {
        HttpSession session  = request.getSession();
        User user = (User)session.getAttribute("login_user");
        ModelAndView mv = new ModelAndView("/welcome");
        mv.addObject("user",user);
        return mv;
    }

    @RequestMapping("/login")
    public ModelAndView login(HttpServletRequest request,HttpServletResponse response) {
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        ModelAndView mv = new ModelAndView("/login");
        User user = null;
        if(name==null || password == null){
            return mv;
        }else {
            user= userService.getUserByNameAndPassword(name, password);
            HttpSession session  = request.getSession();
            session.setAttribute("login_user", user);
            mv.addObject("user", user);
            if(user!=null){
                return new ModelAndView("redirect:/use/index");
            }else {
                return mv;
            }
        }
    }
}
