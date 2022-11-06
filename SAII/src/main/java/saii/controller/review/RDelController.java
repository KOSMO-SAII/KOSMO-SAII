package saii.controller.review;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fileUpDown.FileUtil;
import saii.domain.reviewboardDAO;
import saii.dto.reviewboardDTO;
import utils.AlertFunc;

@WebServlet("/review_delete")
public class RDelController extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int r_id = Integer.parseInt(req.getParameter("r_id"));

		String r_d = req.getParameter("r_id");

		reviewboardDAO dao = new reviewboardDAO();

		dao = new reviewboardDAO();
		reviewboardDTO dto = dao.selectView(r_id);
		int result = dao.deletePost(r_id);	//DB 게시물 삭제
		
		if(result==1) {	//게시물 삭제 성공 시 첨부파일도 삭제
			String saveFileName = dto.getN_file();
			FileUtil.deleteFile(req,"/Storage",saveFileName);	//storage에 있는 파일 삭제
			dao.delAllComment(r_d);
		}
		AlertFunc.alertLocation(resp, "삭제되었습니다.", "http://localhost:8081/SAII/review_list");
		dao.close();
	}
			
}
