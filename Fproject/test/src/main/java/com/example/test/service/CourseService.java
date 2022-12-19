package com.example.test.service;

import com.example.test.domain.CourseDTO;
import com.example.test.domain.CourseListDTO;
import com.example.test.domain.MainBoardDTO;
import com.example.test.entity.*;
import com.example.test.repository.*;
import com.google.common.base.StandardSystemProperty;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

@Service
@RequiredArgsConstructor
public class CourseService {
    @Autowired
    private CourseDAO courseDAO;
    @Autowired
    private MainBoardDAO mainBoardDAO;

    private final CourseDataRepository courseDataRepository;

    private final CourseRepository courseRepository;

    private final CourseListRepositroy courseListRepositroy;

    private final MemberRepository memberRepository;
    private final MemberService memberService;
    private ModelMapper modelMapper = new ModelMapper();




    public ArrayList<CourseDTO> toCDTO(String[] str) {
        ArrayList<CourseDTO> cdtos = new ArrayList<>();
        CourseDTO cdto = new CourseDTO();
        int order = 1;

        for (String s : str) {
            String[] data = s.split("\\|");
            cdto.setOrder(order++);
            cdto.setCategory(data[0]);
            cdto.setAddress_id(data[1]);
            cdto.setAddress_name(data[2]);
            cdto.setRoad_address_name(data[3]);
            cdto.setPhone_number(data[4]);
            cdto.setPlace_name(data[5]);
            cdto.setPlace_url(data[6]);
            cdto.setX(data[7]);
            cdto.setY(data[8]);
            if(data.length==10) {
                cdto.setMemo(data[9]);

            }

            cdtos.add(cdto);

            cdto = new CourseDTO();
        }

        return cdtos;
    }


    public String findIdNickName(int course_id) {
        String nickname=mainBoardDAO.getNickname(course_id);
        return nickname;
    }


    public MainBoardDTO findIdPostRegion(int course_id) {
        MainBoardDTO mdto=mainBoardDAO.getMainboard(course_id);
        return mdto;
    }


    public ArrayList<CourseDTO> getIdCourse(int course_id) {
        ArrayList<CourseDTO> cdtos=courseDAO.getCourse(course_id);
        return cdtos;
    }


    public List<Map<String, String>> printCourse(ArrayList<CourseDTO> cdtos) {
        List<Map<String, String>> list=new Vector<Map<String,String>>();

        for(int i=0; i<cdtos.size();i++) {
            Map<String, String> map= new HashMap<>();
            map.put("address_id",cdtos.get(i).getAddress_id());
            map.put("address_name",cdtos.get(i).getAddress_name());
            map.put("category",cdtos.get(i).getCategory());
            map.put("Course_id",cdtos.get(i).getCourse_id());
            map.put("Memo",cdtos.get(i).getMemo());
            map.put("Phone_number",cdtos.get(i).getPhone_number());
            map.put("Place_name",cdtos.get(i).getPlace_name());
            map.put("Place_url",cdtos.get(i).getPlace_url());
            map.put("Road_address_name",cdtos.get(i).getRoad_address_name());
            map.put("X",cdtos.get(i).getX());
            map.put("Y",cdtos.get(i).getY());

            list.add(map);

        }

        return list;
    }


    public MainBoardDTO editMode(HttpServletRequest req) {

        String[] str = req.getParameterValues("data");
        int course_id=Integer.parseInt(req.getParameter("c_id")) ;
        ArrayList<CourseDTO> cdtos = this.toCDTO(str);
        courseDAO.updateCourse(course_id, cdtos);

        MainBoardDTO mdto=mainBoardDAO.getMainboard(course_id);

        return mdto;
    }


    public MainBoardDTO writeMode(HttpServletRequest req) {
        String[] str = req.getParameterValues("data");
        System.out.println(str);
        ArrayList<CourseDTO> cdtos = this.toCDTO(str);

        Long course_id = this.makeCourse();
        int index = 1;
        for(CourseDTO dto : cdtos) {
            System.out.println(dto.toString());
            this.saveCourse(dto, course_id, index++);
        }

//        MainBoardDTO mdto = new MainBoardDTO();

//        mdto.setCourse_id(Long.toString(course_id));

        String title = req.getParameter("title");
        String region = req.getParameter("region");
        String nickname = req.getParameter("nickname");

        if (title != null && title.equals(""))
            title = nickname + "_" + cdtos.get(0).getPlace_name();
        if (region.equals("없음")) {
            String[] reg = cdtos.get(0).getAddress_name().split("\\s");
            region = reg[0];
            if(region.equals("세종특별자치시"))
                region = "세종";
            if(region.equals("제주특별자치도"))
                region = "제주";
        }
//        mdto.setTitle(title);
//        mdto.setRegion(region);

        //임시
//        Map<String,Object> map = new HashMap<String,Object >();
//        map.put("mdto",mdto);
//        map.put("user_id",1); //임시값, 나중에 session에서 유저id 가져와야함
//        mainBoardDAO.insertWrite(map);
        CourseList courseList = new CourseList();
        courseList.setCourse_id(course_id);
        courseList.setTitle(title);
        courseList.setRegion(region);
        System.out.println(courseList.toString());
        courseListRepositroy.save(courseList);

//        return mdto;
        return null;
    }


    public void insertCourseS(ArrayList<CourseDTO> cdtos,int course_id) {

        for(CourseDTO cdto:cdtos){
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("cdto",cdto );
            map.put("id",course_id);
            courseDAO.insertCourseData(map);

        }

    }


    public List<Map<String, String>> giveCourse(HttpServletRequest req) {
        List<Map<String, String>> list=new Vector<Map<String,String>>();
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
            if(data.length==10) {
                map.put("Memo",data[9]);
            }else {
                map.put("Memo","");
            }
            list.add(map);

        }
        return list;
    }
    //===================

    public void loginCheck(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if(session.getAttribute("UserId")!=null) {
            req.getRequestDispatcher("/saii/course/courseWritePage.jsp").forward(req, resp);
        }else {
//            AlertFunc.alertLocation(resp, "작성하시려면 로그인을 해주세요", "http://localhost:8081/SAII/login");
        }
    }

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

//    public MainBoardDTO saveMainboard(HttpServletRequest req) {
//
//        int c_id=Integer.parseInt(req.getParameter("c_id"));
//        MainBoardDTO mdto = mainBoardDAO.getMainboard(c_id);
//
//        return mdto;
//    }

    public void saveCourse(CourseDTO cdto, long course_id, long course_order){
        CourseData courseData = modelMapper.map(cdto, CourseData.class);
        courseData.setId(course_id);
        courseData.setOrder(course_order);
        System.out.println(courseData.toString());
        courseDataRepository.save(courseData);
    }

    public long makeCourse(){
        Course course = new Course();
        courseRepository.save(course);
        return course.getId();
    }

    public List<CourseListDTO> getList(){
        List<CourseList> courseLists = courseListRepositroy.findAll();
        List<CourseListDTO> lists = new ArrayList<>();
        int order = 1;
        for(CourseList courseList : courseLists){
            CourseListDTO cdto = modelMapper.map(courseList, CourseListDTO.class);
            int length = courseDataRepository.countById(cdto.getCourse_id());
            List<CourseDTO> datas = new ArrayList<>();
            for(int i=0; i < length;i++) {
                CourseDataId id = new CourseDataId();
                id.setId(cdto.getCourse_id());
                id.setOrder((long) i+1);
                datas.add(modelMapper.map(courseDataRepository.findById(id), CourseDTO.class));
            }
            cdto.setCourseDatas(datas);
            cdto.setCenter();
            long id = Long.parseLong(cdto.getCreatedBy());
            System.out.println(id);
            cdto.setCreatedBy(memberService.getMember(id).getNickname());
            lists.add(cdto);
        }
        return lists;
    }
}
