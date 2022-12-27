package com.example.test.domain;

import com.example.test.entity.CourseReview;
import com.example.test.entity.Member;
import com.example.test.entity.ReviewComment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.User;

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
    private Member member;
    private CourseReview posts;

    /* Dto -> Entity */
    public ReviewComment toReviewComment() {
        ReviewComment comments = ReviewComment.builder()
                .id(id)
                .comment(comment)
                .createdDate(createdDate)
                .modifiedDate(modifiedDate)
                .member(member)
                .posts(posts)
                .build();

        return comments;
    }
}
