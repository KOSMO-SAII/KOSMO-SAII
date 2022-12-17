package com.example.test.reviewboard;

import com.example.test.TestApplication;
import com.example.test.entity.CourseReview;
import com.example.test.repository.ReviewCourseRepository;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(classes = TestApplication.class)
public class ReviewTests {
    @Autowired
    ReviewCourseRepository courseReviewRepository;

    @Test
    void save(){
        CourseReview params = CourseReview.builder()
                .title("테스트 제목")
                .content("테스트 내용")
                .course_id("3")
                .build();
        System.out.println(params.toString());
        courseReviewRepository.save(params);

//        CourseReview entity = courseReviewRepository.findById((long) 1).get();
        long id = 2;
        Optional<CourseReview> temp = courseReviewRepository.findById(id);
        if(temp.isPresent()) {
            CourseReview entity = temp.get();

//            assertThat(entity.getTitle()).isEqualTo("테스트 제목");
//            assertThat(entity.getContent()).isEqualTo("테스트 내용");
//            assertThat(entity.getCourse_id()).isEqualTo(1);
        }
        else{
            System.out.println("save error");
            System.out.println(temp);
        }
    }

    @Test
    void findAll(){
        long reviewsCount = courseReviewRepository.count();
        List<CourseReview> courseReviewList = courseReviewRepository.findAll();
    }

    @Test
    void delete(){
        long id = 1;
        Optional<CourseReview> temp = courseReviewRepository.findById(id);
        if(temp.isPresent()) {
            CourseReview entity = temp.get();

            courseReviewRepository.delete(entity);
        }
        else{
            System.out.println("delete error");
        }
    }


}
