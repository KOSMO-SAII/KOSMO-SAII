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

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "memberId")
//    private Member user;

    @OneToMany(mappedBy = "reviewCourse", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @OrderBy("id asc")
    private List<ReviewComment> comments;

    @Builder
    public CourseReview(String course_id, String title, String content, String author){
        this.course_id=course_id;
        this.title=title;
        this.content=content;
        this.author=author;
    }

    public void update(String title, String content, String course_id){
        this.title=title;
        this.content=content;
        this.course_id=course_id;
    }

}
