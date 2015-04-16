package com.interest.impl;

import com.interest.dao.UserDAO;
import com.interest.model.User;
import com.interest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by 431 on 2015/4/9.
 */
@Service("userService")
public class UserServiceImpl implements UserService{
    @Autowired
    private UserDAO userDAO;


    public int insertUser(User user) {
        // TODO Auto-generated method stub
        return userDAO.insertUser(user);
    }


    public User getUserById(Integer id) {
        return userDAO.getUserById(id);
    }


    public User getUserByNameAndPassword(String name, String password) {
        Map params = new HashMap<String, String>();
        params.put("name",name);
        params.put("password",password);
        return userDAO.getUserByNameAndPassword(params);
    }
}
