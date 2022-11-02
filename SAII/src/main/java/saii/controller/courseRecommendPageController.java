package saii.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import saii.domain.courseDAO;
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
		courseDAO cdao = new courseDAO();
		List<Map<String,String>> list = new Vector<Map<String, String>>();
		for(mainboardDTO mdto : mdtos) {
			HashMap<String, String> map = new HashMap<>();
			map.put("title", mdto.getM_title());
			map.put("region", mdto.getRegion());
			map.put("p_name", cdao.getPlaceNames(mdto.getCourse_id()));
			map.put("c_id", mdto.getCourse_id());
			
			// 각 정보 속성값에 저장하기
			list.add(map);
			System.out.println(map.get("p_name"));
		}		
		req.setAttribute("list", list);
		req.getRequestDispatcher("saii/courseRecommendPage.jsp").forward(req, resp);
	}
	
	
	
}



