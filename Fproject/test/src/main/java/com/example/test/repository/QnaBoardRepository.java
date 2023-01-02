package com.example.test.repository;

import com.example.test.domain.ReviewCourseDTO;
import com.example.test.entity.QnABoard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface QnaBoardRepository extends JpaRepository<QnABoard, Long> {

    Page<QnABoard> findByTitleContaining(String searchKeyword, Pageable pageable);

    @Query(value = "SELECT r FROM QnABoard r ORDER BY r.id DESC")
    Page<QnABoard> findAllDesc(PageRequest pageRequest);
}
