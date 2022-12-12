package com.example.test.entity;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@Getter
public class PlaceComment extends Base{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    @Column(nullable = false)
    private Long place_id;

    @Column(nullable = false)
    private Double score;

    private String comment;
}
