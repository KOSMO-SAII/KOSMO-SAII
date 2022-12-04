package com.example.test.entity;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class MainBoard extends Base{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @Column(nullable = false)
    private Long course_id;

    @Column(nullable = false)
    private String title;

    private String region;

}
