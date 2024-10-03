package com.uml.dao;

import com.uml.model.Book;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

public interface BookMapper {
    // 用户提交预定
    void insertBook(@Param("user_id") Integer userId,
                    @Param("listing_id") Integer listingId,
                    @Param("checkin_at") Date checkInDate,
                    @Param("checkout_at") Date checkOutDate,
                    @Param("total_price") Integer totalPrice);

    // 预定时获取民宿价格
    Integer findPriceByListingId(@Param("listingId") Integer listingId);

    // 用户获取预定成功的信息
    Book findBookByUserId(@Param("userId") Integer userId);

    // 查看用户是否预定过该民宿
    Integer checkBookExists(@Param("userId") Integer userId, @Param("listingId") Integer listingId);
}
