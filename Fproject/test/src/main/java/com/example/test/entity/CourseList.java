package com.example.test.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class CourseList extends Base{

    @Id
    @Column(name = "list_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, name="course_id")
    private long courseid;

    @Column(nullable = false)
    private String title;

    private String region;

    @Column(columnDefinition = "integer default 0")
    private long viewCount;
}
