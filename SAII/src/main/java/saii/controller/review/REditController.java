package saii.controller.review;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

import fileUpDown.FileUtil;
import saii.domain.reviewboardDAO;
import saii.dto.reviewboardDTO;
import utils.AlertFunc;

@WebServlet("/review_edit")
public class REditController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int r_id = Integer.parseInt(req.getParameter("r_id"));
		reviewboardDAO dao = new reviewboardDAO();
		reviewboardDTO dto = dao.selectView(r_id);
		req.setAttribute("dto", dto);
		req.getRequestDispatcher("/saii/review/Redit.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//1. 파일 업로드 처리 ==========================
		//업로드 디렉터리의 물리적 경로 확인
		String saveDirectory = req.getServletContext().getRealPath("../Storage");
		
		//초기화 매개변수로 설정한 첨부 파일 최대 용량 확인
		ServletContext application = getServletContext();
		int maxPostSize = Integer.parseInt(application.getInitParameter("maxPostSize"));
		
		//파일업로드
		MultipartRequest mr = FileUtil.uploadFile(req, saveDirectory, maxPostSize);
		
		if (mr == null) {
			//파일 업로드 실패
			AlertFunc.alertBack(resp, "첨부 파일이 제한 용량을 초과합니다.");
			return;
		}
		
		//2.파일 업로드 외 처리 =========================
		//수정 내용을 매개변수에서 얻어옴
		int r_id = Integer.parseInt(mr.getParameter("r_id"));
		String prevO_file = mr.getParameter("prevO_file");
		String prevN_file = mr.getParameter("prevN_file");
		
		String r_title = mr.getParameter("r_title");
		String content = mr.getParameter("content");
		
		//DTO에 저장
		reviewboardDTO dto = new reviewboardDTO();
		dto.setR_id(r_id);
		dto.setR_title(r_title);
		dto.setContent(content);
		
		//원본 파일명과 저장된 파일 이름 설정
		String fileName = mr.getFilesystemName("o_file");	
		if(fileName != null) {
			//첨부파일이 있을 경우 파일명 변경
			//새로운 파일명 생성
			String now = new SimpleDateFormat("yyyyMMdd_HmsS").format(new Date());
			String ext = fileName.substring(fileName.lastIndexOf(".")); //.포함 뒤에 문자
			String newFileName = now + ext;
			
			//파일명 변경
			File oldFile = new File(saveDirectory + File.separator + fileName);
			File newFile = new File(saveDirectory + File.separator + newFileName);
			oldFile.renameTo(newFile);
			
			dto.setO_file(fileName);	//원래 파일 이름
			dto.setN_file(newFileName);	//서버에 저장된 파일 이름
			
			//기존 파일 삭제
			FileUtil.deleteFile(req,"/Storage",prevN_file);
			
		}else {
			//첨부파일이 없으면 기존 이름 유지
			dto.setO_file(prevO_file);	
			dto.setN_file(prevN_file);	
			
		}
		
		//DB에 수정 내용 반영
		reviewboardDAO dao = new reviewboardDAO();
		int result = dao.updatePost(dto);
		dao.close();
		
		//성공 or 실패?
		if(result==1) { //수정 성공
			resp.sendRedirect("http://localhost:8081/SAII/review_view?r_id=" + r_id);
		}else { //수정 실패
			AlertFunc.alertLocation(resp, "수정 실패", "http://localhost:8081/SAII/review_view?r_id=" + r_id);
		}
		
	}
	
}
