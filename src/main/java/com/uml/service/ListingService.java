package com.uml.service;

import com.uml.model.Listing;

import java.util.List;

public interface ListingService {

    //根据用户输入或筛选查询民宿
    List<Listing> searchListingByKeyOrDistrict(String key, String district);

    //根据民宿ID获取民宿评分平均值
    int findScoreByListingId(Integer id);

    //根据民宿ID获取民宿详细信息
    Listing searchDetailsByListingId(Integer id);
}
