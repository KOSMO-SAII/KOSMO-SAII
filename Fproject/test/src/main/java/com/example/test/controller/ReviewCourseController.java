package com.example.test.controller;

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

    @RequestMapping("/course-reviews")
    public String index(Model model, Principal principal){
        model.addAttribute("reviewCourse",reviewCourseService.findAllDesc());
        if(principal != null){
            model.addAttribute("name",principal.getName());
        }
        return "reviews/reviewCourse";
    }




}

