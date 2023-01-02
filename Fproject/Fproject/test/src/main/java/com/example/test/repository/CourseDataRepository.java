package com.example.test.repository;

import com.example.test.entity.CourseData;
import com.example.test.entity.CourseDataId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseDataRepository extends JpaRepository<CourseData, CourseDataId> {

    int countById(long id);

}
