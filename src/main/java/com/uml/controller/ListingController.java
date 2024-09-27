package com.uml.controller;

import com.uml.model.Listing;
import com.uml.service.ListingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
public class ListingController {

    @Autowired
    private ListingService listingService;

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
        List<Listing> listings = listingService.searchListingByKeyOrDistrict(key, district);
        if (listings != null && !listings.isEmpty()) {
            return ResponseEntity.ok(listings);
        }
        return ResponseEntity.badRequest().body(null);
    }

    // 获取民宿的评分
    @GetMapping("/{id}/score")
    public int getScoreByListingId(@PathVariable Integer id) {
        return listingService.findScoreByListingId(id);
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