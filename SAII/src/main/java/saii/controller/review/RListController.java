package saii.controller.review;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import saii.domain.reviewboardDAO;
import saii.dto.reviewboardDTO;
import utils.PagingUtil;

@WebServlet("/review_list")
public class RListController extends HttpServlet {
   
   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
         throws ServletException, IOException {
	   System.out.println("review_list doGet");
	   
      //DAO 생성
      reviewboardDAO dao = new reviewboardDAO(); //
      //변수 저장용
      Map<String,Object> map = new HashMap<>();
      
      String categoryType = req.getParameter("categoryType");
      if(categoryType != null) map.put("categoryType", categoryType);
      
      //검색 옵션
      String searchType = req.getParameter("searchType");
      String searchStr = req.getParameter("searchStr");
      if(searchStr != null) {
         map.put("searchType", searchType);
         map.put("searchStr", searchStr);
      }
      int totalCount = dao.selectCount(map);//게시물의 갯수
      
      ServletContext application = getServletContext();
      int pageSize = Integer.parseInt(application.getInitParameter("PAGE_SIZE"));
      int pageBlock = Integer.parseInt(application.getInitParameter("PAGING_BLOCK"));
      
      //페이지 확인
      int pageNum = 1;
      String pageTemp = req.getParameter("pageNum");
      if(pageTemp!=null && !pageTemp.equals("")) {
         pageNum=Integer.parseInt(pageTemp);
      }
      
      //목록에 보여줄 게시물 범위 계산
      int start = (pageNum-1)*pageSize+1;
      int end = pageNum*pageSize;
      map.put("start",start);
      map.put("end",end);
      
      
      //DB에서 게시물 정보 받아와야함
      List<reviewboardDTO> boardLists = dao.selectListPage(map);
      dao.close();
      
      //뷰에 전달할 값들 추가
      String pagingStr = PagingUtil.pagingCenter(totalCount, pageSize, pageBlock, pageNum, "/SAII/review_list");
      map.put("pagingStr", pagingStr);
      map.put("totalCount", totalCount);
      map.put("pageSize", pageSize);
      map.put("pageNum", pageNum);
      //뷰로 최종 전달
      req.setAttribute("boardLists", boardLists);
      req.setAttribute("map", map);
      req.getRequestDispatcher("/saii/review/RlistCard.jsp").forward(req, resp);
   }
   

}