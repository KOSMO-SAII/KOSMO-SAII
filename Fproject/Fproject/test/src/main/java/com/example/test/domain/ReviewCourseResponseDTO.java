package com.example.test.domain;

import com.example.test.entity.Base;
import com.example.test.entity.CourseReview;
import com.example.test.entity.Member;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 게시글 정보를 리턴할 응답(Response) 클래스
 * Entity 클래스를 생성자 파라미터로 받아 데이터를 Dto로 변환하여 응답
 * comments 필드의 List 타입을 DTO 클래스로해서 엔티티간 무한 참조를 방지
 */

@Getter
public class ReviewCourseResponseDTO extends Base {
    private long id;
    private String course_id;
    private String content;
    private String title;
    private String author;

    //댓글 추가
//    private Long userId;
    private List<ReviewCommentResponseDTO> comments;

    /* Entity -> Dto*/
    public ReviewCourseResponseDTO(CourseReview reviewCourse){
        this.id=reviewCourse.getId();
        this.course_id=reviewCourse.getCourse_id();
        this.title=reviewCourse.getTitle();
        this.content=reviewCourse.getContent();
        this.author=reviewCourse.getAuthor();
//        this.userId = reviewCourse.getUser().getMemberId();
        this.comments = reviewCourse.getComments().stream().map(ReviewCommentResponseDTO::new).collect(Collectors.toList());
    }
}

