package com.example.test.controller;


import com.example.test.entity.CourseList;
import com.example.test.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
public class CoursePageController extends HttpServlet {

    @Autowired
    private CourseService courseService;

      @PostMapping("/courseDelete")
      public String courseDelete(Optional<Integer> page,Model model, HttpServletRequest req, HttpServletResponse resp, Principal principal){
          Long c_id =Long.parseLong(req.getParameter("c_id"));
          courseService.deleteCourse(c_id);

          PageRequest pageRequest = PageRequest.of(page.isPresent() ? page.get() : 0,3);
          String searchStr = req.getParameter("searchStr");
          if(searchStr == null){
              searchStr = "";
          }
          model.addAttribute("lists", courseService.getList(searchStr, pageRequest));
          model.addAttribute("pages", courseService.getPage(searchStr, pageRequest));
          model.addAttribute("search", searchStr);
          model.addAttribute("maxPage", 5);
          return "course/courseList";
      }

      @GetMapping("/courseViewPage/{num}")
    public String doGetView(@PathVariable("num") String num, Model model, HttpServletRequest req, HttpServletResponse resp, Principal principal) throws ServletException, IOException {
        Long course_id= Long.parseLong(num);
        String mode="view";

        List<CourseList> courseList = courseService.getCourseList(course_id,mode);
        Map<String,String> map = courseService.getDays(course_id);
        //코스 id에 맞는 작성자 찾기
        String createdBy = courseList.get(0).getCreatedBy();
          String nowUser;
        try {
             nowUser = principal.getName();

        }catch (NullPointerException e){
              nowUser="Anonymous";

            String region = courseList.get(0).getRegion();
            String title = courseList.get(0).getTitle();

            //받아온 코스 id로 db에서 값 뽑아옴
            List<Map<String, String>> list=courseService.giveCourseData(course_id);
            System.out.println(list.toString());


            req.setAttribute("title", title);
            req.setAttribute("region",region);
            req.setAttribute("c_id", course_id);
            req.setAttribute("createdBy", createdBy);
            req.setAttribute("list", list);
            req.setAttribute("days", map.get("days"));
            req.setAttribute("start",map.get("start"));
            req.setAttribute("nowUser",nowUser);
            req.setAttribute("end",map.get("end"));
            req.setAttribute("mode",mode);

            //return "test/test";
            return "course/courseViewPage";
        }

        //코스 id에 맞는 글 제목,지역 찾기
        String region = courseList.get(0).getRegion();
        String title = courseList.get(0).getTitle();

        //받아온 코스 id로 db에서 값 뽑아옴
        List<Map<String, String>> list=courseService.giveCourseData(course_id);
        System.out.println(list.toString());


        req.setAttribute("title", title);
        req.setAttribute("region",region);
        req.setAttribute("c_id", course_id);
        req.setAttribute("createdBy", createdBy);
        req.setAttribute("list", list);
        req.setAttribute("days", map.get("days"));
        req.setAttribute("start",map.get("start"));
        req.setAttribute("nowUser",nowUser);
        req.setAttribute("end",map.get("end"));
        req.setAttribute("mode",mode);
        //return "test/test";
        return "course/courseViewPage";
    }


    @PostMapping("/courseViewPage")
    public String doPostView(Model model, HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException{


        String mode=req.getParameter("mode");
        String days=req.getParameter("days");
        String start=req.getParameter("start");
        String end=req.getParameter("end");

        //courseWrite페이지에서 수정모드로 넘어온 값이 있을 시 db수정
        if(mode.equals( "edit")) {
            System.out.println("edit모드");
            String title=req.getParameter("title");
            String region=req.getParameter("region");

            List<Map<String, String>> list=courseService.editMode(req);
            
            req.setAttribute("title", title);
            req.setAttribute("region", region);
            req.setAttribute("list", list);
            System.out.println(list);

        }else if(mode.equals( "write")) {

            //courseWrite페이지에서 작성모드로 넘어온 값이 있을 시 db저장
            if(req.getParameterValues("data")!=null) {
                List<Map<String, String>> list=courseService.writeMode(req);

                String title = req.getParameter("title");
                String region = req.getParameter("region");
                //String nickname = req.getParameter("nickname");
                //req.setAttribute("nickname", nickname);
                req.setAttribute("title", title);
                req.setAttribute("region", region);
                req.setAttribute("list", list);
                System.out.println(list);
            }
        }
        req.setAttribute("days",days);
        req.setAttribute("start",start);
        req.setAttribute("end",end);
        req.setAttribute("mode",mode);
        return "course/CourseViewPage";

    }

    @GetMapping("/courseWritePage")
    public String doGetWrite(Model model, HttpServletRequest req, HttpServletResponse resp, Principal principal) throws ServletException, IOException {
        boolean check=courseService.loginCheck(principal);
        System.out.println(check);
        if(check){
            req.setAttribute("mode","write");
            return "course/courseWritePage";
        }else {


            return "redirect:/members/login";
        }

    }

    @PostMapping("/courseWritePage")
    public String doPostWrite(Model model, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException  {
        Long course_id= Long.parseLong(req.getParameter("c_id"));
        String mode= req.getParameter("mode");
        List<CourseList> courseList = courseService.getCourseList(course_id,mode);
        System.out.println("코스아이디: "+course_id);
        List<Map<String, String>> list = courseService.changeCourseData(course_id);
        Map<String,String> map =courseService.getDays(course_id);
        //System.out.println("days: "+days);


        req.setAttribute("days",map.get("days"));
        req.setAttribute("title", courseList.get(0).getTitle());
        req.setAttribute("region",courseList.get(0).getRegion());
        req.setAttribute("list", list);
        req.setAttribute("start",map.get("start"));
        req.setAttribute("end",map.get("end"));
        req.setAttribute("mode",mode);

        return "course/courseWritePage";
    }
}
