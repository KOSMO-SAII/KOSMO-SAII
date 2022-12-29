package com.example.test.repository;

import com.example.test.entity.CourseList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseListRepository extends JpaRepository<CourseList, Long> {

    public List<CourseList> findTop3ByOrderByViewCountDesc();

    public List<CourseList> findByCourseid(Long course_id);

}
