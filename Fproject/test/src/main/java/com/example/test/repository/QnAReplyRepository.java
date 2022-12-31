package com.example.test.repository;

import com.example.test.entity.QnABoardReply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface QnAReplyRepository extends JpaRepository<QnABoardReply, Long> {
    List<QnABoardReply> findByQnABoard_Id(Long id);
};
