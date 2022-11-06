package saii.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;



import saii.controller.JDBConnect;
import saii.dto.mainboardDTO;
import saii.dto.memberDTO;

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
		String sql = "select * from (select rownum as pnum, a.* "
				+ " from (select m.m_id, m.m_title, m.region, m.course_id, m.nickname, m.m_postdate, m.visitcount, NVL(m2.cnt, 0) as goodcount "
				+ " from main_board m, (select m_id, count(*) as cnt from good group by m_id) m2 "
				+ " where m.m_id = m2.m_id(+) order by m_id desc) a)";
		if(map.get("searchStr") != null) {
			sql += " WHERE " + map.get("searchType") + " LIKE '%" + map.get("searchStr") + "%' AND (pnum BETWEEN ? AND ?)";
		}else {
			sql += " WHERE pnum BETWEEN ? AND ? ";			
		}
		
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1, map.get("startNum").toString());
			psmt.setString(2, map.get("endNum").toString());
			rs = psmt.executeQuery();
			while(rs.next()) {
				mainboardDTO dto = new mainboardDTO();
				dto.setM_id(rs.getString("m_id"));
				dto.setM_title(rs.getString("m_title"));
				dto.setRegion(rs.getString("region"));
				dto.setCourse_id(rs.getString("course_id"));
				dto.setNickname(rs.getString("nickname"));
				dto.setM_postdate(rs.getDate("m_postdate"));
				dto.setVisitcount(rs.getInt("visitcount"));
				dto.setGoodcount(rs.getInt("goodcount"));
				
				bl.add(dto);
			}
		}catch(Exception e) {
			System.out.println("게시판 목록 읽기 중 에러");
			e.printStackTrace();
		}
		return bl;
	}
	
	public List<mainboardDTO> myPage_selectListPage(Map<String, Object> map){
		List<mainboardDTO> bl = new Vector<mainboardDTO>();
		goodDAO gdao = new goodDAO();
		String sql = "select rownum, m_id, m_title, region, course_id, mb.nickname, m_postdate, visitcount, goodcount "
				+ "from main_board mb, member m";
		if(map.get("searchStr") != null) {
			sql += " WHERE " + map.get("searchType") + " LIKE '%" + map.get("searchStr") + "%'"
					+ "and mb.nickname=m.nickname and mb.nickname='"+map.get("nick")+"'";
		}else {
			sql += " WHERE mb.nickname=m.nickname and mb.nickname='"+map.get("nick")+"'" ;			
		}
		
		try {
			stmt = con.createStatement();
			rs =stmt.executeQuery(sql);
			while(rs.next()) {
				mainboardDTO dto = new mainboardDTO();
				dto.setM_id(rs.getString("m_id"));
				dto.setM_title(rs.getString("m_title"));
				dto.setRegion(rs.getString("region"));
				dto.setCourse_id(rs.getString("course_id"));
				dto.setNickname(rs.getString("nickname"));
				dto.setVisitcount(rs.getInt("visitcount"));
				dto.setM_postdate(rs.getDate("m_postdate"));
				
				bl.add(dto);
			}
		}catch(Exception e) {
			System.out.println("게시판 목록 읽기 중 에러");
			e.printStackTrace();
		}
		return bl;
	}
	
	public void updateVisitCount(String m_id) {
		String sql = "update main_board "
				   + "set visitcount = visitcount + 1 "
				   + "where m_id = ?";
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1, m_id);
			psmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void minusVisitCount(String m_id) {
		String sql = "update main_board "
				   + "set visitcount = visitcount - 1 "
				   + "where m_id = ?";
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1, m_id);
			psmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public mainboardDTO selectView(String m_id) {
		mainboardDTO dto = new mainboardDTO();
		String sql = "select m_id, m_title, region, course_id, nickname, m_postdate, visitcount, (select count(*) from good where m_id = ?) as goodcount "
				   + " from main_board "
				   + " where m_id = ? ";
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1, m_id);
			psmt.setString(2, m_id);
			rs = psmt.executeQuery();
			if(rs.next()) {
				dto.setM_id(rs.getString("m_id"));
				dto.setM_title(rs.getString("m_title"));
				dto.setRegion(rs.getString("region"));
				dto.setCourse_id(rs.getString("course_id"));				
				dto.setNickname(rs.getString("nickname"));
				dto.setM_postdate(rs.getDate("m_postdate"));
				dto.setVisitcount(rs.getInt("visitcount"));
				dto.setGoodcount(rs.getInt("goodcount"));
			}
		}catch(Exception e) {
			System.out.println("게시글 상세보기 중 예외");
			e.printStackTrace();
		}
		return dto;
	}
	
	public int insertWrite(mainboardDTO dto, String nickname) {
		int rs = 0;
		String sql = "insert into main_board(m_id, m_title, region, course_id, nickname, visitcount)"
				   + " values(seq_mboard_num.nextval, ?, ?, ?, ?, 0)";
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1, dto.getM_title());
			psmt.setString(2, dto.getRegion());
			psmt.setString(3, dto.getCourse_id());
			psmt.setString(4, nickname);
			rs = psmt.executeUpdate();
		}catch(Exception e) {
			System.out.println("게시물 입력 중 예외");
			e.printStackTrace();
		}
		return rs;
	}
	
	public int updateWrite(mainboardDTO dto, String nickname) {
		int rs = 0;
		String sql = "update main_board set m_title = ?, region = ?, course_id = ?, where nickname = ?;";
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1, dto.getM_title());
			psmt.setString(2, dto.getRegion());
			psmt.setString(3, dto.getCourse_id());
			psmt.setString(4, nickname);
			rs = psmt.executeUpdate();
		}catch(Exception e) {
			System.out.println("게시물 수정 중 예외");
			e.printStackTrace();
		}
		return rs;
	}
	
	public int delete(String m_id) {
		int rs = 0;
		String sql = "delete from main_board where m_id = ?";
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1, m_id);
			rs = psmt.executeUpdate();
		}catch(Exception e) {
			System.out.println("게시물 삭제 중 예외");
			e.printStackTrace();
		}
		return rs;
	}
	
	public ArrayList<mainboardDTO> getRecommendData() {
		
		ArrayList<mainboardDTO> dtos = new ArrayList<>();
		String sql = "SELECT * FROM (SELECT * FROM MAIN_BOARD ORDER BY VISITCOUNT DESC) WHERE ROWNUM <= 3";
		
		try {
			stmt = con.createStatement();
			rs =stmt.executeQuery(sql);
			
			while(rs.next()) {
				mainboardDTO dto = new mainboardDTO();
				
				dto.setM_id(rs.getString("m_id"));
				dto.setM_title(rs.getString("m_title"));
				dto.setCourse_id(rs.getString("course_id"));
				dto.setVisitcount(rs.getInt("visitcount"));
				dto.setRegion(rs.getString("region"));
				
				dtos.add(dto);
			
			}
			
			
		} catch (Exception e) {
			System.out.println("get recommend err");
			e.printStackTrace();
		}
		
		
		return dtos;
	}
	
	public ArrayList<mainboardDTO> getmylist(String nickname){
		ArrayList<mainboardDTO> dtos = new ArrayList<>();
		String sql = "SELECT * FROM MAIN_BOARD WHERE NICKNAME=? ORDER BY M_ID";
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1, nickname);
			rs=psmt.executeQuery();
			
			while(rs.next()) {
				mainboardDTO dto = new mainboardDTO();
				
				dto.setM_id(rs.getString("m_id"));
				dto.setM_title(rs.getString("m_title"));
				dto.setRegion(rs.getString("region"));
				dto.setCourse_id(rs.getString("course_id"));				
				dto.setNickname(rs.getString("nickname"));
				dto.setM_postdate(rs.getDate("m_postdate"));
				dto.setVisitcount(rs.getInt("visitcount"));
				dto.setGoodcount(rs.getInt("goodcount"));
				
				dtos.add(dto);
				}
		} catch (Exception e) {
			System.out.println("getmylist err");
			e.printStackTrace();
		}
		return dtos;
	}
	
	public ArrayList<mainboardDTO> myfavolist(String nickname){
		ArrayList<mainboardDTO> dto = new ArrayList<>();
		String sql = "SELECT G.M_ID,B.M_TITLE,B.REGION,B.COURSE_ID,B.NICKNAME,B.M_POSTDATE,B.VISITCOUNT FROM GOOD G, MAIN_BOARD B, MEMBER M WHERE G.M_ID=B.M_ID AND B.NICKNAME=M.NICKNAME "
				+ "AND G.NICKNAME=?";
		try {
			psmt=con.prepareStatement(sql);
			psmt.setString(1, nickname);
			rs=psmt.executeQuery();
			
			while(rs.next()) {
				mainboardDTO dto2 = new mainboardDTO();
				
				dto2.setM_id(rs.getString("m_id"));
				dto2.setM_title(rs.getString("m_title"));
				dto2.setRegion(rs.getString("region"));
				dto2.setCourse_id(rs.getString("course_id"));
				dto2.setNickname(rs.getString("nickname"));
				dto2.setM_postdate(rs.getDate("m_postdate"));
				dto2.setVisitcount(rs.getInt("visitcount"));
				
				dto.add(dto2);
			}
		} catch (Exception e) {
			System.out.println("favolist err");
			e.printStackTrace();
		}
		return dto;
	}
	
	
	public int mylistcount(String nickname) {
		int result =0;
		String sql = "SELECT COUNT(*) FROM MAIN_BOARD WHERE NICKNAME=?";
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1, nickname);
			rs = psmt.executeQuery();
			rs.next();
			result = rs.getInt(1);
		} catch (Exception e) {
			System.out.println("mylistcount err");
			e.printStackTrace();
		}
		
		return result;
	}
	
	public String favoprofile (String m_id) {
		String result ="";
		String sql = "SELECT N_PROFILE_IMG FROM MEMBER M, MAIN_BOARD B WHERE M.NICKNAME=B.NICKNAME AND B.M_ID=?";
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1, m_id);
			rs=psmt.executeQuery();
			rs.next();
			result=rs.getString(1);
		} catch (Exception e) {
			System.out.println("fovoprofile err");
			e.printStackTrace();
		}
		return result;
		
	}
	
	public String getNickname(int course_id) {
		String result="";
		String sql="select nickname "
				+ "from main_board "
				+ "where course_id=? ";
		try {
			psmt=con.prepareStatement(sql);
			psmt.setInt(1, course_id);
			rs=psmt.executeQuery();
			rs.next();
			result=rs.getString(1);
		}catch (Exception e) {
			System.out.println("getNickname err");
			e.printStackTrace();
		}
		return result;
	}
	
	public mainboardDTO getMainboard(int course_id) {
		mainboardDTO mdto= new mainboardDTO();
	
		String sql="select m_title,region "
				+ "from main_board "
				+ "where course_id= ? ";
		try {
			psmt=con.prepareStatement(sql);
			psmt.setInt(1, course_id);
			rs=psmt.executeQuery();
			rs.next();
			mdto.setM_title(rs.getString(1)); 
			mdto.setRegion(rs.getString(2));
			System.out.println(mdto.getM_title());
			System.out.println(mdto.getRegion());
		}catch (Exception e) {
			System.out.println("getNickname err");
			e.printStackTrace();
		}
		return mdto;
	}
}
