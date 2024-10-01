package com.uml.service;

import com.uml.model.Book;

public interface BookService {
    void insertBook(Book book);
    Integer findPriceByListingId(Integer listingId);
}
