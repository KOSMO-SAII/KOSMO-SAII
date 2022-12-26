package com.example.test.controller;

import com.example.test.config.LoginUser;
import com.example.test.config.SessionMember;
import com.example.test.domain.MemberDTO;
import com.example.test.domain.ReviewCommentResponseDTO;
import com.example.test.domain.ReviewCourseListResponseDTO;
import com.example.test.domain.ReviewCourseResponseDTO;
import com.example.test.entity.Member;
import com.example.test.repository.MemberRepository;
import com.example.test.service.MemberService;
import com.example.test.service.ReviewCourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.mail.Session;
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
        ReviewCourseResponseDTO dto = reviewCourseService.findById(id);
        model.addAttribute("reviewCourse",dto);
        return "reviews/reviewCourseUpdate";
    }
    //수정
    @GetMapping("/reviews/detail/{id}")
    public String reviewDetail(@PathVariable Long id, Principal principal, Model model) {
        ReviewCourseResponseDTO dto = reviewCourseService.findById(id);
        Member member = memberRepository.findByNickname(dto.getAuthor());
        String loginId = member.getLoginId();
        //로그인 아이디 비교하려고 함.
        // 리뷰 테이블에는 닉네임값만 있음. 닉네임으로 멤버 정보 가져와서 아이디 값 가져오기. principal.getName()과 가져온 아이디 값 비교하기.


        // 댓글
        List<ReviewCommentResponseDTO> comments = dto.getComments();
            model.addAttribute("comments",comments);


        // 사용자
        if(principal != null){
            model.addAttribute("name", principal.getName());

            //리뷰 작성자가 본인인지 확인
            if(dto.getAuthor().equals(memberService.getMember().getLoginId())){
                model.addAttribute("author",true);

            }
            if(principal.getName().equals(loginId)){
                model.addAttribute("check",true);
            }
            System.out.println(principal.getName()+"=========");
            System.out.println(loginId+"********");

        }

        model.addAttribute("reviewCourse",dto);
        return "reviews/reviewCourseDetail";
    }
//    @GetMapping("/reviews/detail/{id}")
//    public String reviewDetail(@PathVariable Long id,  Model model) {
//        ReviewCourseResponseDTO dto = reviewCourseService.findById(id);
//        model.addAttribute("reviewCourse",dto);
//        return "reviews/reviewCourseDetail";
//    }
    //조회
    @GetMapping("/reviews/search")
    public String search(@RequestParam(value="searchQuery") String keyword, Model model) {
        List<ReviewCourseListResponseDTO> dto = reviewCourseService.findByKeyword(keyword);
        model.addAttribute("reviewCourse", dto);
        return "/reviews/reviewCourse";
    }
    //검색

}
