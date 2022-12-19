package com.example.test.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReviewCourseUpdateRequestDTO {
    private String title;
    private String content;
    private String course_id;

    @Builder
    public ReviewCourseUpdateRequestDTO(String title, String content, String course_id){
        this.title=title;
        this.content=content;
        this.course_id=course_id;
    }
}
