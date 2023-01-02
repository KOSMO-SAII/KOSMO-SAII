package com.example.test.repository;

import com.example.test.domain.CourseDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Map;

@Mapper
@Repository
public interface CourseDAO {

    public void insertCourse(CourseDTO courseDTO);

    public void insertCourseData( Map<String,Object> cdto);

    public void updateCourse(int course_id, ArrayList<CourseDTO> cdtos);

    public void insertUpdate(String query, CourseDTO cdto, int course_id);

    public ArrayList<CourseDTO> getCourse(int course_id);

    public int getCurrentCourseId();

    public String getPlaceNames(String course_id);

    public int mylistcount(int course_id);
}
