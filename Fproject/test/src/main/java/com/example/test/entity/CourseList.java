package com.example.test.entity;

import lombok.Data;
import lombok.Getter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Getter
public class CourseList extends Base{

    @Id
    @Column(name = "list_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private long course_id;

    @Column(nullable = false)
    private String title;

    private String region;

    @Column(columnDefinition = "integer default 0")
    private long viewCount;
}
