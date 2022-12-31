package com.example.test.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "QnABoard")
public class QnABoard extends Base{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "QnA_id")
    private Long id;

    private String title;

    private String content;

    @OneToMany(mappedBy = "qnABoard")
    private List<QnABoardReply> reply;

}
