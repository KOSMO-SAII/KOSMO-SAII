package com.example.test.entity;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@Getter
public class LikedPost extends Base{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "liked_id")
    private long id;

    @Column(nullable = false)
    private long post_id;

}

