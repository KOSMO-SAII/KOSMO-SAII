package com.example.test.entity;

import lombok.Data;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Data
@Entity
@IdClass(CourseDataId.class)
public class CourseData {

    @Id
    private Long course_id;

    @Id
    private Long course_order;

    private String category;

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

    private String memo;
}
