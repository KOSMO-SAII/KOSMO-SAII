package com.example.test.service;

import com.example.test.domain.ReviewCourseDTO;
import com.example.test.entity.ReviewCourse;
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
    public Long save(ReviewCourseDTO reviewCourseDTO){
        ReviewCourse reviewCourse = modelMapper.map(reviewCourseDTO, ReviewCourse.class);
        reviewCourseRepository.save(reviewCourse);
        return reviewCourse.getId();
    }

    @Transactional
    public Long update(Long id, ReviewCourseDTO requestDTO){
        ReviewCourse reviewCourse = reviewCourseRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다. id="+id));
        reviewCourse.update(requestDTO.getTitle(), requestDTO.getContent(), requestDTO.getCourse_id());
        System.out.println(requestDTO.getCourse_id());
        return id;
    }

    public ReviewCourseDTO findById(Long id){
        ReviewCourse reviewCourse = reviewCourseRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다. id="+id));
        ReviewCourseDTO dto = modelMapper.map(reviewCourse, ReviewCourseDTO.class);
        System.out.println(reviewCourse.toString());
        System.out.println(dto.toString());
        return dto;
    }

    @Transactional
    public Page<ReviewCourseDTO> findAllDesc(PageRequest pageRequest){

        Page<ReviewCourseDTO> courseReviewList = reviewCourseRepository.findAllDesc(pageRequest);

        return courseReviewList;
    }
    //reviewCourseRepository 결과로 넘어온 CourseReviewList를 Page로 래핑하고 map을 통해
    // ReviewCourseListResponseDTO로 변환해 반환하는 메소드.




    @Transactional
    public void delete(Long id){
        ReviewCourse reviewCourse = reviewCourseRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다. id="+id));
        reviewCourseRepository.delete(reviewCourse);
    }

    @Transactional
    public List<ReviewCourseDTO> findByKeyword(String title){
        return reviewCourseRepository.findByTitleContaining(title).stream()
                .map(reviewCourse -> new ReviewCourseDTO(reviewCourse))
                .collect(Collectors.toList());
    }

//    @Transactional
//    public int updateView(Long id){
//        return reviewCourseRepository.updateView(id);
//    }

}
