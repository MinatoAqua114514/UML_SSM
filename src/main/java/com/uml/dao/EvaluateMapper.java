package com.uml.dao;

import com.uml.model.Evaluate;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EvaluateMapper {
    List<Evaluate> getAllEvaluateByListingId(@Param("listing_id") Integer listingId);
}
