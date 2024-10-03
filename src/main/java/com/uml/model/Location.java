package com.uml.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Location {
    private Integer id;
    private String province_name;
    private String city_name;
    private String district_name;
}
