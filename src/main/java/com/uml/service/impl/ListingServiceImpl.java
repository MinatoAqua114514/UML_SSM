package com.uml.service.impl;

import com.uml.dao.ListingMapper;
import com.uml.dao.LocationMapper;
import com.uml.model.Listing;
import com.uml.model.Location;
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
        return getListingList(listings);
    }

    @Override
    public List<Listing> getAllListing() {
        List<Listing> listings = listingMapper.getAllListing();
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
        listing.getLocation().setProvince_name(locationMapper.searchProvinceNameByListingId(listing.getId()));
        listing.getLocation().setCity_name(locationMapper.searchCityNameByListingId(listing.getId()));
        listing.getLocation().setDistrict_name(locationMapper.searchDistrictNameByListingId(listing.getId()));
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
