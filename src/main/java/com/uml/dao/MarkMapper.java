package com.uml.dao;

import com.uml.model.Mark;
import org.apache.ibatis.annotations.Param;

public interface MarkMapper {
    Mark findMarkByUserIdAndListingId(@Param("userId") Integer userId, @Param("listingId") Integer listingId);
}
