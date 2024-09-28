package com.uml.service.impl;

import com.uml.dao.ListingMapper;
import com.uml.dao.LocationMapper;
import com.uml.model.Listing;
import com.uml.service.ListingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListingServiceImpl implements ListingService {

    @Autowired
    private ListingMapper listingMapper;
    @Autowired
    private LocationMapper locationMapper;


    @Override
    public List<Listing> searchListingByKeyOrDistrict(String key, String district) {
        List<Listing> listings = listingMapper.searchListingByKeyOrDistrict(key, district);
        for (Listing listing : listings) {
            listing.setScore(listingMapper.findScoreByListingId(listing.getId()));
        }
        return listings;
    }

    @Override
    public int findScoreByListingId(Integer id) {
        return listingMapper.findScoreByListingId(id);
    }

    @Override
    public Listing searchDetailsByListingId(Integer id) {
        //查询详细信息
        Listing listing = listingMapper.searchDetailsByListingId(id);
        //添加评分信息
        listing.setScore(listingMapper.findScoreByListingId(listing.getId()));
        //添加省市区名信息
        listing.setProvince_name(locationMapper.searchProvinceNameByListingId(listing.getId()));
        listing.setCity_name(locationMapper.searchCityNameByListingId(listing.getId()));
        listing.setDistrict_name(locationMapper.searchDistrictNameByListingId(listing.getId()));
        return listing;
    }
}
