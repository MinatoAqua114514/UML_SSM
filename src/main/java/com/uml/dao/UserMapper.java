package com.uml.dao;

import com.uml.model.Listing;
import com.uml.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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
    String findPasswordByUsername(@Param("username") String username);

    //根据用户输入或筛选查询民宿
    List<Listing> searchListingByKeyOrDistrict(@Param("key") String key, @Param("district") String district);

    //根据民宿id查询其评分
    int searchMarkByListingId(@Param("id") Integer id);
}
