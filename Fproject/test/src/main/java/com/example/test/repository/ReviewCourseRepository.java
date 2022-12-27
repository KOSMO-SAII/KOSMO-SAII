package com.example.test.repository;

import com.example.test.domain.ReviewCourseDTO;
import com.example.test.entity.ReviewCourse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewCourseRepository extends JpaRepository<ReviewCourse, Long> {

    //기본적 CRUD 메소드 자동 생성 해준다. save, update, delete.. 자동적으로 쿼리를 해준다. 명시하지 않아도.
    //사용할 쿼리문들 작성
    //jpa 쿼리 작성 법 검색

    @Query(value = "SELECT r FROM ReviewCourse r ORDER BY r.id DESC")
    Page<ReviewCourseDTO> findAllDesc(PageRequest pageRequest);
    //조회용 프레임워크 사용




    List<ReviewCourse> findByTitleContaining(String title);

//    @Modifying
//    @Query("update CourseReview r set r.view = r.view + 1 where r.id = :id")
//    int updateView(Long id);
}
