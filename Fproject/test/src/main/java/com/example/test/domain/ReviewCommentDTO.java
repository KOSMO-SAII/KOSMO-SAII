package com.example.test.domain;

import com.example.test.entity.ReviewCourse;
import com.example.test.entity.Member;
import com.example.test.entity.ReviewComment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewCommentDTO {
    private Long id;
    private String comment;
    private Long postId;

    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private String createdBy;
    private String modifiedBy;

    /* Dto -> Entity */
    public ReviewComment toReviewComment() {
        ReviewComment comments = ReviewComment.builder()
                .id(id)
                .comment(comment)
                .build();

        return comments;
    }
}
