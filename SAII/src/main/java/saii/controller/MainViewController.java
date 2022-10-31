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

@WebServlet("/view")
public class MainViewController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		// 게시글 가져오기
		mainboardDAO dao = new mainboardDAO();
		String m_id = req.getParameter("m_id");
		dao.updateVisitCount(m_id);
		mainboardDTO dto = dao.selectView(m_id);
		dao.close();
		
		//dto.setContent(dto.getContent().replaceAll("/r/n", "<br/>"));
		
		req.setAttribute("dto", dto);
		req.getRequestDispatcher("/saii/View.jsp").forward(req, resp);
	}
}
