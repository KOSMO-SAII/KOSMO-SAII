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
		String board_no = req.getParameter("board_no");
		String cmt_no = req.getParameter("cmt_no");
		
		reviewboardDAO dao = new reviewboardDAO();
		
		int result = dao.delComment(cmt_no);
		if(result ==1) {
			resp.sendRedirect("/saii//review_view?cmt_no="+board_no);
		}else {
			AlertFunc.alertBack(resp, "댓글 삭제 실패");
		}
	}
}
