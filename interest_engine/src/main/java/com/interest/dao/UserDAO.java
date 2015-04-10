package com.interest.dao;

import com.interest.model.User;

/**
 * Created by 431 on 2015/4/9.
 */
public interface UserDAO {
    /**
     * 添加新用户
     * @param user
     * @return
     */
    public int insertUser(User user);
    public User getUserById(Integer id);
}
