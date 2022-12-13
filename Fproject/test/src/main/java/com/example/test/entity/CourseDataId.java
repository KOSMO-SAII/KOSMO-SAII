package com.example.test.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class CourseDataId implements Serializable {
    private Long course_id;
    private Long course_order;
}
