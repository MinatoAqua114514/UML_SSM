package com.uml.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Book {
    private Integer id;
    private Integer userId;
    private Integer listingId;
    private Date checkInDate;
    private Date checkOutDate;
    private Integer totalPrice;
}
