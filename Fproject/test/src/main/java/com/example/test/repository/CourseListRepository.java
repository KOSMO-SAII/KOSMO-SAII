package com.example.test.repository;

import com.example.test.entity.CourseList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseListRepository extends JpaRepository<CourseList, Long> {

    List<CourseList> findTop3ByOrderByViewCountDesc();

    List<CourseList> findByCourseid(Long course_id);

    List<CourseList> findByCreatedBy(String name);

    Page<CourseList> findByRegionContainsOrderByIdDesc(String searchStr, Pageable pageable);
}
