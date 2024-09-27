package com.uml.dao;

import com.uml.model.Listing;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ListingMapper {
    // 根据民宿ID获取民宿评分平均值
    int findScoreByListingId(@Param("id") Integer id);

    //根据用户输入或筛选查询民宿
    List<Listing> searchListingByKeyOrDistrict(@Param("key") String key, @Param("district") String district);
}
