package com.interest.controller;


import com.interest.model.User;
import com.interest.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
