package com.example.test.entity;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Course extends Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id")
    private Long id;


}
