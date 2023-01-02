package com.example.test.entity;

import lombok.*;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "QnABoard")
public class QnABoard extends Base{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "QnA_id")
    private Long id;

    @NotNull
    private String title;

    @NotNull
    private String content;

    @OneToMany(mappedBy = "qnABoard",orphanRemoval = true)
    private List<QnABoardReply> reply;

    @ManyToOne
    @JoinColumn(name="member")
    private Member member;

}
