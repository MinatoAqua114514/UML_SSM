package com.uml.service.impl;

import com.uml.dao.ListingMapper;
import com.uml.dao.LocationMapper;
import com.uml.model.Listing;
import com.uml.model.Location;
import com.uml.service.ListingService;
import com.uml.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class ListingServiceImpl implements ListingService {

    @Autowired
    private ListingMapper listingMapper;
    @Autowired
    private LocationMapper locationMapper;


    @Override
    public List<Listing> getAllListing() {
        List<Listing> listings = listingMapper.getAllListing();
        return getListingList(listings);
    }

    @Override
    public List<Listing> searchListingByKeyOrDistrict(String province, String city, String district, String key) {
        List<Listing> listings = listingMapper.searchListingByKeyOrDistrict(province, city, district, key);
        return getListingList(listings);
    }

    @Override
    public List<Listing> searchListingByKeyOrCity(String province, String city ,String key) {
        List<Listing> listings = listingMapper.searchListingByKeyOrCity(province, city, key);
        return getListingList(listings);
    }

    @Override
    public List<Listing> searchListingByKeyOrProvince(String province, String key) {
        List<Listing> listings = listingMapper.searchListingByKeyOrProvince(province, key);
        return getListingList(listings);
    }

    private List<Listing> getListingList(List<Listing> listings) {
        for (Listing listing : listings) {
            //添加民宿评分信息
            listing.setScore(listingMapper.findScoreByListingId(listing.getId()));
            //添加民宿地区信息
            Location location = new Location();
            location.setProvince_name(locationMapper.searchProvinceNameByListingId(listing.getId()));
            location.setCity_name(locationMapper.searchCityNameByListingId(listing.getId()));
            location.setDistrict_name(locationMapper.searchDistrictNameByListingId(listing.getId()));
            listing.setLocation(location);
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
        Location location = locationMapper.searchLocationByListingId(listing.getId());
        listing.setLocation(location);
        return listing;
    }

    @Override
    public int checkListingExists(Integer id) {
        if (listingMapper.checkListingExists(id) == 0) {
            return 0;
        } else {
            return 1;
        }
    }
}
