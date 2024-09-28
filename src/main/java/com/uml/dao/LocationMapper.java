package com.uml.dao;

import org.apache.ibatis.annotations.Param;

public interface LocationMapper {
    //根据民宿id查询省市区名
    String searchDistrictNameByListingId(@Param("id") Integer id);

    String searchCityNameByListingId(@Param("id") Integer id);

    String searchProvinceNameByListingId(@Param("id") Integer id);

}
