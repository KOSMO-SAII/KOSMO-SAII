package com.example.test.service;

import com.example.test.domain.CourseDTO;
import com.example.test.domain.MainBoardDTO;
import com.example.test.entity.Course;
import com.example.test.entity.CourseData;
import com.example.test.repository.CourseDAO;
import com.example.test.repository.CourseDataRepository;
import com.example.test.repository.CourseRepository;
import com.example.test.repository.MainBoardDAO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

@Service
@RequiredArgsConstructor
public class CourseWriteServiceImple extends HttpServlet implements CourseWriteService {

    private CourseDAO courseDAO;

    private MainBoardDAO mainBoardDAO;

    private CourseDataRepository courseDataRepository;

    private CourseRepository courseRepository;

    private ModelMapper modelMapper;
//    @Autowired
//    public courseWriteServiceImple(courseDAO courseDAO, mainBoardDAO mainBoardDAO){
//        this.mainBoardDAO=mainBoardDAO;
//        this.courseDAO=courseDAO;
//    }

    @Override
    public void loginCheck(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if(session.getAttribute("UserId")!=null) {
            req.getRequestDispatcher("/saii/course/courseWritePage.jsp").forward(req, resp);
        }else {
//            AlertFunc.alertLocation(resp, "작성하시려면 로그인을 해주세요", "http://localhost:8081/SAII/login");
        }
    }

    @Override
    public List<Map<String, String>> changeCourse( HttpServletRequest req) {
        List<Map<String, String>> list=new Vector<>();
        String[] datas = req.getParameterValues("data");

        for(int k=0; k<datas.length;k++) {
            String[] data =  datas[k].split("\\|");

            Map<String, String> map= new HashMap<>();
            map.put("category",data[0]);
            map.put("address_id",data[1]);
            map.put("address_name",data[2]);
            map.put("Road_address_name",data[3]);
            map.put("Phone_number",data[4]);
            map.put("Place_name",data[5]);
            map.put("Place_url",data[6]);
            map.put("X",data[7]);
            map.put("Y",data[8]);
            if(data.length == 10) {
                map.put("Memo",data[9]);
            }else {
                map.put("Memo","");
            }
            list.add(map);

        }
        return list;
    }

    @Override
    public MainBoardDTO saveMainboard(HttpServletRequest req) {

        int c_id=Integer.parseInt(req.getParameter("c_id"));
        MainBoardDTO mdto = mainBoardDAO.getMainboard(c_id);

        return mdto;
    }

    public void saveCourse(CourseDTO cdto, Long course_id){
        CourseData courseData = modelMapper.map(cdto, CourseData.class);
        courseData.setCourse_id(course_id);
        courseDataRepository.save(courseData);
    }

    public long makeCourse(){
        Course course = new Course();
        courseRepository.save(course);
        return course.getId();
    }
}
