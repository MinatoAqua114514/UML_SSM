package com.uml.controller;

import com.uml.model.Book;
import com.uml.model.Evaluate;
import com.uml.model.Listing;
import com.uml.model.Mark;
import com.uml.service.BookService;
import com.uml.service.EvaluateService;
import com.uml.service.ListingService;
import com.uml.service.MarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;


@Controller
@RequestMapping("/listing")
public class ListingController {

    @Autowired
    private ListingService listingService;
    @Autowired
    private EvaluateService evaluateService;
    @Autowired
    private BookService bookService;
    @Autowired
    private MarkService markService;

    //搜索、筛选民宿概要信息
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public ModelAndView search(String province, String city, String district,String key) {

        ModelAndView mv = new ModelAndView();

        // 当不提供筛选信息时，返回主页显示所有民宿结果
        if(StringUtils.isEmpty(province) && StringUtils.isEmpty(key)) {
            mv.setViewName("index");
            return mv;
        }

        List<Listing> listings = null;

        // 用户通过筛选地区和民宿名称进行搜索
        if (StringUtils.isEmpty(city)) {
            listings = listingService.searchListingByKeyOrProvince(province, key);
        } else if (StringUtils.isEmpty(district)) {
            listings = listingService.searchListingByKeyOrCity(province, city, key);
        } else {
            listings = listingService.searchListingByKeyOrDistrict(province, city, district, key);
        }

        if (listings != null && !listings.isEmpty()) {
            mv.addObject("listings", listings);
            mv.setViewName("search_result");
        } else {
            mv.addObject("error", "搜索失败");
            mv.setViewName("404");
        }

        return mv;
    }

    @RequestMapping("/find_all_listings")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("listings_commend");
        List<Listing> listings= listingService.getAllListing();
        mv.addObject("listings",listings);
        return mv;
    }

    //查询显示民宿详细信息
    @RequestMapping(
            value = "/search_details",
            method = RequestMethod.POST,
            consumes = "application/json",
            produces = "application/json"
    )
    public ResponseEntity<Listing> search_details(@RequestBody Map<String, Integer> requestBody) {
        Integer id = requestBody.get("id");
        Listing listing = listingService.searchDetailsByListingId(id);
        if (listing != null) {
            return ResponseEntity.ok(listing);
        }
        return ResponseEntity.badRequest().body(null);
    }

    /**
    * 获取民宿id跳转到详情界面
    * Get listing by listingId
    * Get mark score by listingId
    * List of evaluate by listingId
    */
    @RequestMapping("/details/{id}")
    public ModelAndView toDetails(@PathVariable Integer id) {
        //检验民宿是否存在
        if (listingService.checkListingExists(id) == 0){
            return new ModelAndView("404");
        }

        //获取民宿信息
        Listing listing = listingService.searchDetailsByListingId(id);

        //获取评论区信息
        List<Evaluate> evaluate = evaluateService.getAllEvaluateByListingId(id);

        ModelAndView detailListing = new ModelAndView("listing_detail");
        detailListing.addObject("listing", listing);
        detailListing.addObject("evaluate", evaluate);

        return detailListing;
    }


    /**
     * 提交预定信息
     * Insert evaluate by Text, score, userID, listingID
     */
    @RequestMapping(value = "/addBook/{listingId}", method = RequestMethod.POST)
    public ModelAndView addBook(@PathVariable("listingId") Integer listingId, HttpServletRequest request, HttpSession session) {
        ModelAndView mv;
        // 未登录
        if (session.getAttribute("userId") == null) {
            mv = new ModelAndView("redirect:/login");
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
            mv = new ModelAndView("redirect:/errorPage");
        }
        return mv;
    }

    // 提交用户评论
    @RequestMapping(value = "/addEvaluate/{listingId}", method = RequestMethod.POST)
    public ModelAndView addEvaluate(@PathVariable("listingId") Integer listingId, HttpServletRequest request, HttpSession session){
        ModelAndView mv;
        // 未登录
        if (session.getAttribute("userId") == null) {
            mv = new ModelAndView("redirect:/login");
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
            mv.addObject("message", "请先预定该民宿。");
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
            mv.addObject("message", "无法提交评价，请稍后再试。");
            return mv;
        }

        mv = new ModelAndView("redirect:/listing/details/" + listingId);

        return mv;
    }

    /**
     * 用户提交民宿的评分
     *
     */
    @RequestMapping(value = "/addMark/{listingId}", method = RequestMethod.POST)
    public ModelAndView addMark(@PathVariable("listingId") Integer listingId, Integer score, HttpServletRequest request, HttpSession session) {
        ModelAndView mv;
        // 未登录
        if (session.getAttribute("userId") == null) {
            mv = new ModelAndView("redirect:/login");
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
            mv.addObject("message", "无法提交评分，请稍后再试。");
            return mv;
        }

        mv = new ModelAndView("redirect:/listing/details/" + listingId);

        return mv;
    }
}



//关于前端页面获取民宿评分，例：
//<div id="listing-score"></div>
//
//<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
//<script>
//$(document).ready(function() {
//    var listingId = '你的民宿ID'; // 替换为实际的民宿ID
//
//    $.ajax({
//            url: listingId + '/score',
//            method: 'GET',
//            success: function(score) {
//        $('#listing-score').text('评分: ' + score);
//    },
//    error: function() {
//        $('#listing-score').text('无法获取评分');
//    }
//        });
//});
//</script>