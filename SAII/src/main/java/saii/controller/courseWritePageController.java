package saii.controller;

import java.io.IOException;
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
import saii.dto.courseDTO;

@WebServlet("/course_write")
public class courseWritePageController extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		System.out.println("/course_write doGet");
		req.getRequestDispatcher("/saii/courseWritePage.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/course_write doPost");
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
				req.getRequestDispatcher("/saii/courseWritePage.jsp").forward(req, resp);
	}
	
	
}
