package com.uml.service;

import com.uml.model.Book;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

public interface BookService {
    void insertBook(Book book);
    Integer findPriceByListingId(Integer listingId);
}
