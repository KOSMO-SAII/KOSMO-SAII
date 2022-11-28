package com.example.test.entity;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Main_board extends BaseTime{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long post_id;

    @Column(nullable = false)
    private Long course_id;

    @Column(nullable = false)
    private Long user_id;

    @Column(nullable = false)
    private String title;

    private String region;

}
