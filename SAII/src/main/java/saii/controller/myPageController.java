package saii.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import saii.domain.mainboardDAO;
import saii.domain.memberDAO;
import saii.dto.mainboardDTO;
import saii.dto.memberDTO;

@WebServlet("/mypage")
public class myPageController extends HttpServlet{

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("/mypage doGet");
		String id = req.getParameter("id");
		memberDAO dao = new memberDAO();
		memberDTO dto = dao.userinfo(id);
		
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
		int totalCount = mdao.selectCount(map);//게시물의 갯수
		
		List<mainboardDTO> boardLists = mdao.selectListPage1(map);
		
		map.put("totalCount", totalCount);
		req.setAttribute("boardLists", boardLists);
		req.setAttribute("map", map);
		req.setAttribute("dto", dto);
		req.getRequestDispatcher("saii/myPage.jsp").forward(req, resp);
	}	
}
