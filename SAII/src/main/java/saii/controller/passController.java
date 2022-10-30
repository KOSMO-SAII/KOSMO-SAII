package saii.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import saii.domain.memberDAO;
import saii.dto.memberDTO;

@WebServlet("/passcheck")
public class passController extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		memberDAO dao = new memberDAO();
		String password = req.getParameter("password");
		String id = req.getParameter("id");
		boolean confirmed = dao.checkPassword(id, password);
		System.out.println(id + password + confirmed);

		if (!confirmed) {
			resp.setContentType("text/html;charset=UTF-8");
			PrintWriter writer = resp.getWriter();
			String script = "<script>" + "alert('비밀번호가 틀렸습니다.');window.open('','_self').close();</script>";
			writer.println(script);
		} else {
			memberDTO dto = dao.getmemberDTO(id, password);
			resp.setContentType("text/html;charset=UTF-8");
			PrintWriter writer = resp.getWriter();
			System.out.println(dto.getId());
			String script = "<script> window.open('','_self').close(); opener.location.href='http://localhost:8081/SAII/mypage.edit?id=" + id + "';" + "</script>";
			writer.println(script);
		}
	}

}
