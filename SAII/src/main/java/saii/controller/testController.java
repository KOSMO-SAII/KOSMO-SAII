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
		ArrayList<courseDTO> cdtos = new ArrayList<>();		
		cdtos.add(cdao.toCDTO(8, req.getParameter("c1")));
		cdtos.add(cdao.toCDTO(8, req.getParameter("c2")));
		cdtos.add(cdao.toCDTO(8, req.getParameter("c3")));
	
		cdao.insertCourse(cdtos);
		
		req.getRequestDispatcher("/saii/test.jsp").forward(req, resp);
		
	}
}
