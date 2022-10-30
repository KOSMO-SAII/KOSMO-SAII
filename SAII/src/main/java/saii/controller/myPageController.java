package saii.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import saii.domain.memberDAO;
import saii.dto.memberDTO;

@WebServlet("/mypage")
public class myPageController extends HttpServlet{

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("/mypage doGet");
		String id = req.getParameter("id");
		memberDAO dao = new memberDAO();
		memberDTO dto = dao.userinfo(id);
		
		req.setAttribute("dto", dto);
		req.getRequestDispatcher("saii/myPage.jsp").forward(req, resp);
	}	
}
