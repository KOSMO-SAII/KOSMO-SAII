package saii.domain;

import java.util.List;
import java.util.Map;
import java.util.Vector;

import saii.controller.JDBConnect;
import saii.dto.mainboardDTO;

public class mainboardDAO extends JDBConnect {
	public mainboardDAO() {
		super();
	}
	
	public int selectCount(Map<String, Object> map) {
		int totalCount = 0;
		String sql = "SELECT COUNT(*) FROM MAIN_BOARD ";
		if(map.get("searchStr") != null) {
			sql += "WHERE " + map.get("searchType") + " LIKE '%" + map.get("searchStr") + "%'";
		}
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			rs.next();
			totalCount = rs.getInt(1);
		}catch(Exception e) {
			System.out.println("게시글 카운트 중 에러");
			e.printStackTrace();
		}
		return totalCount;
	}
	
	public List<mainboardDTO> selectListPage(Map<String, Object> map){
		List<mainboardDTO> bl = new Vector<mainboardDTO>();
		String sql = "select rownum, m_id, m_title, region, course_name, content, nickname, m_postdate, visitcount, goodcount from main_board";
		if(map.get("searchStr") != null) {
			sql += " WHERE " + map.get("searchType") + " LIKE '%" + map.get("searchStr") + "%' AND (rownum BETWEEN ? AND ?)";
		}else {
			sql += " WHERE rownum BETWEEN ? AND ? ORDER BY M_ID DESC ";			
		}
		
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1, map.get("start").toString());
			psmt.setString(2, map.get("end").toString());
			rs = psmt.executeQuery();
			while(rs.next()) {
				mainboardDTO dto = new mainboardDTO();
				dto.setM_id(rs.getString("m_id"));
				dto.setM_title(rs.getString("m_title"));
				dto.setRegion(rs.getString("region"));
				dto.setCourse_name(rs.getString("course_name"));
				dto.setContent(rs.getString("content"));
				dto.setNickname(rs.getString("nickname"));
				dto.setVisitcount(rs.getInt("visitcount"));
				dto.setGoodcount(rs.getInt("goodcount"));
				dto.setM_postdate(rs.getDate("m_postdate"));
				
				bl.add(dto);
			}
		}catch(Exception e) {
			System.out.println("게시판 목록 읽기 중 에러");
			e.printStackTrace();
		}
		return bl;
	}
}
