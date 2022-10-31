package saii.controller;

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

@WebServlet("/review_mode")
public class RModeController extends HttpServlet {
	
	//적은 데이터 옮길 때는 get 방식, 많은 데이터 옮길 떄는 post
	
	//view.jsp에서 만든 버튼을 누르면 doGet()메소드 실행됨. mode값 set함.
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("review_mode doGet");
		
		int r_id = Integer.parseInt(req.getParameter("r_id"));
		String mode = req.getParameter("mode");

		
//		//비밀번호 확인
		reviewboardDAO dao = new reviewboardDAO();

			if(mode.equals("edit")) {	//수정 모드

				resp.sendRedirect("http://localhost:8081/SAII/review_edit?r_id=" + r_id);
			}
			else if(mode.equals("delete")) {	//삭제 모드
				dao = new reviewboardDAO();
				reviewboardDTO dto = dao.selectView(r_id);
				int result = dao.deletePost(r_id);	//DB 게시물 삭제
				dao.close();
				if(result==1) {	//게시물 삭제 성공 시 첨부파일도 삭제
					String saveFileName = dto.getN_file();
					FileUtil.deleteFile(req,"/Storage",saveFileName);	//storage에 있는 파일 삭제
				}
				AlertFunc.alertLocation(resp, "삭제되었습니다.", "http://localhost:8081/SAII/review_list");
			
			}
		}

	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("review_mode doPost");
		
		//매개변수 저장
		int r_id = Integer.parseInt(req.getParameter("r_id"));
		String mode = req.getParameter("mode");
		
		reviewboardDAO dao = new reviewboardDAO();


			if(mode.equals("edit")) {	//수정 모드
				resp.sendRedirect("http://localhost:8081/SAII/review_edit?r_id=" + r_id);
			}
			else if(mode.equals("delete")) {	//삭제 모드
				dao = new reviewboardDAO();
				reviewboardDTO dto = dao.selectView(r_id);
				int result = dao.deletePost(r_id);	//DB 게시물 삭제
				dao.close();
				if(result==1) {	//게시물 삭제 성공 시 첨부파일도 삭제
					String saveFileName = dto.getN_file();
					FileUtil.deleteFile(req,"/Storage",saveFileName);	//storage에 있는 파일 삭제
				}
				AlertFunc.alertLocation(resp, "삭제되었습니다.", "/review_list");
			
			}
		}

	
}
