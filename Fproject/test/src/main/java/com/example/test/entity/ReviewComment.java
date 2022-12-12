package com.example.test.entity;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@Getter
public class ReviewComment extends Base{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    private Long post_id;
}
