package com.uml.service.impl;

import com.uml.dao.EvaluateMapper;
import com.uml.model.Evaluate;
import com.uml.service.EvaluateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EvaluateServiceImpl implements EvaluateService {

    @Autowired
    private EvaluateMapper evaluateMapper;

    @Override
    public List<Evaluate> getAllEvaluateByListingId(Integer listingId) {
        return evaluateMapper.getAllEvaluateByListingId(listingId);
    }
}
