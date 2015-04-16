package com.interest.dao;

import com.interest.model.User;

import java.util.Map;

/**
 * Created by 431 on 2015/4/9.
 */
public interface UserDAO {
    /**
     * 添加新用户
     * @param user
     * @return
     */
    int insertUser(User user);
    User getUserById(Integer id);
    User getUserByNameAndPassword(Map param);
}
