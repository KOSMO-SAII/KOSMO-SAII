package saii.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import saii.domain.mainboardDAO;
import saii.dto.mainboardDTO;

@WebServlet("/delete")
public class MainDeleteController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		mainboardDTO dto = new mainboardDTO();
		mainboardDAO dao = new mainboardDAO();
		
		String m_id = req.getParameter("m_id");
		
		int result = dao.delete(m_id);
		
		dao.close();
		
		// 성공 or 실패?
		if(result == 1) { // 삭제 성공
			resp.sendRedirect("http://localhost:8081/SAII/mainboard");
		}else { // 삭제 실패
			resp.sendRedirect("http://localhost:8081/SAII/view");
		}
	}
}
