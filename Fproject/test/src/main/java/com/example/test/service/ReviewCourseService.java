package com.example.test.service;

import com.example.test.domain.ReviewCourseListResponseDTO;
import com.example.test.domain.ReviewCourseResponseDTO;
import com.example.test.domain.ReviewCourseSaveRequestDTO;
import com.example.test.domain.ReviewCourseUpdateRequestDTO;
import com.example.test.entity.CourseReview;
import com.example.test.repository.ReviewCourseRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor    //final이 선언된 모든 필드를 인자값으로 생성자 생성해줌.
@Service
public class ReviewCourseService {

    private final ReviewCourseRepository reviewCourseRepository;

    private ModelMapper modelMapper = new ModelMapper();
    @Transactional
    public Long save(ReviewCourseSaveRequestDTO requestDTO){
        return reviewCourseRepository.save(requestDTO.toCourseReview()).getId();
    }

    @Transactional
    public Long update(Long id, ReviewCourseUpdateRequestDTO requestDTO){
        CourseReview reviewCourse = reviewCourseRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다. id="+id));
        reviewCourse.update(requestDTO.getTitle(), requestDTO.getContent(), requestDTO.getCourse_id());
        System.out.println(requestDTO.getCourse_id());
        return id;
    }

    public ReviewCourseResponseDTO findById(Long id){
        CourseReview reviewCourse = reviewCourseRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다. id="+id));
        return new ReviewCourseResponseDTO(reviewCourse);
    }

    @Transactional
    public Page<ReviewCourseListResponseDTO> findAllDesc(PageRequest pageRequest){

        Page<ReviewCourseListResponseDTO> courseReviewList = reviewCourseRepository.findAllDesc(pageRequest);

        return courseReviewList;
    }
    //reviewCourseRepository 결과로 넘어온 CourseReviewList를 Page로 래핑하고 map을 통해
    // ReviewCourseListResponseDTO로 변환해 반환하는 메소드.

    @Transactional
    public void delete(Long id){
        CourseReview reviewCourse = reviewCourseRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다. id="+id));
        reviewCourseRepository.delete(reviewCourse);
    }

    @Transactional
    public List<ReviewCourseListResponseDTO> findByKeyword(String title){
        return reviewCourseRepository.findByTitleContaining(title).stream()
                .map(reviewCourse -> new ReviewCourseListResponseDTO(reviewCourse))
                .collect(Collectors.toList());
    }

//    @Transactional
//    public int updateView(Long id){
//        return reviewCourseRepository.updateView(id);
//    }

}
