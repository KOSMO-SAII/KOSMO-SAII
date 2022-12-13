package com.example.test.controller;

import com.example.test.service.MemberService;
import com.example.test.service.ReviewCourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@RequiredArgsConstructor
@Controller
public class IndexController {//페이지에 관련된 컨트롤러
    public final ReviewCourseService reviewCourseService;

    @GetMapping("/index")
    public String index(Model model, Principal principal){
        model.addAttribute("courseReview", reviewCourseService.findAllDesc());
        if(principal != null){
            model.addAttribute("name",principal.getName());
        }
        return "index";
    }
}
