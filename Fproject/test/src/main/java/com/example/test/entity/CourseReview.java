package com.example.test.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@NoArgsConstructor
@Entity
@Getter
@ToString
public class CourseReview extends Base{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private long id;

    @Column(nullable = false)
    private long course_id;

    private String content;

    private String title;


    @Builder
    public CourseReview(long course_id, String title, String content){
        this.course_id=course_id;
        this.title=title;
        this.content=content;
    }
}
