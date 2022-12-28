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

    private String author;

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
