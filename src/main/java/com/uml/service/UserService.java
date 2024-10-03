package com.uml.service;

import com.uml.model.User;

public interface UserService {
    // 检查用户名是否已存在
    int checkUsernameExists(String username);

    // 检查邮箱是否已存在
    int checkEmailExists(String email);

    // 注册新用户
    void insertUser(User user);

    // 用户名登录匹配密码
    User findUserByUsername(String username);

    // 更新用户的个人信息
    void updateUser(User user);

    // 检查用户是否已存在
    Integer checkUserExists(Integer userId);

    // 用户ID获取用户信息
    User findUserById(Integer userId);
}
