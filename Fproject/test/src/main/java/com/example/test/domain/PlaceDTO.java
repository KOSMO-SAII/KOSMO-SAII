package com.example.test.domain;

import lombok.Data;

import javax.persistence.Column;
@Data
public class PlaceDTO {

    private  Long id;

    private String address_id;

    private String address_name;

    private String road_address_name;

    private String phone_number;

    private String place_name;

    private String place_url;

    private String x;

    private String y;
}
