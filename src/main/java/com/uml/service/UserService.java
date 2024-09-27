package com.uml.service;

import com.uml.model.Listing;
import com.uml.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserService {
    // 检查用户名是否已存在
    int checkUsernameExists(String username);

    // 检查邮箱是否已存在
    int checkEmailExists(String email);

    // 注册新用户
    void insertUser(User user);

    // 用户名登录匹配密码
    String findPasswordByUsername(String username);

    //根据用户输入或筛选查询民宿
    List<Listing> searchListingByKeyOrDistrict(String key, String district);
}
