package com.example.test.controller;

import com.example.test.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class CourseListController {

    @Autowired
    private CourseService courseService;

    @RequestMapping("course/list")
        public String list(Optional<Integer> page, Model model){
            System.out.println("controller");

            PageRequest pageRequest = PageRequest.of(page.isPresent() ? page.get() : 0,3);

            model.addAttribute("lists", courseService.getList(pageRequest));
            model.addAttribute("maxPage", 5);

            return "course/courseList";
        }

}
