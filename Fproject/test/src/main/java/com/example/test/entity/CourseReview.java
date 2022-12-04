package com.example.test.entity;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class CourseReview extends Base{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private long id;

    @Column(nullable = false)
    private long course_id;

    private String content;
}
