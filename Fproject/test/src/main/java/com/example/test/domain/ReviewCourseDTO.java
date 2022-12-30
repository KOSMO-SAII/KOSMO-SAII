package com.example.test.domain;

import com.example.test.entity.Member;
import com.example.test.entity.ReviewCourse;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.User;

import javax.sound.sampled.ReverbType;
import java.time.LocalDateTime;

/**
 * 게시글 정보를 리턴할 응답(Response) 클래스
 * Entity 클래스를 생성자 파라미터로 받아 데이터를 Dto로 변환하여 응답
 * comments 필드의 List 타입을 DTO 클래스로해서 엔티티간 무한 참조를 방지
 */

@Data
@RequiredArgsConstructor
public class ReviewCourseDTO{
    private long id;
    private String course_id;
    private String title;
    private String writer;
    private String content;

    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    private String createdBy;
    private String modifiedBy;

    private Member member;
    private long memberId;

    /* Entity -> Dto*/
    public ReviewCourseDTO(ReviewCourse reviewCourse){
        this.id=reviewCourse.getId();
        this.course_id=reviewCourse.getCourse_id();
        this.title=reviewCourse.getTitle();
        this.writer=reviewCourse.getCreatedBy();
        this.content=reviewCourse.getContent();
        this.member=reviewCourse.getMember();
        ModelMapper modelMapper = new ModelMapper();
    }

//    public ReviewCourse toEntity(){
//        ReviewCourse reviewCourse = new ReviewCourse();
//        reviewCourse.setMember(member);
//        reviewCourse.setWriter(writer);
//        reviewCourse.setId(id);
//        reviewCourse.setCourse_id(course_id);
//        reviewCourse.setTitle(title);
//        reviewCourse.setContent(content);
//
//        return reviewCourse;
//    }

}

