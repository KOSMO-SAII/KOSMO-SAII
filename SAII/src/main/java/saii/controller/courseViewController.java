package saii.controller;

import java.io.IOException;
import java.util.ArrayList;
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

import saii.domain.courseDAO;
import saii.domain.mainboardDAO;
import saii.dto.courseDTO;
import saii.dto.mainboardDTO;

@WebServlet("/course_view")
public class courseViewController extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//코스 id 받아옴
		int courseId= Integer.parseInt( req.getParameter("num"));
		
		courseDAO cdao= new courseDAO();
		//받아온 코스 id로 db에서 값 뽑아옴
		ArrayList<courseDTO> cdtos=cdao.getCourse(courseId);
		
		List<Map<String, String>> list=new Vector<Map<String,String>>();
		
		for(int i=0; i<cdtos.size();i++) {
			Map<String, String> map= new HashMap<>();
			map.put("address_id",cdtos.get(i).getAddress_id());
			map.put("address_name",cdtos.get(i).getAddress_name());
			map.put("category",cdtos.get(i).getCategory());
			map.put("Course_id",cdtos.get(i).getCourse_id());
			map.put("Memo",cdtos.get(i).getMemo());
			map.put("Phone_number",cdtos.get(i).getPhone_number());
			map.put("Place_name",cdtos.get(i).getPlace_name());
			map.put("Place_url",cdtos.get(i).getPlace_url());
			map.put("Road_address_name",cdtos.get(i).getRoad_address_name());
			map.put("X",cdtos.get(i).getX());
			map.put("Y",cdtos.get(i).getY());
			
			list.add(map);
			
		}
		req.setAttribute("List", list);
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
			cdao.insertCourse(cdtos); 	
			String course_id = Integer.toString(cdao.getCurrentCourseId());
			cdao.close();
			
			mainboardDAO mdao = new mainboardDAO();
			mainboardDTO mdto = new mainboardDTO();
			
			mdto.setCourse_id(course_id);
			String title = req.getParameter("title");
			String region = req.getParameter("region");
			String nickname = req.getParameter("nickname");
			//System.out.println(nickname);
			if (title != null && title.equals(""))
				title = nickname + "_" + cdtos.get(0).getPlace_name();
			if (region != null && region.equals("")) {
				String[] reg = cdtos.get(0).getAddress_name().split("\\s");
				region = reg[0] + " " + reg[1];				
			}
			mdto.setM_title(title);
			mdto.setRegion(region);
			//System.out.println("mdao insert");
			mdao.insertWrite(mdto,nickname);		
		}
		//System.out.println("==여기는 문자열 자르기");
		List<Map<String, String>> list=new Vector<Map<String,String>>();
		String[] datas = req.getParameterValues("data");
		//System.out.println(Arrays.toString(datas));
		//System.out.println(datas[0]);
		
		for(int k=0; k<datas.length;k++) {
			String[] data =  datas[k].split("\\|");
			//System.out.println(Arrays.toString(data));
		
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
			//System.out.println(map.entrySet());
		}
		//System.out.println("===여기는 리스트");
		//System.out.println(list);
		//System.out.println(list.get(0));
		req.setAttribute("List", list);
		req.getRequestDispatcher("/saii/courseView.jsp").forward(req, resp);
	}

	
}
