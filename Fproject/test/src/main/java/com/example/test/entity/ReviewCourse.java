package com.example.test.entity;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Getter
@Setter
@Entity
public class ReviewCourse extends Base{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private long id;

    private String course_id;

    private String content;

    private String title;


//    @ManyToOne의 기본 Fetch 전략은 EAGER(즉시 로딩)이지만, 필요하지 않은 쿼리도 JPA에서 함께 조회하기 때문에
//    N+1 문제를 야기할 수 있어, Fetch 전략을 LAZY(지연 로딩)로 설정함.
    @ManyToOne(fetch = FetchType.LAZY) //User 입장에선 Posts와 다대일 관계이므로 @ManyToOne이 된다.
    @JoinColumn(name = "member_id") //외래 키 매핑을 위해 JoinColumn을 사용. Member 엔티티의 id 필드를 "member_id"라는 이름으로 외래 키를 가짐.
    private Member member;

    private String writer;





    @Builder
    public ReviewCourse(String course_id, String title, String content){
        this.course_id=course_id;
        this.title=title;
        this.content=content;
    }

//    게시글 수정
    public void update(String title, String content, String course_id){
        this.title=title;
        this.content=content;
        this.course_id=course_id;
    }

}
