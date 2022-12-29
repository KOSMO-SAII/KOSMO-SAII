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

    private MemberService memberService;

    private ModelMapper modelMapper = new ModelMapper();




    public ArrayList<CourseDTO> toCDTO(String[] str,int day) {
        ArrayList<CourseDTO> cdtos = new ArrayList<>();
        CourseDTO cdto = new CourseDTO();
        int order= 1;

        int[] date= new int[day];
        for(int i=0;i<date.length;i++){
            date[i]=0;
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

            cdto.setCorder(date[dayN-1]++);

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


    public List<Map<String, String>> editMode(HttpServletRequest req) {

        String[] str = req.getParameterValues("data");
        int day= Integer.parseInt(req.getParameter("days"));
        ArrayList<CourseDTO> cdtos = this.toCDTO(str,day);

        Long course_id=Long.parseLong(req.getParameter("c_id"));

        this.deleteData(course_id);

        for(CourseDTO dto : cdtos) {
            this.saveCourseData(dto, course_id);
        }

        this.changeCourse(req,course_id);
        CourseListDTO cldt = new CourseListDTO();

        cldt.setCourseid(course_id);

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
        cldt.setTitle(title);
        cldt.setRegion(region);

        Long listId=this.getListId(course_id);
        this.changeCourseList(cldt,listId);

        List<Map<String, String>> list =this.giveCourseData(course_id);

        req.setAttribute("c_id",course_id);

        return list;
    }


    public List<Map<String, String>> writeMode(HttpServletRequest req) {
        String[] str = req.getParameterValues("data");
        int day= Integer.parseInt(req.getParameter("days"));
        ArrayList<CourseDTO> cdtos = this.toCDTO(str,day);


        Long course_id = this.makeCourse(req);
        for(CourseDTO dto : cdtos) {
            this.saveCourseData(dto, course_id);
        }

        CourseListDTO clto = new CourseListDTO();

        clto.setCourseid(course_id);

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
        clto.setTitle(title);
        clto.setRegion(region);

        this.saveCourseList(clto);

        List<Map<String, String>> list =this.giveCourseData(course_id);

        req.setAttribute("c_id",course_id);

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


    public List<Map<String, String>> giveCourseData(Long course_id) {
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
        return list;
    }
    //===================

    public boolean loginCheck(Principal principal) throws ServletException, IOException {
        if(principal==null){
            return false;
        }else{
            return  true;

        }

    }

    public List<Map<String, String>> changeCourseData(Long course_id) {
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
        return list;
    }

    public List<CourseList> getCourseList(Long course_id){
        System.out.println("getCourseList/course_id : "+course_id);
        List<CourseList> courseList= courseListRepository.findByCourseid(course_id);
        System.out.println("getCourseList: "+courseList);
        return courseList;
    }

    public void saveCourseData(CourseDTO cdto, Long course_id){
        System.out.println("saveCourseData 모달 전 "+cdto.getPlace_name());
        CourseData courseData = modelMapper.map(cdto, CourseData.class);
        courseData.setId(course_id);
        System.out.println("saveCourseData 모달 후 "+courseData.getPlace_name());
        courseDataRepository.save(courseData);
    }
    public void saveCourseList(CourseListDTO clto){
        System.out.println("saveCourseList 모달 전"+clto.getCourseid()+" "+clto.getRegion()+" "+clto.getTitle());
        CourseList courseList = modelMapper.map(clto,CourseList.class);
        System.out.println("saveCourseList 모달 후"+courseList.getCourseid()+" "+courseList.getRegion()+" "+courseList.getTitle());
        courseListRepository.save(courseList);
    }

    //미완성
    public void changeCourseList(CourseListDTO clto, Long listId){
        CourseList courseList = modelMapper.map(clto,CourseList.class);
        courseList.setId(listId);
        courseListRepository.save(courseList);

    }

    public Long getListId(Long course_id){
        List<CourseList> courlist= courseListRepository.findByCourseid(course_id);
        System.out.println(courlist.get(0).getId());
        return courlist.get(0).getId();

    }
    public Long makeCourse(HttpServletRequest req){
        Course course = new Course();
        course.setDays(req.getParameter("days"));
        course.setStartday(req.getParameter("start"));
        course.setEndday(req.getParameter("end"));
        courseRepository.save(course);
        return course.getId();
    }

    public Long changeCourse(HttpServletRequest req,Long course_id){
        Course course = new Course();
        course.setId(course_id);
        course.setDays(req.getParameter("days"));
        course.setStartday(req.getParameter("start"));
        course.setEndday(req.getParameter("end"));
        courseRepository.save(course);
        return course.getId();
    }
    public Map<String,String> getDays(Long course_id){
       Course cs= courseRepository.findById(course_id).orElseThrow(()->new NoSuchElementException("값 없음"));
       String days=cs.getDays();
       String start=cs.getStartday();
       Map<String,String> map=new HashMap<String,String>();
       map.put("days",days);
       map.put("start",start);
       return map;
    }

    public void deleteData(Long course_id){
        int count = courseDataRepository.countById(course_id);

        List<CourseData> li =new Vector<CourseData>();
        for(long i=1;i<=count;i++){
            CourseDataId courseDataid=new CourseDataId();
            courseDataid.setId(course_id);
            courseDataid.setOrder(i);
            courseDataRepository.deleteById(courseDataid);

        }
    }

    public Page<CourseListDTO> getList(PageRequest pageRequest){

        Page<CourseList> courseLists = courseListRepository.findAll(pageRequest);

        for(CourseList courseList : courseLists){
            CourseListDTO cdto = modelMapper.map(courseList, CourseListDTO.class);
            int length = courseDataRepository.countById(cdto.getCourseid());
            List<CourseDTO> datas = new ArrayList<>();
            for(int i=0; i < length;i++) {
                CourseDataId id = new CourseDataId();
                id.setId(cdto.getCourseid());
                id.setOrder((long) i+1);
                datas.add(modelMapper.map(courseDataRepository.findById(id), CourseDTO.class));
            }
            cdto.setCourseDatas(datas);
            cdto.setCenter();
            long id = Long.parseLong(cdto.getCreatedBy());
            System.out.println(id);
            cdto.setCreatedBy(memberService.getMember(id).getNickname());
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
