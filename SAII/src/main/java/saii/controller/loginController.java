package saii.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import saii.domain.memberDAO;
import saii.dto.memberDTO;
import saii.service.LoginService;

@WebServlet("/login")
public class loginController extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		System.out.println("/login doGet");
		req.getRequestDispatcher("/saii/loginPage.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LoginService loginService = new LoginService();
		
		String userId= req.getParameter("user_id");
		String userPw=req.getParameter("user_pw");
		System.out.println(userId);
		System.out.println(userPw);
		
		memberDTO dto = loginService.doPost(userId,userPw);
		
		HttpSession session =req.getSession();
		System.out.println(dto.getId());
		if(dto.getId()!=null) {
			session.setAttribute("UserId", dto.getId());
			session.setAttribute("UserName", dto.getName());			
			
			resp.setContentType("text/html; charset=UTF-8");
			PrintWriter out = resp.getWriter();
			out.println(
					"<script language='javascript'>alert('로그인 되었습니다.'); location.href='http://localhost:8081/SAII/saii/startPage.jsp';</script>");
			out.flush();
		}else {
			req.setAttribute("LoginErrMsg", "로그인 오류입니다");
			req.getRequestDispatcher("/saii/loginPage.jsp").forward(req, resp);
		}
	}
}
