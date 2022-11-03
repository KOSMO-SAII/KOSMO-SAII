package saii.controller.mainpage;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import saii.controller.Paging;
import saii.domain.goodDAO;
import saii.domain.mainboardDAO;
import saii.dto.mainboardDTO;

@WebServlet("/mainboard")
public class MainListController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		// DAO 생성
		mainboardDAO dao = new mainboardDAO();
		goodDAO gdao = new goodDAO();
		Map<String, Object> map = new HashMap<>();
		
		// 검색 옵션
		String searchType = req.getParameter("searchType");
		String searchStr = req.getParameter("searchStr");
		if(searchStr != null) {
			map.put("searchType", searchType);
			map.put("searchStr", searchStr);
		}
		
		// 페이징
		Paging paging = new Paging();
		
		int page = 1;
		if(req.getParameter("page") != null) {
			page = Integer.parseInt(req.getParameter("page"));
		}
		
		paging.setPage(page);
		int count = dao.selectCount(map);
		paging.setTotalCount(count);
		
		map.put("startNum", paging.getStartNum());
		map.put("endNum", paging.getEndNum());
		
		// DB에서 게시물 정보
		List<mainboardDTO> mainBoardLists = dao.selectListPage(map);
		dao.close();
		
		// 뷰로 최종 전달
		req.setAttribute("mainBoardLists", mainBoardLists);
		req.setAttribute("map", map);
		req.setAttribute("paging", paging);
		req.getRequestDispatcher("/saii/mainboard.jsp").forward(req, resp);
	}
}
