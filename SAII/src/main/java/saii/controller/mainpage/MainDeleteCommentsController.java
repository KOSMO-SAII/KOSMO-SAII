package saii.controller.mainpage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import saii.domain.mainCommentsDAO;
import saii.domain.reviewboardDAO;
import utils.AlertFunc;

@WebServlet("/deleteMainComments")
public class MainDeleteCommentsController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String c_id = req.getParameter("c_id");
		String m_id = req.getParameter("m_id");
		
		mainCommentsDAO mcdao = new mainCommentsDAO();
		int result = mcdao.deleteComments(c_id);
		
		if(result == 1) {
			resp.sendRedirect("http://localhost:8081/SAII/view?m_id=" + m_id);
		}else {
			AlertFunc.alertBack(resp, "댓글 삭제 실패");
		}
	}
}
