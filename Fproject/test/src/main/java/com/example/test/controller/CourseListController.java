package com.example.test.controller;

import com.example.test.entity.CourseList;
import com.example.test.repository.CourseListRepositroy;
import com.example.test.service.CourseService;
import jnr.ffi.annotations.In;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.awt.print.Pageable;
import java.util.List;
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
