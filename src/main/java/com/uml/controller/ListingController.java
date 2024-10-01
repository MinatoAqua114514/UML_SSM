package com.uml.controller;

import com.uml.model.Listing;
import com.uml.service.ListingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/listing")
public class ListingController {

    @Autowired
    private ListingService listingService;

    //搜索、筛选民宿概要信息
    @RequestMapping(
            value = "/search",
            method = RequestMethod.POST,
            consumes = "application/json",
            produces = "application/json"
    )
    public ResponseEntity<List<Listing>> search(@RequestBody Map<String, String> requestBody) {
        String key = requestBody.get("key");
        String district = requestBody.get("district");
        List<Listing> listings = listingService.searchListingByKeyOrDistrict(key, district);
        if (listings != null && !listings.isEmpty()) {
            return ResponseEntity.ok(listings);
        }
        return ResponseEntity.badRequest().body(null);
    }

    @RequestMapping("/find_all_listings")
    public ModelAndView index() {
        ModelAndView mav = new ModelAndView("index");
        List<Listing> listings= listingService.getAllListing();
        mav.addObject("listings",listings);
        return mav;
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