package com.uml.service;

import com.uml.model.Mark;

public interface MarkService {
    Mark findMarkByUserIdAndListingId(Integer userId, Integer listingId);
}
