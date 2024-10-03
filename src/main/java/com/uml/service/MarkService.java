package com.uml.service;

import com.uml.model.Mark;
import org.apache.ibatis.annotations.Param;

public interface MarkService {
    Mark findMarkByUserIdAndListingId(Integer userId, Integer listingId);
    void insertMark(Mark mark);
}
