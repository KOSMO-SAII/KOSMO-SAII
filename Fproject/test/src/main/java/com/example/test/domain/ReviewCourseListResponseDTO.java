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

    public ReviewCourseListResponseDTO(CourseReview reviewCourse){
        this.id=reviewCourse.getId();
        this.course_id=reviewCourse.getCourse_id();
        this.title=reviewCourse.getTitle();
        this.content=reviewCourse.getContent();
        this.createdBy=reviewCourse.getCreatedBy();
        this.updateDate=reviewCourse.getUpdateDate();
    }

}
