package com.uml.controller;

import com.uml.model.Book;
import com.uml.model.User;
import com.uml.service.BookService;
import com.uml.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
//  import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    @Autowired
    private BookService bookService;

    // 常量定义
    private static final String SESSION_USER_ID = "userId";
    //密码加密    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    //添加日志    private static final Logger logger = LoggerFactory.getLogger(UserController.class);



    /**
     * 用户注册功能(2024/10/04)
     * 1.校验用户提交的username,email,password格式
     * 2.检查用户名和邮箱是否已注册过
     * 3.将用户的信息插入存储到数据库中
     * 4.TODO 对用户的密码进行加密存储
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView register(
            @RequestParam("username") String username,
            @RequestParam("email") String email,
            @RequestParam("password") String password) {

        ModelAndView mv = new ModelAndView();

        // 输入格式校验
        if (isValidUsername(username)) {
            mv.addObject("error", "用户名格式不正确。");
            mv.setViewName("register");
            return mv;
        }

        if (isValidEmail(email)) {
            mv.addObject("error", "邮箱格式不正确。");
            mv.setViewName("register");
            return mv;
        }

        if (isValidPassword(password)) {
            mv.addObject("error", "密码格式不正确。");
            mv.setViewName("register");
            return mv;
        }

        // 检查用户名或邮箱是否已存在
        if (userService.checkUsernameExists(username) > 0) {
            mv.addObject("error", "用户名已存在，请重新提交。");
            mv.setViewName("register");
            return mv;
        }

        if (userService.checkEmailExists(email) > 0) {
            mv.addObject("error", "邮箱已存在，请重新提交。");
            mv.setViewName("register");
            return mv;
        }

        // 确保用户名和邮箱都不存在后，再将信息写入数据库
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);
        // 密码加密 user.setPassword(passwordEncoder.encode(password));

        // 将用户信息插入数据库
        try {
            userService.insertUser(user);
        } catch (DataAccessException dae) {
            // 数据库操作异常 logger.error("数据库访问异常: ", dae);
            mv.addObject("error", "注册失败，请稍后再试。");
            mv.setViewName("register");
            return mv;
        }

        // 注册成功，返回注册成功的提示信息，跳转登录页面
        mv.addObject("success", "注册成功！");
        mv.setViewName("register");
        return mv;
    }



    /**
     * 用户登录(2024/10/04)
     * 1.校验用户提交的username,password格式
     * 2.检查登录的用户名是否存在
     * 3.检查登录密码是否正确
     * 4.TODO 使用 BCrypt 进行密码匹配
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            HttpSession session) {

        ModelAndView mv = new ModelAndView();

        // 输入格式校验
        if (isValidUsername(username)) {
            mv.addObject("error", "用户名格式不正确。");
            mv.setViewName("login");
            return mv;
        }

        if (isValidPassword(password)) {
            mv.addObject("error", "密码格式不正确。");
            mv.setViewName("login");
            return mv;
        }

        try {
            if (userService.checkUsernameExists(username) > 0) {
                User user = userService.findUserByUsername(username);
                String truePassword = user.getPassword();

                // 使用 BCrypt 进行密码匹配 passwordEncoder.matches(password, storedPassword)
                if (Objects.equals(truePassword, password)) {
                    Integer userId = user.getId();
                    session.setAttribute("userId", userId);
                    session.setAttribute("username", username);
                    mv.addObject("success", "登录成功");
                    mv.setViewName("index");
                } else {
                    // 密码错误
                    mv.addObject("error", "密码错误");
                    mv.setViewName("login");
                }
            } else {
                // 用户不存在
                mv.addObject("error", "用户不存在");
                mv.setViewName("login");
            }
            return mv;
        } catch (DataAccessException dae) {
            // 数据库操作异常 logger.error("数据库访问异常: ", dae);
            mv.addObject("error", "登录失败，请稍后再试。");
            mv.setViewName("login");
            return mv;
        }
    }



    /**
     *用户退出登录(2024/10/04)
     * 1.清除所有会话数据
     * 2.重定向返回主页
     */
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public String logout(HttpSession session) {
        // 清除所有会话数据
        session.invalidate();

        // 日志记录
        // logger.info("User logged out successfully");

        // 使用重定向方式返回主页，避免刷新页面时重复提交
        return "redirect:/index";
    }



    /**
     * 用户信息详情页信息显示(2024/10/04)
     * 1.检查用户是否已经登录
     * 2.获取对应用户的所有个人详细信息
     * 3.将信息绑定并返回至视图"profile"
     */
    @GetMapping("/profile")
    public ModelAndView profile(HttpSession session) {
        ModelAndView mv = checkUser(session);
        if (mv != null) {
            return mv;
        }

        Integer userId = getCurrentUserId(session);
        User user = userService.findUserById(userId);
        mv = new ModelAndView("profile");
        mv.addObject("user", user);
        return mv;
    }



    /**
     * 编辑修改用户个人信息(2024/10/04)
     * 1.检查用户是否已登录并存在
     * 2.校验用户提交的username,email格式
     * 3.更新用户数据库中存储的信息
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ModelAndView update(HttpSession session,
                               @RequestParam(value = "username", required = false) String username,
                               @RequestParam(value = "email", required = false) String email) {

        // 检查用户是否已登录并存在
        ModelAndView checkUserMv = checkUser(session);
        if (checkUserMv != null) {
            return checkUserMv;
        }

        Integer userId = getCurrentUserId(session);
        // 这里 userId 不可能为 null，因为 checkUser 已经验证过
        // 但为了保险起见，仍然进行一次检查
        if (userId == null) {
            return new ModelAndView("redirect:/login");
        }

        // 数据验证
        if (isValidUsername(username)) {
            ModelAndView mv = new ModelAndView("profile");
            mv.addObject("error", "用户名必须为5-20位的字母或数字");
            return mv;
        }

        if (isValidEmail(email)) {
            ModelAndView mv = new ModelAndView("profile");
            mv.addObject("error", "邮箱格式不正确");
            return mv;
        }

        // TODO 前端显示更新表单时，需将用户原本的信息默认显示在表单中，方便统一提交
        // 更新用户信息
        User user = new User();
        user.setId(userId);
        user.setUsername(username);
        user.setEmail(email);

        try {
            userService.updateUser(user);
            session.setAttribute("username", username);  // 更新 session 中的用户名
            return new ModelAndView("redirect:/user/profile");  // 跳转到个人资料页面
        } catch (Exception e) {
            // 日志记录异常 logger.error("更新用户信息失败: ", e);
            ModelAndView mv = new ModelAndView("profile");  // 返回一个友好的错误页面
            mv.addObject("message", "用户信息更新失败，请稍后再试");
            return mv;
        }

    }



    /**
     * 用户预定民宿的订单信息(2024/10/04)
     * 1.检查用户是否已经登录
     * 2.获取对应用户的所有民宿订单详细信息
     * 将信息绑定并返回至视图"order"
     */
    @GetMapping("/order")
    public ModelAndView order(HttpSession session) {
        ModelAndView mv = checkUser(session);
        if (mv != null) {
            return mv;
        }

        Integer userId = getCurrentUserId(session);
        Book book = bookService.findBookByUserId(userId);
        mv = new ModelAndView("order");
        mv.addObject("book", book);
        return mv;
    }



    /**
     * 用户信息校验(2024/10/04)
     * TODO 根据具体需求修改数据校验正则
     */
    private boolean isValidUsername(String username) {
        // 实现用户名格式校验逻辑，如正则匹配
        //return username == null || !username.matches("^[a-zA-Z0-9]{5,20}$");
        return false;
    }

    private boolean isValidEmail(String email) {
        // 实现邮箱格式校验逻辑，如正则匹配
        //return email == null || !email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
        return false;
    }

    private boolean isValidPassword(String password) {
        // 实现密码格式校验逻辑，如长度和字符要求
        //return password == null || password.length() < 8;
        return false;
    }

    /**
     * 获取当前登录用户的ID(2024/10/04)
     */
    private Integer getCurrentUserId(HttpSession session) {
        Object userIdObj = session.getAttribute(SESSION_USER_ID);
        if (userIdObj instanceof Integer) {
            return (Integer) userIdObj;
        }
        return null;
    }

    /**
     * 检查用户是否已登录并存在(2024/10/04)
     */
    private ModelAndView checkUser(HttpSession session) {
        Integer userId = getCurrentUserId(session);
        if (userId == null) {
            return new ModelAndView("redirect:/login");
        }
        // 用户信息有误，清除session并提示重新登录
        if (userService.checkUserExists(userId) == 0) {
            // 日志记录 logger.warn("用户ID {} 不存在，可能的会话问题", userId);
            session.invalidate();
            ModelAndView mv = new ModelAndView("404");
            mv.addObject("message", "用户不存在，请重新登录");
            return mv;
        }
        return null; // 用户已登录且存在
    }
}
