package com.uml.service;

import com.uml.model.Book;

public interface BookService {
    // 用户提交预定
    void insertBook(Book book);

    // 预定时获取民宿价格
    Integer findPriceByListingId(Integer listingId);

    // 用户获取预定成功的信息
    Book findBookByUserId(Integer userId);

    // 查看用户是否预定过该民宿
    Integer checkBookExists(Integer userId, Integer listingId);
}
