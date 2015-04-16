package com.interest.service;

import com.interest.model.User;

/**
 * Created by 431 on 2015/4/9.
 */
public interface UserService {
    int insertUser(User user);

    User getUserById(Integer id);

    User getUserByNameAndPassword(String name, String password);
}
