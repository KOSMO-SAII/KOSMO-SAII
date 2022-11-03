package saii.controller.review;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fileUpDown.FileUtil;

@WebServlet("/review_download")
public class RDownloadController extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//매개변수 받기
		String o_file = req.getParameter("o_file");	//원본 파일명
		String n_file = req.getParameter("n_file");	//저장된 파일명
		int r_id = Integer.parseInt(req.getParameter("r_id"));		//게시물 일련번호
		
		//파일 다운로드
		
		FileUtil.download(req, resp, "/Storage", n_file, o_file);
		
	}
	


	
}
