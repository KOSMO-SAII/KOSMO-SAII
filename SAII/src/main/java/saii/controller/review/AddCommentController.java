package saii.controller.review;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import saii.domain.reviewboardDAO;
import saii.dto.CommentDTO;
import utils.AlertFunc;

@WebServlet("/addComment")
public class AddCommentController extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//데이터 가져오기(cmt_content, board_no)
		String board_no = req.getParameter("board_no");
		String cmt_content = req.getParameter("cmt_content");
		
		reviewboardDAO dao = new reviewboardDAO();
		CommentDTO dto = new CommentDTO();
		
		HttpSession session = req.getSession();
		String cmt_id = (String)session.getAttribute("UserId");
		
		dto.setCmt_id(cmt_id);
		dto.setBoard_no(board_no);
		dto.setCmt_content(cmt_content);
		
		int result = dao.addComment(dto);
		if(result == 1) {
			resp.sendRedirect("/SAII/review_view?r_id=" + board_no);
		}else {
			AlertFunc.alertBack(resp,"댓글 쓰기 실패");
		}
	}
}
