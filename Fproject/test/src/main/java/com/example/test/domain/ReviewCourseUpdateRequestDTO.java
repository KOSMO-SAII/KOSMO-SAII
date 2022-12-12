package com.example.test.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReviewCourseUpdateRequestDTO {
    private String title;
    private String content;

    @Builder
    public ReviewCourseUpdateRequestDTO(String title, String content){
        this.title=title;
        this.content=content;
    }
}
