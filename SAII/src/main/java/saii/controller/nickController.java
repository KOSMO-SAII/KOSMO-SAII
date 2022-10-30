package saii.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import saii.domain.memberDAO;

@WebServlet("/nickcheck")
public class nickController extends HttpServlet {

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/saii/nickcheck.jsp").forward(req, resp);
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		memberDAO dao = new memberDAO();
		String nickname = req.getParameter("nickname");
		boolean confirmed = dao.checkNick(nickname);
		
		if (!confirmed) { // 중복
			alertBack(resp, "중복된 닉네임입니다.");
		} else { // 실행
			alertLocation(resp, "사용할수있는 닉네임입니다.", "http://localhost:8081/SAII/saii/signupPage.jsp?nickname=" + nickname);
		}

		dao.close();
	}
	
	public void alertBack(HttpServletResponse response, String msg) {
	      try {
	         response.setContentType("text/html;charset=UTF-8");
	         PrintWriter writer = response.getWriter();
	         String script = "<script>" + "    alert('" + msg + "');" + "    history.back();" + "</script>";
	         writer.println(script);
	      } catch (Exception e) {
	      }
	   }
	   
	   public void alertLocation(HttpServletResponse response, String msg, String url) {
	      try {
	         response.setContentType("text/html;charset=UTF-8");
	         PrintWriter writer = response.getWriter();
	         String script = "<script>" + "    alert('" + msg + "');window.open('','_self').close();" + "    location.href='" + url + "';" + "</script>";
	         writer.println(script);
	      } catch (Exception e) {
	      }
	   }
}
