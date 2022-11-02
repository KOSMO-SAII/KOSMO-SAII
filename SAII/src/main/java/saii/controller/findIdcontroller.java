package membercontroll;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import membership.MemberDAO;

public class findIdcontroller {
	
	private void dopost (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
	    String member_name = req.getParameter("member_name");
	     String member_phone = req.getParameter("member_phone");

		MemberDAO dao = new MemberDAO();
	 	String id = dao.findId(member_name, member_phone);
	 	
	 	RequestDispatcher dispatcher=req.getRequestDispatcher("/IdPwCheck/findid2.jsp");
		dispatcher.forward(req, resp);

	}
}
	
 
