package com.example.test.service;

import com.example.test.domain.CourseDTO;
import com.example.test.domain.MainBoardDTO;
import com.example.test.repository.MainBoardDAO;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface CourseViewService {
    public ArrayList<CourseDTO> toCDTO(String[] str);

    public String findIdNickName(int course_id);

    public MainBoardDTO findIdPostRegion(int course_id);

    public ArrayList<CourseDTO> getIdCourse(int course_id);

    public List<Map<String,String>> printCourse(ArrayList<CourseDTO> cdtos);

    public MainBoardDTO editMode(HttpServletRequest req);

    public MainBoardDTO writeMode(HttpServletRequest req);

    public void insertCourseS(ArrayList<CourseDTO> cdtos,int course_id);

    public List<Map<String, String>> giveCourse(HttpServletRequest req);

}
