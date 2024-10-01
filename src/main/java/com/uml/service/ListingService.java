package com.uml.service;

import com.uml.model.Listing;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ListingService {

    //根据用户输入或筛选查询民宿
    List<Listing> searchListingByKeyOrDistrict(String key, String district);

    //根据民宿ID获取民宿评分平均值
    int findScoreByListingId(Integer id);

    //根据民宿ID获取民宿详细信息
    Listing searchDetailsByListingId(Integer id);

    //获取所有民宿信息，用户主页展示
    List<Listing> getAllListing();

    //通过id检查民宿是否已存在
    int checkListingExists(Integer id);
}
