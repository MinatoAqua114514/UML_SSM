package com.uml.dao;

import org.apache.ibatis.annotations.Param;

public interface LocationMapper {
    //根据民宿id查询省市区名
    String searchDistrictNameByListingId(@Param("id") Integer id);

    String searchCityNameByListingId(@Param("id") Integer id);

    String searchProvinceNameByListingId(@Param("id") Integer id);

    // 查找所有省
    String findAllProvinces();

    // 依据省名查找市名
    String findAllCitiesOfProvince(@Param("province_name") String province_name);

    // 依据市名查找区名
    String findAllDistrictsOfCity(@Param("city_name") String city_name);
}
