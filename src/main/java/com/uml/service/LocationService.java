package com.uml.service;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LocationService {

    // 查找所有省
    List<String> findAllProvinces();

    // 依据省名查找市名
    List<String> findAllCitiesOfProvince(String province_name);

    // 依据市名查找区名
    List<String> findAllDistrictsOfCity(String city_name);
}