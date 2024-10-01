package com.uml.service;

import com.uml.model.Evaluate;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EvaluateService {
    List<Evaluate> getAllEvaluateByListingId(Integer listingId);
}
