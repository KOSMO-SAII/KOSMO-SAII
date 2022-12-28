package com.example.test.service;

import com.example.test.domain.CourseDTO;
import com.example.test.domain.CourseListDTO;
import com.example.test.domain.MainBoardDTO;
import com.example.test.entity.Course;
import com.example.test.entity.CourseData;
import com.example.test.entity.CourseDataId;
import com.example.test.entity.CourseList;
import com.example.test.repository.*;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.Principal;
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

    private final CourseListRepository courseListRepository;

    private ModelMapper modelMapper = new ModelMapper();




    public ArrayList<CourseDTO> toCDTO(String[] str,int day) {
        ArrayList<CourseDTO> cdtos = new ArrayList<>();
        CourseDTO cdto = new CourseDTO();
        int order= 1;

        int[] date= new int[day];
        for(int i=0;i<date.length;i++){
            date[i]=0;
        }

        for(int i=0;i<str.length;i++){
            System.out.println("스플릿 이전");
            System.out.println(str[i]);

        }

        for (String s : str) {
            String[] data = s.split("\\|");
            int dayN=Integer.parseInt(data[9]);
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
            cdto.setDay(data[9]);
            if(data.length==11) {
                cdto.setMemo(data[10]);
            }
            System.out.println(date);
            System.out.println(date[dayN-1]);
            cdto.setCorder(date[dayN-1]++);

            cdtos.add(cdto);

            cdto = new CourseDTO();
        }

//        for(int i=0;i<cdtos.size();i++){
//            System.out.println("스플릿 이후");
//            System.out.println(cdtos.get(i));
//
//        }

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
            map.put("Course_id",cdtos.get(i).getId());
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
        ArrayList<CourseDTO> cdtos = this.toCDTO(str,2);
        courseDAO.updateCourse(course_id, cdtos);

        MainBoardDTO mdto=mainBoardDAO.getMainboard(course_id);

        return mdto;
    }


    public List<Map<String, String>> writeMode(HttpServletRequest req) {
        String[] str = req.getParameterValues("data");
        int day= Integer.parseInt(req.getParameter("days"));
        ArrayList<CourseDTO> cdtos = this.toCDTO(str,day);


        Long course_id = this.makeCourse(req);
        for(CourseDTO dto : cdtos) {
            //System.out.println("db저장 : "+dto.getPlace_name());
            this.saveCourse(dto, course_id);
        }

        MainBoardDTO mdto = new MainBoardDTO();

        mdto.setCourse_id(Long.toString(course_id));

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
        mdto.setTitle(title);
        mdto.setRegion(region);

        mainBoardDAO.insertWrite(mdto);

        List<Map<String, String>> list =this.giveCourse(course_id);

        return list;
    }


    public void insertCourseS(ArrayList<CourseDTO> cdtos,int course_id) {

        for(CourseDTO cdto:cdtos){
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("cdto",cdto );
            map.put("id",course_id);
            courseDAO.insertCourseData(map);

        }

    }


    public List<Map<String, String>> giveCourse(Long course_id) {
        List<Map<String, String>> list=new Vector<Map<String,String>>();

        CourseDataId courseData =new CourseDataId();

        int count = courseDataRepository.countById(course_id);

        List<CourseData> li =new Vector<CourseData>();
        for(long i=1;i<=count;i++){
            courseData.setId(course_id);
            courseData.setOrder(i);
            CourseData ot=courseDataRepository.findById(courseData).orElseThrow(()->new NoSuchElementException("값 없음"));
            li.add(ot);
        }

        for(int j=0;j<li.size();j++) {
            Map<String, String> map= new HashMap<>();
            map.put("category",li.get(j).getCategory());
            map.put("address_id",li.get(j).getAddress_id());
            map.put("address_name",li.get(j).getAddress_name());
            map.put("Road_address_name",li.get(j).getRoad_address_name());
            map.put("Phone_number",li.get(j).getPhone_number());
            map.put("Place_name",li.get(j).getPlace_name());
            map.put("Place_url",li.get(j).getPlace_url());
            map.put("X",li.get(j).getX());
            map.put("Y",li.get(j).getY());
            map.put("day",li.get(j).getDay());
            map.put("Memo",li.get(j).getMemo());
            map.put("corder",String.valueOf(li.get(j).getCorder()));

            list.add(map);
        }
//        String[] datas = req.getParameterValues("data");
//
//
//        for(int k=0; k<datas.length;k++) {
//            String[] data =  datas[k].split("\\|");
//
//
//            Map<String, String> map= new HashMap<>();
//            map.put("category",data[0]);
//            map.put("address_id",data[1]);
//            map.put("address_name",data[2]);
//            map.put("Road_address_name",data[3]);
//            map.put("Phone_number",data[4]);
//            map.put("Place_name",data[5]);
//            map.put("Place_url",data[6]);
//            map.put("X",data[7]);
//            map.put("Y",data[8]);
//            map.put("day",data[9]);
//            if(data.length==11) {
//                map.put("Memo",data[10]);
//            }else {
//                map.put("Memo","");
//            }
//            list.add(map);

//        }
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

    public MainBoardDTO saveMainboard(HttpServletRequest req) {
        int c_id=Integer.parseInt(req.getParameter("c_id"));
        MainBoardDTO mdto = mainBoardDAO.getMainboard(c_id);

        return mdto;
    }

    public void saveCourse(CourseDTO cdto, Long course_id){
        CourseData courseData = modelMapper.map(cdto, CourseData.class);
        courseData.setId(course_id);
        courseDataRepository.save(courseData);
    }

    public Long makeCourse(HttpServletRequest req){
        Course course = new Course();
        course.setDays(req.getParameter("days"));
        courseRepository.save(course);
        return course.getId();
    }

    public Page<CourseListDTO> getList(PageRequest pageRequest){

        Page<CourseList> courseLists = courseListRepository.findAll(pageRequest);

        for(CourseList courseList : courseLists){
            CourseListDTO cdto = modelMapper.map(courseList, CourseListDTO.class);
            System.out.println(cdto);
            int length = courseDataRepository.countById(cdto.getCourse_id());
            List<CourseDTO> datas = new ArrayList<>();
            for(int i=0; i < length;i++) {
                CourseDataId id = new CourseDataId();
                id.setId(cdto.getCourse_id());
                id.setOrder((long) i+1);
                System.out.println(courseDataRepository.findById(id));
                datas.add(modelMapper.map(courseDataRepository.findById(id), CourseDTO.class));
            }
            cdto.setCourseDatas(datas);
            cdto.setCenter();
            System.out.println("center = " + cdto.getXPoint() + "  " + cdto.getYPoint());
        }
        Page<CourseListDTO> lists = courseLists.map(courseList -> modelMapper.map(courseList, CourseListDTO.class));
        return lists;
    }
    public List<CourseListDTO> getCard(){
        List<CourseList> courseLists = courseListRepository.findTop3ByOrderByViewCountDesc();
        List<CourseListDTO> list = new ArrayList<>();

        for(CourseList c : courseLists){
            list.add(modelMapper.map(c, CourseListDTO.class));
        }

        return list;
    }
}
