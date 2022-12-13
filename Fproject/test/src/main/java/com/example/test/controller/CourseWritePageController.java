package com.example.test.controller;

import com.example.test.domain.MainBoardDTO;
import com.example.test.service.CourseWriteServiceImple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
public class CourseWritePageController extends HttpServlet {

    @Autowired
    private CourseWriteServiceImple courseWriteServiceImple;


    @GetMapping("/courseWritePage")
    public String doGet(Model model, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        courseWriteServiceImple.loginCheck(req,resp);
        return "course/courseWritePage";
    }

    @PostMapping("/courseWritePage")
    public String doPost(Model model, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException  {
        List<Map<String, String>> list = courseWriteServiceImple.changeCourse(req);

        MainBoardDTO mdto =courseWriteServiceImple.saveMainboard(req);

        req.setAttribute("title", mdto.getTitle());
        req.setAttribute("region",mdto.getRegion());
        req.setAttribute("list", list);

        return "course/courseWritePage";
    }
}
