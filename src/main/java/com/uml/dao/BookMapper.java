package com.uml.dao;

import org.apache.ibatis.annotations.Param;

import java.util.Date;

public interface BookMapper {
    void insertBook(@Param("user_id") Integer userId,
                    @Param("listing_id") Integer listingId,
                    @Param("checkin_at") Date checkInDate,
                    @Param("checkout_at") Date checkOutDate,
                    @Param("total_price") Integer totalPrice);
    Integer findPriceByListingId(@Param("listingId") Integer listingId);
}
