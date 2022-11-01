package saii.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import saii.domain.mainboardDAO;
import saii.dto.mainboardDTO;

@WebServlet("/course_recommend")
public class courseRecommendPageController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		System.out.println("course_recommend doGet");
			
		//MBOARD DAO 정보가져오기
		mainboardDAO mdao = new mainboardDAO();
		ArrayList<mainboardDTO> mdtos = mdao.getRecommendData();
		//COURSE DAO 정보가져오기
		
		getCourse
		// 각 정보 속성값에 저장하기
				
		req.getRequestDispatcher("saii/courseRecommendPage.jsp").forward(req, resp);
	}
	
	
	
}



