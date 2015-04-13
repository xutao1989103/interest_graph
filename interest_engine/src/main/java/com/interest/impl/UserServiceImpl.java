package com.interest.impl;

import com.interest.dao.UserDAO;
import com.interest.model.User;
import com.interest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Created by 431 on 2015/4/9.
 */
@Service("userService")
public class UserServiceImpl implements UserService{
    @Autowired
    private UserDAO userDAO;

    @Override
    public int insertUser(User user) {
        // TODO Auto-generated method stub
        return userDAO.insertUser(user);
    }

    @Override
    public User getUserById(Integer id) {
        return userDAO.getUserById(id);
    }
}
