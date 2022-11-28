package com.example.test.service;

import com.example.test.domain.MainBoardDTO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface CourseWriteService {
    public void loginCheck(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
    public List<Map<String, String>> changeCourse( HttpServletRequest req);
    public MainBoardDTO saveMainboard(HttpServletRequest req);
}
