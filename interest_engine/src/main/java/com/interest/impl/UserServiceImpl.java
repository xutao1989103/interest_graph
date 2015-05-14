package com.interest.impl;

import com.interest.dao.UserDAO;
import com.interest.enums.Status;
import com.interest.model.Input;
import com.interest.model.InterestPoint;
import com.interest.model.User;
import com.interest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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

    @Override
    public User getUserByNameAndPassword(String name, String password) {
        Map params = new HashMap<String, String>();
        params.put("name",name);
        params.put("password",password);
        return userDAO.getUserByNameAndPassword(params);
    }

    public Status saveUser(User user){
        if(getUserById(user.getId())==null){
            insertUser(user);
        }
        return Status.SUCCESS;
    }

    public synchronized Status save(InterestGraphImpl graph, Input input, User user){
        System.out.println(Thread.currentThread()+":"+ "user "+ user.getId()+" "+user.getName()+ " begin to save " + input.getList().size() + " items.");
        saveUser(user);
        graph.setInput(input);
        List<InterestPoint> interestPoints =  graph.gather();
        Status status = graph.saveInterests(user, interestPoints);
        System.out.println(Thread.currentThread()+":"+ "user "+ user.getId()+" "+user.getName()+ " have saved " + input.getList().size() + " items.");
        return status;
    }

}
