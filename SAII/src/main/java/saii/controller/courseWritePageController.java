package saii.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import saii.domain.courseDAO;
import saii.dto.courseDTO;

@WebServlet("/course_write")
public class courseWritePageController extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		System.out.println("/course_write doGet");
		req.getRequestDispatcher("/saii/courseWritePage.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("course_write dopost");
		courseDAO cdao = new courseDAO();
		courseDTO cdto ;
		int len = 0;
		
		req.getRequestDispatcher("saii/test.jsp").forward(req, resp);
	}
	
	
}
