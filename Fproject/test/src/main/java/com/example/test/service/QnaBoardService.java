package com.example.test.service;

import com.example.test.entity.QnABoard;
import com.example.test.repository.QnaBoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QnaBoardService {

    private final QnaBoardRepository qnaBoardRepository;

    // 글 작성 처리
    public void write(QnABoard qnABoard) {
        qnaBoardRepository.save(qnABoard);
    }

    // 게시글 리스트 처리
    public Page<QnABoard> list(Pageable pageable) {
        return qnaBoardRepository.findAll(pageable);
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

    public Page<QnABoard> searchList(String searchKeyword, Pageable pageable) {
        return qnaBoardRepository.findByTitleContaining(searchKeyword, pageable);
    }
}
