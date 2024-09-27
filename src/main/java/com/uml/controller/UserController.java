package com.uml.controller;

import com.uml.model.Listing;
import com.uml.model.User;
import com.uml.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author: JLChen
 * @since: 2024-09-27 13:28
 * @description:
 */


@Controller
public class UserController {//i

    @Autowired
    private UserService userService;

    // 用户注册
    @RequestMapping(
            value = "/register",
            method = RequestMethod.POST,
            consumes = "application/json",
            produces = "application/json"
    )
    public ResponseEntity<String> register(@RequestBody User user) {
        // 检查用户名或邮箱是否已存在
        if (userService.checkUsernameExists(user.getUsername()) != 0) {
            return ResponseEntity.badRequest().body("用户名已存在");
        }
        if (userService.checkEmailExists(user.getEmail()) != 0) {
            return ResponseEntity.badRequest().body("邮箱已存在");
        }

        // 将用户信息插入数据库
        userService.insertUser(user);

        // 返回注册成功的提示信息
        return ResponseEntity.ok("注册成功");
    }

    // 用户登录
    @RequestMapping(
            value = "/login",
            method = RequestMethod.POST,
            consumes = "application/json",
            produces = "application/json"
    )
    public ResponseEntity<String> login(@RequestBody User user) {
        if (userService.checkUsernameExists(user.getUsername()) != 0) {
            String truePassword = userService.findPasswordByUsername(user.getUsername());
            if (Objects.equals(truePassword, user.getPassword())) {
                return ResponseEntity.ok("登录成功");
            } else {
                return ResponseEntity.badRequest().body("密码错误");
            }
        } else {
            return ResponseEntity.badRequest().body("用户名不存在");
        }
    }

    //用户搜索、筛选民宿
    @RequestMapping(
            value = "/search",
            method = RequestMethod.POST,
            consumes = "application/json",
            produces = "application/json"
    )
    public ResponseEntity<List<Listing>> search(@RequestBody Map<String, String> requestBody) {
        String key = requestBody.get("key");
        String district = requestBody.get("district");
        List<Listing> listings = userService.searchListingByKeyOrDistrict(key, district);
        if (listings!=null && !listings.isEmpty()) {
            return ResponseEntity.ok(listings);
        }
        return ResponseEntity.badRequest().body(null);
    }
}
