package com.example.test.controller;

import com.example.test.entity.Member;
import com.example.test.service.MemberService;
import com.example.test.service.ReviewCourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.Optional;

@RequiredArgsConstructor
@Controller
public class ReviewCourseController {

    public final ReviewCourseService reviewCourseService;

    public final MemberService memberService;

    @RequestMapping("/reviews/course")
    public String index(Optional<Integer> page, Model model, Principal principal){
        Member member= memberService.getMember();
        PageRequest pageRequest = PageRequest.of(page.isPresent() ? page.get() : 0,12);

        model.addAttribute("reviewCourse",reviewCourseService.findAllDesc(pageRequest));
        model.addAttribute("maxPage", 5);
//        model.addAttribute("sessionId",principal.getName());

        if(principal != null){
            System.out.println(member.getNickname());
            model.addAttribute("nickname",member.getNickname());
        }
        return "reviews/reviewCourse";
    }




}

