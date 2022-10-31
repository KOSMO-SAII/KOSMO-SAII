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
import saii.dto.mainboardDTO;

@WebServlet("/write")
public class WriteController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		req.getRequestDispatcher("/saii/Write.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		// form값을 DTO에 저장
		mainboardDTO dto = new mainboardDTO();
		dto.setM_title(req.getParameter("m_title"));
		dto.setRegion(req.getParameter("region"));
		dto.setCourse_name(req.getParameter("course_name"));
		dto.setContent(req.getParameter("content"));
		
		// DAO를 통해 DB에 게시 내용 저장
		mainboardDAO dao = new mainboardDAO();
		int result = dao.insertWrite(dto);
		dao.close();
		
		// 성공 or 실패?
		if(result == 1) { // 글쓰기 성공
			resp.sendRedirect("../mainboard");
		}else { // 글쓰기 실패
			resp.sendRedirect("../write");
		}
	}
}
