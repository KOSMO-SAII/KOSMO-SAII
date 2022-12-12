package com.example.test.domain;

import com.example.test.entity.Base;
import com.example.test.entity.CourseReview;
import lombok.Getter;

@Getter
public class ReviewCourseResponseDTO extends Base {
    private long id;
    private long course_id;
    private String content;
    private String title;

    public ReviewCourseResponseDTO(CourseReview courseReview){
        this.id=courseReview.getId();
        this.course_id=courseReview.getCourse_id();
        this.title=courseReview.getTitle();
        this.content=courseReview.getContent();
        this.createdBy=courseReview.getCreatedBy();
    }
}
