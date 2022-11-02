package saii.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import saii.domain.reviewboardDAO;
import utils.AlertFunc;

@WebServlet("/delComment")
public class DelCommentController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String r_id = req.getParameter("r_id");
		String cmt_no = req.getParameter("cmt_no");
		
		reviewboardDAO dao = new reviewboardDAO();
		
		int result = dao.delComment(cmt_no);
		if(result ==1) {
			resp.sendRedirect("/SAII/review_view?r_id="+r_id);
		}else {
			AlertFunc.alertBack(resp, "댓글 삭제 실패");
		}
		
		
		
		
	}
}
