package com.uml.model;

import java.sql.Timestamp;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Listing {
    private Integer id;
    private Integer district_id;
    private String title;
    private String description;
    private Integer price;
    private Timestamp created_at;
    private String location;
    private int score;
    private String province_name;
    private String city_name;
    private String district_name;
}
