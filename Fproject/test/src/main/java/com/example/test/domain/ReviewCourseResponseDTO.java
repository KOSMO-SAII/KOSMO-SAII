package com.example.test.domain;

import com.example.test.entity.Base;
import com.example.test.entity.CourseReview;
import com.example.test.entity.Member;
import lombok.Getter;
import lombok.ToString;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 게시글 정보를 리턴할 응답(Response) 클래스
 * Entity 클래스를 생성자 파라미터로 받아 데이터를 Dto로 변환하여 응답
 * comments 필드의 List 타입을 DTO 클래스로해서 엔티티간 무한 참조를 방지
 */

@Getter
@ToString
public class ReviewCourseResponseDTO extends Base {
    private long id;
    private String course_id;
    private String title;
    private String author;
    private String content;
//    private String createdDate, modifiedDate;
    private Long memberId;
    private List<ReviewCommentResponseDTO> comments;

    /* Entity -> Dto*/
    public ReviewCourseResponseDTO(CourseReview reviewCourse){
        this.id=reviewCourse.getId();
        this.course_id=reviewCourse.getCourse_id();
        this.title=reviewCourse.getTitle();
        this.author=reviewCourse.getAuthor();
        this.content=reviewCourse.getContent();
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
//        this.memberId = reviewCourse.getMember().getMemberId();
//        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@" + memberId);
        this.comments = reviewCourse.getComments().stream().map(ReviewCommentResponseDTO::new).collect(Collectors.toList());
    }
}

