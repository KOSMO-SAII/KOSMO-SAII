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

    public ReviewCourseResponseDTO(CourseReview reviewCourse){
        this.id=reviewCourse.getId();
        this.course_id=reviewCourse.getCourse_id();
        this.title=reviewCourse.getTitle();
        this.content=reviewCourse.getContent();
        this.createdBy=reviewCourse.getCreatedBy();
    }
}
