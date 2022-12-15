package com.example.test.controller;


import com.example.test.domain.CourseDTO;
import com.example.test.domain.MainBoardDTO;
import com.example.test.service.CourseService;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class CoursePageController extends HttpServlet {

    @Autowired
    private CourseService courseService;

      @GetMapping("/courseViewPage")
    public String doGetView(Model model, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int course_id= Integer.parseInt( req.getParameter("num"));
        //코스 id에 맞는 닉네임 찾기
        String nickname = courseService.findIdNickName(course_id);

        //코스 id에 맞는 글 제목,지역 찾기
        MainBoardDTO mdto=courseService.findIdPostRegion(course_id);

        //받아온 코스 id로 db에서 값 뽑아옴
        ArrayList<CourseDTO> cdtos=courseService.getIdCourse(course_id);

        //넘어온 코스 정보 list에 저장
        List<Map<String, String>> list = courseService.printCourse(cdtos);

        req.setAttribute("title", mdto.getTitle());
        req.setAttribute("region",mdto.getRegion());
        req.setAttribute("c_id", course_id);
        req.setAttribute("nickname", nickname);
        req.setAttribute("List", list);

        return "course/courseViewPage";
    }


    @PostMapping("/courseViewPage")
    public String doPostView(Model model, HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException{


        String mode=req.getParameter("mode");

        //courseWrite페이지에서 수정모드로 넘어온 값이 있을 시 db수정
        if(mode.equals( "edit")) {
            System.out.println("edit모드");
            MainBoardDTO mdto = courseService.editMode(req);
            
            req.setAttribute("title", mdto.getTitle());
            req.setAttribute("region", mdto.getRegion());

        }else {
            //courseWrite페이지에서 작성모드로 넘어온 값이 있을 시 db저장
            if(req.getParameterValues("data")!=null) {
                courseService.writeMode(req);

                String title = req.getParameter("title");
                String region = req.getParameter("region");
                String nickname = req.getParameter("nickname");
                req.setAttribute("nickname", nickname);
                req.setAttribute("title", title);
                req.setAttribute("region", region);
            }

        }
        List<Map<String, String>> list = courseService.giveCourse(req);
        System.out.println(list);

        req.setAttribute("List", list);

        return "course/courseViewPage";
    }

    @GetMapping("/courseWritePage")
    public String doGetWrite(Model model, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        courseService.loginCheck(req,resp);
        return "course/courseWritePage";
    }

    @PostMapping("/courseWritePage")
    public String doPostWrite(Model model, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException  {
        List<Map<String, String>> list = courseService.changeCourse(req);

        MainBoardDTO mdto =courseService.saveMainboard(req);

        req.setAttribute("title", mdto.getTitle());
        req.setAttribute("region",mdto.getRegion());
        req.setAttribute("list", list);

        return "course/courseWritePage";
    }
}
