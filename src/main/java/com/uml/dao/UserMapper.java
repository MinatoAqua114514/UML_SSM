package com.uml.dao;

import com.uml.model.User;
import org.apache.ibatis.annotations.Param;

/**
 * @author: JLChen
 * @since: 2024-09-25 13:19
 * @description:
 */

public interface UserMapper {
    // 检查用户名是否已存在
    int checkUsernameExists(@Param("username") String username);

    // 检查邮箱是否已存在
    int checkEmailExists(@Param("email") String email);

    // 注册新用户
    void insertUser(User user);

    // 用户名登录匹配密码
    User findUserByUsername(@Param("username") String username);

    // 更新用户的个人信息
    void updateUser(@Param("id") Integer userId, @Param("username") String username, @Param("email") String email);

    // 检查用户是否已存在
    Integer checkUserExists(@Param("id") Integer userId);

    // 用户ID获取用户信息
    User findUserById(@Param("id") Integer userId);
}
