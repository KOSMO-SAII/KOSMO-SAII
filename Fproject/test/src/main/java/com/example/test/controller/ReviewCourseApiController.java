package com.example.test.controller;

import com.example.test.domain.ReviewCourseResponseDTO;
import com.example.test.domain.ReviewCourseSaveRequestDTO;
import com.example.test.domain.ReviewCourseUpdateRequestDTO;
import com.example.test.service.ReviewCourseService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class ReviewCourseApiController {
	private final ReviewCourseService reviewCourseService;
	
	@PostMapping("/api/v1/review")
	public Long save(@RequestBody ReviewCourseSaveRequestDTO requestDto) {
		//RequestBody, ResponceBody - 데이터 비동기 처리 하여 값 받음 
		return reviewCourseService.save(requestDto);
	}
	
	@PutMapping("/api/v1/review/{id}")
	public Long update(@PathVariable Long id, @RequestBody ReviewCourseUpdateRequestDTO requestDto) {
		// PutMapping수정할때 사용
		// PathVariable 파라미터값 사용 
		return reviewCourseService.update(id, requestDto);
	}
	
	@GetMapping("/api/v1/review/{id}")
	public ReviewCourseResponseDTO findById(@PathVariable Long id) {
		//GetMapping 조회할때 사용 
		return reviewCourseService.findById(id);
	}
	
	@DeleteMapping("api/v1/review/{id}")
	public Long delete(@PathVariable Long id) {
		reviewCourseService.delete(id);
		return id;
	}
}
