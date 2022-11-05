package saii.controller.login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import saii.domain.memberDAO;
import saii.dto.memberDTO;


@WebServlet("/findId.do")
public class findIdcontroller extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
	    String member_name = req.getParameter("member_name");
	     String member_phone = req.getParameter("member_phone");
		memberDAO dao = new memberDAO();
		
		memberDTO dto= dao.findId(member_name, member_phone);
	 	
		req.setAttribute("id", dto.getId());
	 	req.getRequestDispatcher("/saii/login/findIdResult.jsp").forward(req, resp);


	}
	

}
	
 
