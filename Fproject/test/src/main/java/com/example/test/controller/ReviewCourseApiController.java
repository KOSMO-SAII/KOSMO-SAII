package com.example.test.controller;

import com.example.test.domain.ReviewCourseDTO;
import com.example.test.service.ReviewCourseService;
import org.springframework.web.bind.annotation.*;


import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class ReviewCourseApiController {

/**	REST API 설계 시 가장 중요한 항목은 다음의 2가지로 요약할 수 있다.
*
*	1.Resource(자원) : URI는 정보의 자원을 표현. 동사형보다는 명사형으로 사용.
*	2.자원에 대한 행위 : HTTP Method(GET, POST, PUT, DELETE)로 표현한다(CRUD).
*	POST 리소스 생성/ GET 리소스 조회/ PUT 리소스 수정/ DELETE 리소스 삭제.
*	'/'는 계층 관계, '-'은 URI 가독성 높임.
*	리소스 간 연간관계 : /리소스명/{리소스id}/연관리소스명
*	ex) 사용자가 가지고 있는 디바이스  GET: /users/{userid}/devices
*	ex) 사용자가 좋아하는 디바이스 목록 GET: /users/{userid}/likes/devices
*/


	private final ReviewCourseService reviewCourseService;
	
	@PostMapping("/reviews")
	public Long save(@RequestBody ReviewCourseDTO requestDto) {
		//RequestBody, ResponseBody - 데이터 비동기 처리 하여 값 받음
		System.out.println(requestDto);
		return reviewCourseService.save(requestDto);
	}
	
	@PutMapping("/reviews/{id}")
	public Long update(@PathVariable Long id, @RequestBody ReviewCourseDTO requestDto) {
		// PutMapping 수정할때 사용
		// PathVariable 파라미터값 사용 
		return reviewCourseService.update(id, requestDto);
	}
	
	@GetMapping("/reviews/{id}")
	public ReviewCourseDTO findById(@PathVariable Long id) {
		//GetMapping 조회할때 사용
		return reviewCourseService.findById(id);
	}


	@DeleteMapping("/reviews/{id}")
	public Long delete(@PathVariable Long id) {
		reviewCourseService.delete(id);
		return id;
	}

//	@GetMapping("/reviews/{id}")
//	public String read(@PathVariable Long id, Model model){
//		ReviewCourseResponseDTO responseDto = reviewCourseService.findById(id);
//		reviewCourseService.updateView(id);
//		model.addAttribute("reviewCourse", responseDto);
//
//		return "reviewCourse";
//	}

}
