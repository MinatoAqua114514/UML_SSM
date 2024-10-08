package com.uml.service.impl;

import com.uml.dao.UserMapper;
import com.uml.model.User;
import com.uml.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author: JLChen
 * @since: 2024-09-26 12:11
 * @description:
 */


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public int checkUsernameExists(String username) {
        if (userMapper.checkUsernameExists(username) == 0) {
            return 0;
        } else {
            return 1;
        }
    }

    @Override
    public int checkEmailExists(String email) {
        if (userMapper.checkEmailExists(email) == 0) {
            return 0;
        } else {
            return 1;
        }
    }

    @Override
    public void insertUser(User user) {
        userMapper.insertUser(user);
    }

    @Override
    public User findUserByUsername(String username) {
        return userMapper.findUserByUsername(username);
    }

    /**
     * 事务管理 - 方法级别
     */
    @Override
    @Transactional
    public void updateUser(User user) {
        Integer userId = user.getId();
        String username = user.getUsername();
        String email = user.getEmail();
        userMapper.updateUser(userId, username, email);
    }

    @Override
    public Integer checkUserExists(Integer userId) {
        if (userMapper.checkUserExists(userId) == 0) {
            return 0;
        } else {
            return 1;
        }
    }

    @Override
    public User findUserById(Integer userId) {
        return userMapper.findUserById(userId);
    }
}
