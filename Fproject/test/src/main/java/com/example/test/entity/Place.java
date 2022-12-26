package com.example.test.entity;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
public class Place {

    @Id
    @Column(name = "place_id")
    private Long id;

    @Column(nullable = false)
    private String address_id;

    private String address_name;

    private String road_address_name;

    private String phone_number;

    @Column(nullable = false)
    private String place_name;

    private String place_url;

    @Column(nullable = false)
    private String x;

    @Column(nullable = false)
    private String y;
}
