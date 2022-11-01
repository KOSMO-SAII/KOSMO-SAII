package saii.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import saii.domain.courseDAO;
import saii.domain.mainboardDAO;
import saii.dto.courseDTO;
import saii.dto.mainboardDTO;

@WebServlet("/course_view")
public class courseViewController extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("코스 뷰 get작동");
		System.out.println(req.getParameter("num"));
		
		req.setAttribute("num", req.getParameter("num"));
		System.out.println(req.getAttribute("num"));
		req.getRequestDispatcher("/saii/courseView.jsp").forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("courseview post");
		
		//courseWrite페이지에서 넘어온 값이 있을 시 실행
		if(req.getParameterValues("data")!=null) {
			courseDAO cdao = new courseDAO();
			String[] str = req.getParameterValues("data");
			ArrayList<courseDTO> cdtos = cdao.toCDTO(str);
			cdao.insertCourse(cdtos); 	// 최근 코스id 받기를 어케하지
			String course_id = Integer.toString(cdao.getCurrentCourseId());
			cdao.close();
			
			mainboardDAO mdao = new mainboardDAO();
			mainboardDTO mdto = new mainboardDTO();
			
			mdto.setCourse_id(course_id);
			String title = req.getParameter("title");
			String region = req.getParameter("region");
			String nickname = req.getParameter("nickname");
			if (title != null && title.equals(""))
				title = nickname + "_" + cdtos.get(0).getPlace_name();
			if (region != null && region.equals("")) {
				String[] reg = cdtos.get(0).getAddress_name().split("\\s");
				region = reg[0] + " " + reg[1];				
			}
			mdto.setM_title(title);
			mdto.setRegion(region);
			System.out.println("mdao insert");
			mdao.insertWrite(mdto,nickname);
						
		}
		
		req.getRequestDispatcher("/saii/courseView.jsp").forward(req, resp);
	}

}
