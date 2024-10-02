package com.uml.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Evaluate {
    private Integer id;
    private Integer userId;
    private Integer listingId;
    private Integer markId;
    private String content;
    private Integer score;
    private String username;
    //    private Listing listing;
}
