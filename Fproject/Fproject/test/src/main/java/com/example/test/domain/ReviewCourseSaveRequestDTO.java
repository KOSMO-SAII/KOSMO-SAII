package com.example.test.domain;

import com.example.test.entity.Base;
import com.example.test.entity.BaseTime;
import com.example.test.entity.CourseReview;
import com.example.test.entity.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.python.antlr.ast.Str;
import org.springframework.data.annotation.CreatedBy;

@Getter
@NoArgsConstructor
public class ReviewCourseSaveRequestDTO {
    private String title;
    private String content;
    private String author;

    private String course_id;


    @Builder
    public ReviewCourseSaveRequestDTO(String title, String content, String author, String course_id){
        // 빌더 어노테이션이란? 클래스 레벨에 붙이거나 생성자에 붙이면 빌더 패턴을 자동으로 생성해준다.
        // 빌더 패턴이란? 복잡한 객체를 생성하는 방법을 정의하는 클래스 와 표현하는 방법을 정의하는 클래스를
        // 별도로 분리 . 서로 다른 표현이라도 이를 생성할 수 있는 동일한 절차를 제공하는 패턴
        // 생성자가 많은 경우 빌더 패턴을 사용한다.
        // 일관성과 불변성을 유지하면서 생성자가 많아도 괜찮다
        // CourseReview 클래스를 이용 데이터베이스에 저장할 객체 생성하기 위한 toCourseReview 메소드 구현
        this.title=title;
        this.content=content;
        this.author=author;
        this.course_id=course_id;
    }

    public CourseReview toCourseReview(){

        CourseReview cr = CourseReview.builder()
                .title(title)
                .content(content)
                .author(author)
                .course_id(course_id)
                .build();
        // 빌더 패턴 각각 생성자로 객체 생성
        System.out.println(cr);

        return cr;
    }
    // Entity인 courseReview 클래스가 있어도 dto 클래스 추가 생성
    // entity 클래스를 request, response 클래스로 사용해선 안된다. entity 클래스는 DB와 밀접한 핵심 클래스
    // view layer와 db layer 역할 분리


}
