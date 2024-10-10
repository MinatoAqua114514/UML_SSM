package com.uml.controller;

import com.uml.model.Mark;
import com.uml.service.BookService;
import com.uml.service.MarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("mark")
public class MarkController {

    @Autowired
    private MarkService markService;
    @Autowired
    private BookService bookService;



    /**
     * 用户提交民宿的评分
     * 获取用户的id、对民宿的评分
     */
    @RequestMapping(value = "/add/{listingId}", method = RequestMethod.POST)
    public ModelAndView addMark(@PathVariable("listingId") Integer listingId,
                                Integer score,
                                HttpSession session) {
        ModelAndView mv;
        // 未登录
        if (session.getAttribute("userId") == null) {
            mv = new ModelAndView("login");
            mv.addObject("error","请先登录");
            return mv;
        }
        // 处理参数
        Integer userId = (Integer) session.getAttribute("userId");
        // 用户需预定居住过民宿，才可以评分
        if (bookService.checkBookExists(userId, listingId) == 0) {
            mv = new ModelAndView("404"); // 假设有一个错误页面
            mv.addObject("message", "请先预定该民宿。");
        }
        Mark mark = new Mark();
        if (score > 0 && score <= 5) {
            mark.setUserId(userId);
            mark.setListingId(listingId);
            mark.setScore(score);
        }

        try {
            markService.insertMark(mark);
        } catch (Exception e) {
            // 处理异常，例如记录日志或返回错误信息
            e.printStackTrace();
            mv = new ModelAndView("404"); // 假设有一个错误页面
            mv.addObject("error", "无法提交评分，请稍后再试。");
            return mv;
        }

        mv = new ModelAndView("redirect:/listing/details/" + listingId);

        return mv;
    }
}
