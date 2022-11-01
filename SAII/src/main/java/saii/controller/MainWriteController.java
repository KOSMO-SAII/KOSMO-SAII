package saii.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import saii.domain.mainboardDAO;
import saii.domain.memberDAO;
import saii.dto.mainboardDTO;
import saii.dto.memberDTO;

@WebServlet("/write")
public class MainWriteController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		req.getRequestDispatcher("/saii/MainWrite.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		// form값을 DTO에 저장
		mainboardDTO dto = new mainboardDTO();
		dto.setM_title(req.getParameter("m_title"));
		dto.setRegion(req.getParameter("region"));
		dto.setCourse_name(req.getParameter("course_name"));
		dto.setContent(req.getParameter("content"));
		
		// DB에서 아이디로 닉네임 찾기
		memberDTO memdto = new memberDTO();		
		memberDAO memdao = new memberDAO();
		String id = req.getSession().getAttribute("UserId").toString();
		memdto = memdao.userinfo(id);
		
		// DAO를 통해 DB에 게시 내용 저장
		mainboardDAO dao = new mainboardDAO();
		int result = dao.insertWrite(dto, memdto.getNickname());
		
		memdao.close();
		dao.close();
		
		// 성공 or 실패?
		if(result == 1) { // 글쓰기 성공
			resp.sendRedirect("http://localhost:8081/SAII/mainboard");
		}else { // 글쓰기 실패
			resp.sendRedirect("http://localhost:8081/SAII/write");
		}
	}
}
