package com.uml.controller;

import com.uml.model.Evaluate;
import com.uml.model.Listing;
import com.uml.service.EvaluateService;
import com.uml.service.ListingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/listing")
public class ListingController {

    @Autowired
    private ListingService listingService;
    @Autowired
    private EvaluateService evaluateService;

    //搜索、筛选民宿概要信息
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public ModelAndView search(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        String key = request.getParameter("key");
        String district = request.getParameter("district");
        List<Listing> listings = listingService.searchListingByKeyOrDistrict(key, district);
        if (listings != null && !listings.isEmpty()) {
            mv.addObject("listings", listings);
            mv.setViewName("search_result");
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

    // 获取民宿的评分
    @GetMapping("/{id}/score")
    public ModelAndView getScoreByListingId(@PathVariable Integer id) {
        ModelAndView mv = new ModelAndView("score");
        mv.addObject("score", listingService.findScoreByListingId(id));
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

    /*
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
}

// TODO Insert evaluate by Text, score, userID, listingID

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