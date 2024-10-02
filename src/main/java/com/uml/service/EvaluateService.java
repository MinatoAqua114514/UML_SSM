package com.uml.service;

import com.uml.model.Evaluate;

import java.util.List;

public interface EvaluateService {
    List<Evaluate> getAllEvaluateByListingId(Integer listingId);
    void insertEvaluate(Evaluate evaluate);
}
