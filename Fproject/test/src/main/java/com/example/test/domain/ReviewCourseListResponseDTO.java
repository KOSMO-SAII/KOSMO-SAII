package com.example.test.domain;

import com.example.test.entity.CourseReview;
import lombok.Getter;

import javax.persistence.Column;

@Getter
public class ReviewCourseListResponseDTO {
    private long id;
    private long course_id;
    private String content;
    private String title;

    public ReviewCourseListResponseDTO(CourseReview courseReview){
        this.id=courseReview.getId();
        this.course_id=courseReview.getCourse_id();
        this.title=courseReview.getTitle();
        this.content=courseReview.getContent();
    }

}
