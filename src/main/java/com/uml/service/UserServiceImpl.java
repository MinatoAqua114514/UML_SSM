package com.uml.service;

import com.uml.dao.UserMapper;
import com.uml.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public int checkUsernameExists(String username) {
        return 0;
    }

    @Override
    public int checkEmailExists(String email) {
        return 0;
    }

    @Override
    public void insertUser(User user) {
    }
}
