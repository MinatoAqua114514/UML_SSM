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
}
