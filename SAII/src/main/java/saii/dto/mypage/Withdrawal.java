package saii.dto.mypage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import saii.domain.memberDAO;
import saii.dto.memberDTO;

@WebServlet("/withdraw")
public class Withdrawal extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String id = (String) session.getAttribute("UserId");
		memberDAO dao = new memberDAO();
		int result = dao.delete(id);
		
		
		if(result==1) {
			session.invalidate();
			req.getRequestDispatcher("home").forward(req, resp);
		}else {
			req.getRequestDispatcher("http://localhost:8081/SAII/mypage").forward(req, resp);
		
		}
	}

}
