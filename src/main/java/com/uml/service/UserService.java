package com.uml.service;

import com.uml.model.User;

public interface UserService {
    // 检查用户名是否已存在
    int checkUsernameExists(String username);

    // 检查邮箱是否已存在
    int checkEmailExists(String email);

    // 注册新用户
    void insertUser(User user);
}
