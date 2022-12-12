//package com.example.test.controller;
//
//import com.example.test.entity.CourseReview;
//import com.example.test.service.ReviewCourseService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//
//@RequiredArgsConstructor
//@Controller
//public class IndexController {//페이지에 관련된 컨트롤러
//    private final ReviewCourseService reviewCourseService;
//
//    @GetMapping("/index")
//    public String index(Model model){
//        model.addAttribute("courseReview", reviewCourseService.findAllDesc());
//        return "index";
//        //
//    }
//}
