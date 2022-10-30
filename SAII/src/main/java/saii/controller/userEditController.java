package saii.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import saii.domain.memberDAO;
import saii.dto.memberDTO;

@WebServlet("/mypage.edit")
public class userEditController extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("mpedit get");
		String id = req.getParameter("id");
		memberDAO dao = new memberDAO();
		memberDTO dto = dao.userinfo(id);
		
		req.setAttribute("dto", dto);
		req.getRequestDispatcher("saii/userEditPage.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String id = req.getParameter("id");
		System.out.println(id);
		memberDTO dto = new memberDTO();
		
		dto.setPw(req.getParameter("pw"));
		dto.setNickname(req.getParameter("nickname"));
		dto.setPhone(req.getParameter("phone"));
		dto.setEmail(req.getParameter("email"));
		dto.setAddress(req.getParameter("address"));
		dto.setId(req.getParameter("id"));
		
		
		memberDAO dao = new memberDAO();
		int result = dao.update(dto);
		
		//성공 or 실패?
		if(result==1) { //수정 성공
			System.out.println("수정 성공");
			resp.sendRedirect("http://localhost:8081/SAII/mypage?id="+id);
			System.out.println(id+"여기야");
		}
		else { //수정 실패
			System.out.println("수정 실패");
			resp.sendRedirect("http://localhost:8081/SAII/mypage?id="+id);
		}
		
	}
}
