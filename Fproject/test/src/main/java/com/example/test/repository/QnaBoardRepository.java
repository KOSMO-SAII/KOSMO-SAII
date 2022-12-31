package com.example.test.repository;

import com.example.test.entity.QnABoard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QnaBoardRepository extends JpaRepository<QnABoard, Long> {

    Page<QnABoard> findByTitleContaining(String searchKeyword, Pageable pageable);
}
