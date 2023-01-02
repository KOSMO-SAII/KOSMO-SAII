package com.example.test.controller;

import com.example.test.entity.QnABoardReply;
import com.example.test.service.QnAReplyService;
import lombok.RequiredArgsConstructor;
import org.hibernate.type.PrimitiveCharacterArrayNClobType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class QnAReplyController {
    @Autowired
    private final QnAReplyService qnAReplyService;

    @PostMapping("/reply_write")
    public String replyWrite(@ModelAttribute QnABoardReply qnABoardReply, Long qna_id, Principal principal){

        return qnAReplyService.replyWrite(qnABoardReply, principal, qna_id);

    }

    @PostMapping("/reply_delete")
    public String replyDelete(@ModelAttribute QnABoardReply qnABoardReply, Long reply_id,Long qna_id){
        System.out.println(reply_id+"1번");
        System.out.println(qna_id+"2번");
        System.out.println(qnABoardReply.getId());


        return qnAReplyService.replyDelete(qnABoardReply,qna_id);
    }

}