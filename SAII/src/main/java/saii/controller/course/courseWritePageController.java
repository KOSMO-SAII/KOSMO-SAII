package saii.controller.course;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspWriter;

import saii.domain.courseDAO;
import saii.domain.mainboardDAO;
import saii.dto.courseDTO;
import saii.dto.mainboardDTO;
import utils.AlertFunc;

@WebServlet("/course_write")
public class courseWritePageController extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session= req.getSession();
		if(session.getAttribute("UserId")!=null) {
			req.getRequestDispatcher("/saii/course/courseWritePage.jsp").forward(req, resp);
		}else {
			AlertFunc.alertLocation(resp, "작성하시려면 로그인을 해주세요", "http://localhost:8081/SAII/login");
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
				List<Map<String, String>> list=new Vector<Map<String,String>>();
				String[] datas = req.getParameterValues("data");
				
				mainboardDAO maindao= new mainboardDAO();
				int c_id=Integer.parseInt(req.getParameter("c_id"));
				mainboardDTO mdto=maindao.getMainboard(c_id);
				
				for(int k=0; k<datas.length;k++) {
					String[] data =  datas[k].split("\\|");
				
					Map<String, String> map= new HashMap<>();
					map.put("category",data[0]);
					map.put("address_id",data[1]);
					map.put("address_name",data[2]);
					map.put("Road_address_name",data[3]);
					map.put("Phone_number",data[4]);
					map.put("Place_name",data[5]);
					map.put("Place_url",data[6]);
					map.put("X",data[7]);
					map.put("Y",data[8]);
					if(data.length==10) {
					map.put("Memo",data[9]);
					}else {
					map.put("Memo","");
					}
					list.add(map);

				}
				req.setAttribute("title", mdto.getM_title());
				req.setAttribute("region",mdto.getRegion());
				req.setAttribute("List", list);
				req.getRequestDispatcher("/saii/course/courseWritePage.jsp").forward(req, resp);
	}
	
	
}
