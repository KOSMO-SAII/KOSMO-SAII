package com.example.test.service;

import com.example.test.domain.ReviewCommentDTO;
import com.example.test.entity.ReviewCourse;
import com.example.test.entity.Member;
import com.example.test.entity.ReviewComment;
import com.example.test.repository.MemberRepository;
import com.example.test.repository.ReviewCommentRepository;
import com.example.test.repository.ReviewCourseRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class ReviewCommentService {
    private final ReviewCommentRepository reviewCommentRepository;
    private final ReviewCourseRepository reviewCourseRepository;

    private ModelMapper modelMapper = new ModelMapper();

    @Transactional
    public Long commentSave(ReviewCommentDTO dto){
        long id = dto.getPostId();
        System.out.println(dto.toString());
        ReviewCourse posts = reviewCourseRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("댓글 쓰기 실패: 해당 게시글이 존재하지 않습니다."+id));

        ReviewComment comment = modelMapper.map(dto, ReviewComment.class);
        System.out.println(comment.toString());
        reviewCommentRepository.save(comment);

        return  dto.getId();
    }
}
