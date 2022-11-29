package com.example.test.entity;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Liked_post extends BaseTime{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private long post_id;

    @Column(nullable = false)
    private long user_id;

}

