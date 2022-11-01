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

@WebServlet("/test")
public class testController extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("test post");
		
		
		
		courseDAO cdao = new courseDAO();
		String[] str = req.getParameterValues("data");
		System.out.println(str[0]);
		ArrayList<courseDTO> cdtos = cdao.toCDTO(str);
		
		cdao.insertCourse(cdtos);
		cdao.close();
		
		req.getRequestDispatcher("/saii/courseView.jsp").forward(req, resp);
		
	}
}
