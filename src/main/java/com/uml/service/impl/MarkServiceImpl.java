package com.uml.service.impl;

import com.uml.dao.MarkMapper;
import com.uml.model.Mark;
import com.uml.service.MarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MarkServiceImpl implements MarkService {

    @Autowired
    private MarkMapper markMapper;

    public Mark findMarkByUserIdAndListingId(Integer userId, Integer listingId) {
        return markMapper.findMarkByUserIdAndListingId(userId, listingId);
    }

    public void insertMark(Mark mark) {
        Integer userId = mark.getUserId();
        Integer listingId = mark.getListingId();
        Integer score = mark.getScore();
        markMapper.insertMark(userId, listingId, score);
    }
}
