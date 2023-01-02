package com.example.test.service;

import com.example.test.entity.Member;
import com.example.test.entity.QnABoard;
import com.example.test.entity.QnABoardReply;
import com.example.test.repository.MemberRepository;
import com.example.test.repository.QnAReplyRepository;
import com.example.test.repository.QnaBoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QnAReplyService {
    @Autowired
    private final QnAReplyRepository qnAReplyRepository;

    @Autowired
    private final QnaBoardRepository qnaBoardRepository;

    @Autowired
    private final MemberRepository memberRepository;

    public String replyWrite(QnABoardReply qnABoardReply, Principal principal, Long qna_id){
        System.out.println(qnABoardReply);
        System.out.println(qna_id);
        System.out.println(principal.getName());
        Member findMember = memberRepository.findByLoginId(principal.getName());
        Optional<QnABoard> findBoard = qnaBoardRepository.findById(qna_id);

        qnABoardReply.setQnABoard(findBoard.get());
        qnABoardReply.setMember(findMember);
        qnAReplyRepository.save(qnABoardReply);

        return "redirect:/QnABoard/view/"+qna_id;
    }

    public String replyDelete(QnABoardReply qnABoardReply,Long reply_id){

        qnAReplyRepository.delete(qnABoardReply);

        return "redirect:/QnABoard/view/"+reply_id;
    }


    public List<QnABoardReply> view(Long id) {
        return qnAReplyRepository.findByQnABoard_Id(id);
    }

};