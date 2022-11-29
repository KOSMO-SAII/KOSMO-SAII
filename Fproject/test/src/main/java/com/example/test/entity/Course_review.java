package com.example.test.entity;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Course_review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long review_id;

    @Column(nullable = false)
    private long user_id;

    @Column(nullable = false)
    private long course_id;


}
