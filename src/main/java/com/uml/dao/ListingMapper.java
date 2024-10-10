package com.uml.dao;

import com.uml.model.Listing;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ListingMapper {

    //根据民宿昵称查询民宿
    List<Listing> searchListingByKey(@Param("key") String key);

    //根据区名或民宿昵称查询民宿
    List<Listing> searchListingByKeyOrDistrict(@Param("province") String province,
                                               @Param("city") String city,
                                               @Param("district") String district,
                                               @Param("key") String key);

    //根据市名或民宿昵称查询民宿
    List<Listing> searchListingByKeyOrCity(@Param("province") String province,
                                               @Param("city") String city,
                                               @Param("key") String key);

    //根据省名或民宿昵称查询民宿
    List<Listing> searchListingByKeyOrProvince(@Param("province") String province,
                                               @Param("key") String key);

    //根据民宿ID获取民宿评分平均值
    int findScoreByListingId(@Param("id") Integer id);

    //根据民宿ID获取民宿详细信息
    Listing searchDetailsByListingId(@Param("id") Integer id);

    //获取所有民宿信息，用户主页展示
    List<Listing> getAllListing();

    //通过id检查民宿是否已存在
    int checkListingExists(@Param("id") Integer id);
}
