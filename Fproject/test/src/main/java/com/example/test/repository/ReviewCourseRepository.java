package com.example.test.repository;

import com.example.test.entity.CourseReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewCourseRepository extends JpaRepository<CourseReview, Long> {

    @Query(value = "SELECT r FROM CourseReview r ORDER BY r.id DESC")
    List<CourseReview> findAllDesc();

    List<CourseReview> findByTitleContaining(String title);
}
