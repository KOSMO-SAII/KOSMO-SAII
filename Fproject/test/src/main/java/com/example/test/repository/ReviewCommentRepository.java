package com.example.test.repository;

import com.example.test.domain.ReviewCommentDTO;
import com.example.test.entity.ReviewComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewCommentRepository extends JpaRepository<ReviewComment,Long> {
    List<ReviewComment> findByPostId(long postId);
}
