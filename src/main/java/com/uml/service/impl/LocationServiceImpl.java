package com.uml.service.impl;

import com.uml.dao.LocationMapper;
import com.uml.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class LocationServiceImpl implements LocationService {

    @Autowired
    LocationMapper locationMapper;

    @Override
    public List<String> findAllProvinces() {
        return Collections.singletonList(locationMapper.findAllProvinces());
    }

    @Override
    public List<String> findAllCitiesOfProvince(String province_name) {
        return Collections.singletonList(locationMapper.findAllCitiesOfProvince(province_name));
    }

    @Override
    public List<String> findAllDistrictsOfCity(String city_name) {
        return Collections.singletonList(locationMapper.findAllDistrictsOfCity(city_name));
    }
}
