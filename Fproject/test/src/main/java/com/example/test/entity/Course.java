package com.example.test.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Course extends Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id")
    private Long id;

    @Column(nullable = false)
    private String days;

    @Column(name="start_day")
    private String startday;

    @Column(name="end_day")
    private String endday;
}
