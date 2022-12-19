package com.example.test.domain;

import com.example.test.entity.CourseReview;
import com.example.test.entity.Member;
import com.example.test.entity.ReviewComment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewCommentRequestDTO {
    private Long id;
    private String comment;
    private String createdDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));
    private String modifiedDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));
    private Member user;
    private CourseReview reviewCourse;

    /* Dto -> Entity */
    public ReviewComment toReviewComment() {
        ReviewComment comments = ReviewComment.builder()
                .id(id)
                .comment(comment)
                .createdDate(createdDate)
                .modifiedDate(modifiedDate)
                .user(user)
                .reviewCourse(reviewCourse)
                .build();

        return comments;
    }
}
