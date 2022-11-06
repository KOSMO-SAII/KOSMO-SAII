package saii.controller.review;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import saii.domain.reviewboardDAO;
import saii.dto.CommentDTO;
import utils.AlertFunc;

@SuppressWarnings("serial")
@WebServlet("/editComment")
public class EditCommentController extends HttpServlet{

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("service");
		String cmt_no = req.getParameter("cmt_no");
		String cmt_content = req.getParameter("cmt_content");
		System.out.println(cmt_no);
		
		reviewboardDAO dao = new reviewboardDAO();
		CommentDTO dto = new CommentDTO();

		dto.setCmt_no(cmt_no);
		dto.setCmt_content(cmt_content);
		
		int result = dao.addComment(dto);
		//성공 or 실패?
		if(result==1) { //수정 성공
			resp.sendRedirect("http://localhost:8081/SAII/review_view?cmt_no=" + cmt_no);
		}else { //수정 실패
			AlertFunc.alertBack(resp,"댓글 수정 실패");
		}
	
	}
	
}
