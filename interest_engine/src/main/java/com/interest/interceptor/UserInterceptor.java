package com.interest.interceptor;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by 431 on 2015/4/16.
 */
public class UserInterceptor implements HandlerInterceptor {
    public final static String LOGIN_USER = "login_user";
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        //请求的路径
        String contextPath=request.getContextPath();
        String  url=request.getServletPath().toString();
        HttpSession session = request.getSession();
        String user = (String) session.getAttribute(LOGIN_USER);
//        if (StringUtils.isEmpty(user)) {
//            response.sendRedirect("/use/index");
//            return false;
//        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
