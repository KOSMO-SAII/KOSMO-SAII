package saii.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;

import saii.domain.reviewboardDAO;
import saii.dto.CommentDTO;
import saii.dto.reviewboardDTO;

@WebServlet("/review_view")
public class RViewController extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//게시물 가져오기
		reviewboardDAO dao = new reviewboardDAO();
		int r_id = Integer.parseInt(req.getParameter("r_id"));
		dao.updateVisitCount(r_id);//조회수 증가
		reviewboardDTO dto=dao.selectView(r_id);
		dao.close();
		
		//줄바꿈 처리
		dto.setContent(dto.getContent().replaceAll("\r\n", "<br/>"));	//윈도우에서 쓰는 이스케이프문("\r\n"). os마다 다르게 사용.
		
		req.setAttribute("dto", dto);

		req.getRequestDispatcher("/saii/Rview.jsp").forward(req, resp);
		
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String cmt_no = req.getParameter("cmt_no");
		reviewboardDAO dao = new reviewboardDAO();
		
		ArrayList<CommentDTO> commentLists = dao.listComment(cmt_no);
		
		req.setAttribute("commentLists", commentLists);
		
		req.getRequestDispatcher("/saii/Rview.jsp").forward(req, resp);
	}
	
	
}
