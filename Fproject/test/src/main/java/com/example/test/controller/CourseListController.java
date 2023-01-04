package com.example.test.controller;

import com.example.test.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.net.http.HttpRequest;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class CourseListController {

    @Autowired
    private CourseService courseService;

//    @GetMapping("course/list")
//    public String list(Optional<Integer> page, Model model, HttpServletRequest request){
//        PageRequest pageRequest = PageRequest.of(page.isPresent() ? page.get() : 0,3);
//        model.addAttribute("lists", courseService.getList("", pageRequest));
//        model.addAttribute("pages", courseService.getPage("", pageRequest));
//        model.addAttribute("search", "");
//        model.addAttribute("maxPage", 5);
//
//        return "course/courseList";
//    }

    @RequestMapping("course/list")
    public String search(Optional<Integer> page, Model model, HttpServletRequest request){
        PageRequest pageRequest = PageRequest.of(page.isPresent() ? page.get() : 0,3);
        String searchStr = request.getParameter("searchStr");
        if(searchStr == null){
            searchStr = "";
        }
        model.addAttribute("lists", courseService.getList(searchStr, pageRequest));
        model.addAttribute("pages", courseService.getPage(searchStr, pageRequest));
        model.addAttribute("search", searchStr);
        model.addAttribute("maxPage", 5);

        return "course/courseList";
    }
}
