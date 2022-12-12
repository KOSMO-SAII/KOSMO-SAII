package com.example.test.domain;

import com.example.test.entity.Base;
import com.example.test.entity.CourseReview;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReviewCourseSaveRequestDTO extends Base {
    private String title;
    private String content;

    @Builder
    public ReviewCourseSaveRequestDTO(String title, String content, String createdBy){
        this.title=title;
        this.content=content;
        this.createdBy=createdBy;
    }

    public CourseReview toCourseReview(){
        return CourseReview.builder()
                .title(title)
                .content(content)
                .createdBy(createdBy)
                .build();
    }



}
