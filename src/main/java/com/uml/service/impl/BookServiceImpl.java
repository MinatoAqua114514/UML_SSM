package com.uml.service.impl;

import com.uml.dao.BookMapper;
import com.uml.model.Book;
import com.uml.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookMapper bookMapper;

    @Override
    public void insertBook(Book book) {
        Integer userId = book.getUserId();
        Integer listingId = book.getListingId();
        Date checkInDate = book.getCheckInDate();
        Date checkOutDate = book.getCheckOutDate();
        Integer totalPrice = book.getTotalPrice();
        bookMapper.insertBook(userId, listingId, checkInDate, checkOutDate, totalPrice);
    }

    @Override
    public Integer findPriceByListingId(Integer listingId) {
        return bookMapper.findPriceByListingId(listingId);
    }

    @Override
    public Book findBookByUserId(Integer userId) {
        return bookMapper.findBookByUserId(userId);
    }

    @Override
    public Integer checkBookExists(Integer userId, Integer listingId) {
        return bookMapper.checkBookExists(userId, listingId);
    }
}
