package com.example.test.entity;

import lombok.*;
import org.springframework.security.core.userdetails.User;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Getter
@Entity
public class CourseReview extends Base{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private long id;

    private String course_id;

    private String content;

    private String title;

    private String author;


//    @Column(columnDefinition = "integer default 0")
//    private int view;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Member member;

    //@ManyToOne 과 @OneToMany 로 양방향 관계
    //게시글이 삭제되면 댓글 또한 삭제되어야 하기 때문에 CascadeType.REMOVE 속성을 사용
    @OneToMany(mappedBy = "posts", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @OrderBy("id asc")
    private List<ReviewComment> comments;

    @Builder
    public CourseReview(String course_id, String title, String content, String author){
        this.course_id=course_id;
        this.title=title;
        this.content=content;
        this.author=author;
    }

//    게시글 수정
    public void update(String title, String content, String course_id){
        this.title=title;
        this.content=content;
        this.course_id=course_id;
    }

}
