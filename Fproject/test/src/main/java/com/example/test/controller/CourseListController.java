package com.example.test.controller;

import com.example.test.entity.CourseList;
import com.example.test.repository.CourseListRepositroy;
import com.example.test.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class CourseListController {

    @Autowired
    private CourseService courseService;

    @RequestMapping("course/list")
        public String list(Model model){

            model.addAttribute("lists",courseService.getList());

            return "course/courseList";
        }

}
