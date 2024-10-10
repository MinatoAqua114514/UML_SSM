package com.uml.service;

import com.uml.model.Listing;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ListingService {

    //根据民宿昵称查询民宿
    List<Listing> searchListingByKey(String key);

    //根据区名或民宿昵称查询民宿
    List<Listing> searchListingByKeyOrDistrict(String province, String city, String district, String key);

    //根据市名或民宿昵称查询民宿
    List<Listing> searchListingByKeyOrCity(String province, String city, String key);

    //根据省名或民宿昵称查询民宿
    List<Listing> searchListingByKeyOrProvince(String province, String key);

    //根据民宿ID获取民宿评分平均值
    int findScoreByListingId(Integer id);

    //根据民宿ID获取民宿详细信息
    Listing searchDetailsByListingId(Integer id);

    //获取所有民宿信息，用于主页展示
    List<Listing> getAllListing();

    //通过id检查民宿是否已存在
    int checkListingExists(Integer id);
}
