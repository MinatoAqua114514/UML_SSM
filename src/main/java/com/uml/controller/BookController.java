package com.uml.controller;

import com.uml.model.Book;
import com.uml.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;

    /**
     * 用户提交预定信息
     * 获取用户id、民宿id、入住时间和退房时间
     * 计算获取预定所需金额
     */
    @RequestMapping(value = "/add/{listingId}", method = RequestMethod.POST)
    public ModelAndView addBook(@PathVariable("listingId") Integer listingId,
                                HttpServletRequest request,
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
        String checkInDateStr = request.getParameter("checkInDate");
        String checkOutDateStr = request.getParameter("checkOutDate");

        try {
            // 注意format的格式要与日期String的格式相匹配
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date checkInDate = sdf.parse(checkInDateStr);
            Date checkOutDate = sdf.parse(checkOutDateStr);

            // 对日期进行校验
            if (checkOutDate.before(checkInDate)) {
                // 退房日期早于入住日期，重定向到错误页面
                mv = new ModelAndView("redirect:/errorPage");
                return mv;
            }

            // 计算日期差
            long diffInMillies = Math.abs(checkOutDate.getTime() - checkInDate.getTime());
            long daysBetween = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);

            // 计算订金
            int price = bookService.findPriceByListingId(listingId);
            int totalPrice = price * (int)daysBetween;
            // 新建book
            Book book = new Book();
            book.setUserId(userId);
            book.setListingId(listingId);
            book.setCheckInDate(checkInDate);
            book.setCheckOutDate(checkOutDate);
            book.setTotalPrice(totalPrice);
            // 添加book
            bookService.insertBook(book);

            mv = new ModelAndView("redirect:/listing/details/" + listingId);
        } catch (ParseException e) {
            // 日期格式错误处理
            mv =  new ModelAndView("404");
        }
        return mv;
    }
}
