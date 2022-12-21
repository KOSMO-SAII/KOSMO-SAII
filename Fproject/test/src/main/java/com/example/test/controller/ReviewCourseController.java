package com.example.test.controller;

import com.example.test.entity.Member;
import com.example.test.service.MemberService;
import com.example.test.service.ReviewCourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@RequiredArgsConstructor
@Controller
public class ReviewCourseController {

    public final ReviewCourseService reviewCourseService;

    public final MemberService memberService;

    @RequestMapping("/course-reviews")
    public String index(Model model, Principal principal){
        model.addAttribute("reviewCourse",reviewCourseService.findAllDesc());
        Member member= memberService.getMember();

        if(principal != null){
            System.out.println(member.getNickname());
            model.addAttribute("nickname",member.getNickname());
        }
        return "reviews/reviewCourse";
    }




}

