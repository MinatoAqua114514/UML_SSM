package com.uml.service.impl;

import com.uml.dao.ListingMapper;
import com.uml.model.Listing;
import com.uml.service.ListingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListingServiceImpl implements ListingService {

    @Autowired
    private ListingMapper listingMapper;


    @Override
    public int findScoreByListingId(Integer id) {
        return listingMapper.findScoreByListingId(id);
    }

    @Override
    public List<Listing> searchListingByKeyOrDistrict(String key, String district) {
        List<Listing> listings = listingMapper.searchListingByKeyOrDistrict(key, district);
        for (Listing listing : listings) {
            listing.setScore(listingMapper.findScoreByListingId(listing.getId()));
        }
        return listings;
    }
}
