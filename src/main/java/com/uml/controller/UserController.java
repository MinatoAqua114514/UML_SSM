package com.uml.controller;

import com.uml.model.User;
import com.uml.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Objects;

/**
 * @author: JLChen
 * @since: 2024-09-27 13:28
 * @description:
 */


@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/show_login")
    public String showLogin() {
        return "login";
    }

    // 用户注册
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView register(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        // 获取信息
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        // 检查用户名或邮箱是否已存在 TODO 检验后返回出错信息
        if (userService.checkUsernameExists(username) != 0) {
            // 用户名已存在，返回注册页面重新提交
            mv.addObject("message", "用户名已存在，请重新提交。");
            mv.setViewName("register");
        } else if (userService.checkEmailExists(email) != 0) {
            // 邮箱已存在，返回注册页面重新提交
            mv.addObject("message", "邮箱已存在，请重新提交。");
            mv.setViewName("register");
        } else {
            // 注册成功，跳转登录页面
            mv.setViewName("login");
        }

        // 信息写入
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);
        // 将用户信息插入数据库
        userService.insertUser(user);

        // 返回注册成功的提示信息
        return mv;
    }

    //用于返回主页
    @RequestMapping(value = "/show_index", method = RequestMethod.GET)
    public String showIndex() {
        return "index";
    }

    // 用户登录
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(HttpServletRequest request, HttpSession session) {
        ModelAndView mv = new ModelAndView();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (userService.checkUsernameExists(username) != 0) {
            User user = userService.findUserByUsername(username);
            String truePassword = user.getPassword();
            if (Objects.equals(truePassword, password)) {
                Integer userId = user.getId();
                session.setAttribute("userId", userId);
                session.setAttribute("username", username);
                mv.setViewName("index");
            } else {
                mv.setViewName("login_error");
            }
        } else {
            mv.setViewName("login_error");
        }
        return mv;
    }

    // 用户信息详情页显示
    @RequestMapping(value = "profile/{userId}")
    public ModelAndView profile(@PathVariable Integer userId, HttpSession session) {
        ModelAndView mv = new ModelAndView("profile");
        // 未登录,用户信息为空，在前端提示先登录
        if (session.getAttribute("userId") == null) {
            mv = new ModelAndView("redirect:/login");
            return mv;
        }
        //检验用户是否存在
        if (userService.checkUserExists(userId) == 0){
            return new ModelAndView("404");
        }

        // 返回用户的所有信息
        User user = userService.findUserById(userId);
        mv.addObject("user", user);

        return mv;
    }

    // 用户修改编辑个人信息
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ModelAndView update(HttpServletRequest request, HttpSession session) {
        ModelAndView mv = new ModelAndView();

        // 未登录
        if (session.getAttribute("userId") == null) {
            mv.setViewName("login");
            return mv;
        }

        // 获取信息
        // TODO 前端显示更新表单时，需将用户原本的信息默认显示在表单中，方便统一提交
        Integer userId = (Integer) session.getAttribute("userId");
        String username = request.getParameter("username");
        String email = request.getParameter("email");

        User user = new User();
        user.setId(userId);
        user.setUsername(username);
        user.setEmail(email);


        userService.updateUser(user);

        mv.setViewName("profile");

        return mv;
    }
//    public ResponseEntity<String> login(@RequestBody User user) {
//        if (userService.checkUsernameExists(user.getUsername()) != 0) {
//            String truePassword = userService.findPasswordByUsername(user.getUsername());
//            if (Objects.equals(truePassword, user.getPassword())) {
//                return ResponseEntity.ok("登录成功");
//            } else {
//                return ResponseEntity.badRequest().body("密码错误");
//            }
//        } else {
//            return ResponseEntity.badRequest().body("用户名不存在");
//        }
//    }
}
