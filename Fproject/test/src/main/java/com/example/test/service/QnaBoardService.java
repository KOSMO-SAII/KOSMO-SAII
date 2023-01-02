package com.example.test.service;

import com.example.test.entity.Member;
import com.example.test.entity.QnABoard;
import com.example.test.repository.MemberRepository;
import com.example.test.repository.QnaBoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
@RequiredArgsConstructor
public class QnaBoardService {

    private final QnaBoardRepository qnaBoardRepository;

    private final MemberRepository memberRepository;

    // 글 작성 처리
    public void write(QnABoard qnABoard, Principal principal) {
        Member member = memberRepository.findByLoginId(principal.getName());
        qnABoard.setMember(member);
        qnaBoardRepository.save(qnABoard);
    }

    // 게시글 리스트 처리
    public Page<QnABoard> list(PageRequest pageRequest) {
        return qnaBoardRepository.findAllDesc(pageRequest);
    }

    // 특정 게시글 상세보기
    public QnABoard view(Long id) {
        return qnaBoardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("없는 id입니다."));
    }

    // 특정 게시글 삭제
    public void deleteById(Long id) {
        qnaBoardRepository.deleteById(id);
    }

    public Page<QnABoard> searchList(String searchKeyword, PageRequest pageRequest) {
        return qnaBoardRepository.findByTitleContaining(searchKeyword, pageRequest);
    }
}
