package com.example.test.entity;

import lombok.*;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "QnABoardReply")
public class QnABoardReply extends Base{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reply_id")
    private Long id;

    @NotNull
    @NotBlank
    @Size(min = 1, max = 255)
    @Column(nullable = false, length = 120)
    private String content;

    @ManyToOne
    @JoinColumn(name="qnaBoard")
    private QnABoard qnABoard;

    @ManyToOne
    @JoinColumn(name="member")
    private Member member;
}
