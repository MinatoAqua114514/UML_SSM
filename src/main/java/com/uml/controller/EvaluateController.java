package com.uml.controller;

import com.uml.model.Evaluate;
import com.uml.model.Mark;
import com.uml.service.BookService;
import com.uml.service.EvaluateService;
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
@RequestMapping("/evaluate")
public class EvaluateController {

    @Autowired
    private EvaluateService evaluateService;
    @Autowired
    private BookService bookService;
    @Autowired
    private MarkService markService;



    /**
     * 用户对民宿进行评论
     * 获取用户id和用户的评论内容
     */
    @RequestMapping(value = "/add/{listingId}", method = RequestMethod.POST)
    public ModelAndView addEvaluate(@PathVariable("listingId") Integer listingId,
                                    HttpServletRequest request,
                                    HttpSession session) {
        ModelAndView mv;
        // 未登录
        if (session.getAttribute("userId") == null) {
            mv = new ModelAndView("login");
            mv.addObject("error","请先登录");
            return mv;
        }
        // 获取参数
        Integer userId = (Integer) session.getAttribute("userId");
        String username = (String) session.getAttribute("username");
        String content = request.getParameter("content");

        // 处理参数
        // 用户需预定居住过民宿，才可以评价
        if (bookService.checkBookExists(userId, listingId) == 0) {
            mv = new ModelAndView("404"); // 假设有一个错误页面
            mv.addObject("error", "请先预定该民宿。");
        }
        Evaluate evaluate = new Evaluate();
        evaluate.setUserId(userId);
        evaluate.setUsername(username);
        evaluate.setListingId(listingId);
        evaluate.setContent(content);

        // 如果用户做过评分,则在用户的评价中加入评分信息
        Mark mark = markService.findMarkByUserIdAndListingId(userId, listingId);
        if (mark != null && mark.getId() != null){  // 确保 mark 和 mark.getId() 都不为 null
            int score = mark.getScore();
            evaluate.setScore(score);
            evaluate.setMarkId(mark.getId());
        }

        try {
            evaluateService.insertEvaluate(evaluate);
        } catch (Exception e) {
            // 处理异常，例如记录日志或返回错误信息
            e.printStackTrace();
            mv = new ModelAndView("404"); // 假设有一个错误页面
            mv.addObject("error", "无法提交评价，请稍后再试。");
            return mv;
        }

        mv = new ModelAndView("redirect:/listing/details/" + listingId);

        return mv;
    }
}
