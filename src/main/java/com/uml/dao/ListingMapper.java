package com.uml.dao;

import com.uml.model.Listing;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ListingMapper {

    //根据用户输入或筛选查询民宿
    List<Listing> searchListingByKeyOrDistrict(@Param("key") String key, @Param("district") String district);

    //根据民宿ID获取民宿评分平均值
    int findScoreByListingId(@Param("id") Integer id);

    //根据民宿ID获取民宿详细信息
    Listing searchDetailsByListingId(@Param("id") Integer id);

    //获取所有民宿信息，用户主页展示
    List<Listing> getAllListing();

    //通过id检查民宿是否已存在
    int checkListingExists(@Param("id") Integer id);
}
