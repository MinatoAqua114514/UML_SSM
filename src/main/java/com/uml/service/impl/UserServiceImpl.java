package com.uml.service.impl;

import com.uml.dao.UserMapper;
import com.uml.model.Listing;
import com.uml.model.User;
import com.uml.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * @author: JLChen
 * @since: 2024-09-26 12:11
 * @description:
 */


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public int checkUsernameExists(String username) {
        if (userMapper.checkUsernameExists(username) == 0) {
            return 0;
        } else {
            return 1;
        }
    }

    @Override
    public int checkEmailExists(String email) {
        if (userMapper.checkEmailExists(email) == 0) {
            return 0;
        } else {
            return 1;
        }
    }

    @Override
    public void insertUser(User user) {
        userMapper.insertUser(user);
    }

    @Override
    public String findPasswordByUsername(String username) {
        return userMapper.findPasswordByUsername(username);
    }

    @Override
    public List<Listing> searchListingByKeyOrDistrict(String key, String district) {
        List<Listing> listings = null;
        listings = userMapper.searchListingByKeyOrDistrict(key, district);
        for (Listing listing : listings) {
            listing.setScore(userMapper.searchMarkByListingId(listing.getId()));
        }
        return listings;
    }
}
