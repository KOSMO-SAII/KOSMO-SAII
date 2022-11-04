package saii.dto.mypage;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.mail.Session;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import saii.controller.Paging;
import saii.domain.courseDAO;
import saii.domain.mainboardDAO;
import saii.domain.memberDAO;
import saii.dto.mainboardDTO;
import saii.dto.memberDTO;

@WebServlet("/mypage")
public class myPageController extends HttpServlet{

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("/mypage doGet");
		HttpSession session =req.getSession();
		String idx = (String) session.getAttribute("UserId");
		
		System.out.println(idx);
		memberDAO dao = new memberDAO();
		memberDTO dto = dao.userinfo(idx);
		
		mainboardDAO mdao = new mainboardDAO();
		Map<String,Object> map = new HashMap<>();
		String searchType = req.getParameter("searchType");
		String searchStr = req.getParameter("searchStr");
		if(searchStr != null) {
			map.put("searchType", searchType);
			map.put("searchStr", searchStr);
		}
		String nick = dto.getNickname();
		map.put("nick", nick);
		System.out.println(nick);
		System.out.println(map.get("nick"));
		int totalCount = mdao.selectCount(map);//게시물의 갯수
		int mylist = mdao.mylistcount(nick);
		map.put("mylistcount", mylist);
		
		
		
		// 페이징
		Paging paging = new Paging();
	
		int page = 1;
		if(req.getParameter("page") != null) {
			page = Integer.parseInt(req.getParameter("page"));
		}
			
		paging.setPage(page);
		int count = mdao.selectCount(map);
		paging.setTotalCount(count);
				
		map.put("startNum", paging.getStartNum());
		map.put("endNum", paging.getEndNum());
		
		List<mainboardDTO> boardLists = mdao.myPage_selectListPage(map);
		
		ArrayList<mainboardDTO> mdtos = mdao.getmylist(nick);
		courseDAO cdao = new courseDAO();
		List<Map<String,String>> list = new Vector<Map<String, String>>();
		for(mainboardDTO mdto : mdtos) {
			DateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd");
			
			HashMap<String, String> hmap = new HashMap<>();
			hmap.put("m_title", mdto.getM_title());
			hmap.put("region", mdto.getRegion());
			hmap.put("p_name", cdao.getPlaceNames(mdto.getCourse_id()));
			hmap.put("course_id", mdto.getCourse_id());
			hmap.put("nickname", mdto.getNickname());
			hmap.put("m_postdate", sdFormat.format(mdto.getM_postdate()));
			hmap.put("count", Integer.toString(cdao.mylistcount(Integer.parseInt(mdto.getCourse_id()))));
		
			
			// 각 정보 속성값에 저장하기
			list.add(hmap);
			System.out.println(hmap.get("p_name"));
		}		
		
		req.setAttribute("list", list);
		map.put("totalCount", totalCount);
		req.setAttribute("boardLists", boardLists);
		req.setAttribute("map", map);
		req.setAttribute("dto", dto);
		req.getRequestDispatcher("saii/myPage.jsp").forward(req, resp);
	}	
}
