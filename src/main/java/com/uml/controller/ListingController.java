package com.uml.controller;

import com.uml.model.Evaluate;
import com.uml.model.Listing;
import com.uml.service.EvaluateService;
import com.uml.service.ListingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;


@Controller
@RequestMapping("/listing")
public class ListingController {

    @Autowired
    private ListingService listingService;
    @Autowired
    private EvaluateService evaluateService;



    /**
     * 查看所有民宿
     * 显示民宿列表
     */
    @RequestMapping("/find_all_listings")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("listings_commend");
        List<Listing> listings= listingService.getAllListing();
        mv.addObject("listings",listings);
        return mv;
    }



    /**
     * 搜索和筛选民宿概要信息
     * @param province 可选的省份过滤条件
     * @param city     可选的城市过滤条件
     * @param district 可选的区县过滤条件
     * @param key      可选的关键词，用于搜索民宿名称或其他属性
     * @return ModelAndView 对象，包含视图名称和模型数据
     */
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public ModelAndView search(
            @RequestParam(value = "province", required = false) String province,
            @RequestParam(value = "city", required = false) String city,
            @RequestParam(value = "district", required = false) String district,
            @RequestParam(value = "key", required = false) String key) {

        ModelAndView mv = new ModelAndView();

        // 检查是否所有过滤条件均为空，若是，则返回主页并显示所有民宿
        if (StringUtils.isEmpty(province) && StringUtils.isEmpty(city)
                && StringUtils.isEmpty(district) && StringUtils.isEmpty(key)) {
            mv.setViewName("index");
            return mv;
        }

        List<Listing> listings = null;

        // 用户通过筛选地区和民宿名称进行搜索
        if (StringUtils.isEmpty(province)) {
            listings = listingService.searchListingByKey(key);
        } else if (StringUtils.isEmpty(city)) {
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



    /**
     * 获取民宿id
     * 跳转到对应民宿的详情页
     * 显示民宿的所有详细信息
     * 获取民宿的评分、评论
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