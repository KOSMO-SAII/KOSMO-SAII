package com.example.test.domain;

import com.example.test.entity.Base;
import com.example.test.entity.CourseReview;
import lombok.Getter;

import javax.persistence.Column;
import java.time.LocalDateTime;

@Getter
public class ReviewCourseListResponseDTO {
    private long id;
    private long course_id;
    private String content;
    private String title;

    private String createdBy;

    private LocalDateTime updateDate;

    public ReviewCourseListResponseDTO(CourseReview courseReview){
        this.id=courseReview.getId();
        this.course_id=courseReview.getCourse_id();
        this.title=courseReview.getTitle();
        this.content=courseReview.getContent();
        this.createdBy=courseReview.getCreatedBy();
        this.updateDate=courseReview.getUpdateDate();
    }

}
