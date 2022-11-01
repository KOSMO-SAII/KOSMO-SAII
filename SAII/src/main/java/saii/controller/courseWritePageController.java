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
		
		if(req.getParameter("c1") != null) {
			cdto = cdao.toCDTO(1, req.getParameter("c1"));
			req.setAttribute("c1", cdto);
			len = 1;
		} 
		if(req.getParameter("c2") != null) {
			cdto = cdao.toCDTO(2, req.getParameter("c2"));
			req.setAttribute("c2", cdto);
			len = 2;
		} 
		if(req.getParameter("c3") != null) {
			cdto = cdao.toCDTO(3, req.getParameter("c3"));
			req.setAttribute("c3", cdto);
			len = 3;
		} 
		if(req.getParameter("c4") != null) {
			cdto = cdao.toCDTO(4, req.getParameter("c4"));
			req.setAttribute("c4", cdto);
			len = 4;
		}
		if(req.getParameter("c5") != null) {
			cdto = cdao.toCDTO(5, req.getParameter("c5"));
			req.setAttribute("c5", cdto);
			len = 5;
		}
		if(req.getParameter("c6") != null) {
			cdto = cdao.toCDTO(6, req.getParameter("c6"));
			req.setAttribute("c6", cdto);
			len = 6;
		}
		if(req.getParameter("c7") != null) {
			cdto = cdao.toCDTO(7, req.getParameter("c7"));
			req.setAttribute("c7", cdto);
			len = 7;
		}
		System.out.println("blabla");
		//resp.sendRedirect("http://localhost:8081/SAII/saii/my/test.jsp");
		req.getRequestDispatcher("saii/test.jsp").forward(req, resp);
	}
	
	
}
