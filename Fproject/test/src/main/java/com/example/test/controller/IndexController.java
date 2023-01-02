package com.example.test.controller;


import com.example.test.domain.ReviewCommentDTO;
import com.example.test.domain.ReviewCourseDTO;
import com.example.test.entity.Member;
import com.example.test.entity.ReviewComment;
import com.example.test.repository.MemberRepository;
import com.example.test.service.MemberService;
import com.example.test.service.ReviewCourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class IndexController {//페이지에 관련된 컨트롤러
    public final ReviewCourseService reviewCourseService;
    private final MemberService memberService;
    private final MemberRepository memberRepository;


    @GetMapping("/index")
    public String index(Model model, Principal principal,PageRequest pageRequest){
        model.addAttribute("reviewCourse", reviewCourseService.findAllDesc(pageRequest));
        if(principal != null){
            model.addAttribute("name",principal.getName());
        }
        return "index";
    }

    @GetMapping("/reviews/write")
    public String reviewWrite(Model model, Principal principal){

        Member member = memberService.getMember();
        model.addAttribute("nickname",member.getNickname());
        return "reviews/reviewCourseWrite";
    }
    //작성(저장)
    @GetMapping("/reviews/update/{id}")
    public String reviewUpdate(@PathVariable Long id, Model model){
        ReviewCourseDTO dto = reviewCourseService.findById(id);
        model.addAttribute("reviewCourse",dto);
        return "reviews/reviewCourseUpdate";
    }
    //수정
    @GetMapping("/reviews/detail/{id}")
    public String read(@PathVariable Long id, Principal principal, Model model) {
        ReviewCourseDTO dto = reviewCourseService.findById(id);
        System.out.println(id + " " + dto.toString());
        Member member = memberRepository.findByLoginId(dto.getMember().getLoginId());
        String loginId = member.getLoginId();
        model.addAttribute("nickname",member.getNickname());



        // 댓글
        List<ReviewCommentDTO> comments = reviewCourseService.getComments(id);
        if(comments!=null && !comments.isEmpty()){
            model.addAttribute("comments",comments);
        }

        if (principal == null) {
            model.addAttribute("toLogin", true); //비로그인 회원 로그인창으로
        }else{
            model.addAttribute("okLogin", true); //로그인 회원 기능
        }

        // 사용자
        if(principal != null){
            model.addAttribute("name", principal.getName());

            //리뷰 작성자가 본인인지 확인
            if(dto.getMember().getLoginId().equals(memberService.getMember().getLoginId())){
                model.addAttribute("writer",true);
                model.addAttribute("check",true);

            }
        }

        model.addAttribute("reviewCourse",dto);
        return "reviews/reviewCourseDetail";
    }
//    @GetMapping("/reviews/detail/{id}")
//    public String read(@PathVariable Long id,  Model model) {
//        ReviewCourseResponseDTO dto = reviewCourseService.findById(id);
//        model.addAttribute("reviewCourse",dto);
//        return "reviews/reviewCourseDetail";
//    }
    //조회
    @GetMapping("/reviews/search")
    public String search(@RequestParam(value="searchQuery") String keyword, Model model) {
        List<ReviewCourseDTO> dto = reviewCourseService.findByKeyword(keyword);
        model.addAttribute("reviewCourse", dto);
        return "/reviews/reviewCourse";
    }
    //검색

}
