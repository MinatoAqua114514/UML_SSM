package com.uml.dao;

import com.uml.model.Evaluate;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EvaluateMapper {
    List<Evaluate> getAllEvaluateByListingId(@Param("listing_id") Integer listingId);
    void insertEvaluate(@Param("userId") Integer userId,
                        @Param("listingId") Integer listingId,
                        @Param("markId") Integer markId,
                        @Param("content") String content
                        );
}
