package saii.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import saii.domain.courseDAO;
import saii.dto.courseDTO;

@WebServlet("/course_view")
public class courseViewController extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("코스 뷰 get작동");
		System.out.println(req.getParameter("num"));
		
		req.setAttribute("num", req.getParameter("num"));
		System.out.println(req.getAttribute("num"));
		req.getRequestDispatcher("/saii/courseView.jsp").forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("courseview post");
		
		//courseWrite페이지에서 넘어온 값이 있을 시 실행
		if(req.getParameterValues("data")!=null) {
		courseDAO cdao = new courseDAO();
		String[] str = req.getParameterValues("data");
		System.out.println(str[0]);
		ArrayList<courseDTO> cdtos = cdao.toCDTO(str);
		
		cdao.insertCourse(cdtos);
		cdao.close();
		}
		
		req.getRequestDispatcher("/saii/courseView.jsp").forward(req, resp);
	}

}
