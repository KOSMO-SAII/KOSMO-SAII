package com.example.test.repository;

import com.example.test.entity.QnABoardReply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QnAReplyRepository extends JpaRepository<QnABoardReply, Long> {
};
