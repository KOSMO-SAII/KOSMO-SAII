package saii.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import saii.controller.JDBConnect;
import saii.dto.CommentDTO;
import saii.dto.reviewboardDTO;


public class reviewboardDAO extends JDBConnect {
	public reviewboardDAO() {
		super();
	}

	public int selectCount(Map<String, Object> map) {
		int totalCount = 0;
		String query = "SELECT COUNT(*) FROM REVIEW_BOARD";
		if(map.get("categoryType")!=null) {
			query += " WHERE r_category = '" + map.get("categoryType") + "'";
		}
		if(map.get("searchStr") != null) {
			query += " AND " + map.get("searchType") + " LIKE '%" + map.get("searchStr") + "%'";
		}
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			rs.next();
			totalCount = rs.getInt(1);
		} catch (Exception e) {
			System.out.println("게시물 카운트 중 에러");
			e.printStackTrace();
		}
		return totalCount;
	}

	public List<reviewboardDTO> selectListPage(Map<String, Object> map) {
		List<reviewboardDTO> bl = new Vector<reviewboardDTO>();
		String query = "SELECT * FROM (SELECT ROWNUM AS PNUM, S.* FROM ( "
				+ "      SELECT * FROM REVIEW_BOARD ";
		if(map.get("categoryType")!=null) {
			query += " WHERE r_category = '" + map.get("categoryType") + "'";
		}
		if(map.get("searchStr") != null) {
			query += " AND " + map.get("searchType") + " LIKE '%" + map.get("searchStr") + "%'";
		}
		query += "      ORDER BY r_id DESC";
		query += "   ) S" + ")" + "WHERE PNUM BETWEEN ? AND ?";

		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, map.get("start").toString());
			psmt.setString(2, map.get("end").toString());
			rs = psmt.executeQuery();
			while (rs.next()) {
				reviewboardDTO dto = new reviewboardDTO();
				dto.setR_id(rs.getInt("r_id"));
				dto.setR_category(rs.getString("r_category"));
				dto.setR_title(rs.getString("r_title"));
				dto.setContent(rs.getString("content"));
				dto.setNickname(rs.getString("nickname"));
				dto.setR_postdate(rs.getDate("r_postdate"));
				dto.setO_file(rs.getString("o_file"));
				dto.setN_file(rs.getString("n_file"));
				dto.setVisitcount(rs.getInt("visitcount"));
				bl.add(dto);
			}
		} catch (Exception e) {
			System.out.println("게시물 목록 읽기중 에러");
			e.printStackTrace();
		}

		return bl;
	}

	public int insertWrite(reviewboardDTO dto) {
		int result = 0;
		try {
			String query = "INSERT INTO REVIEW_BOARD ( r_id, r_category, r_title, content, nickname, o_file, n_file) "
					+ " VALUES ( " + " seq_board_num.NEXTVAL,?,?,?,?,?,?)";
			psmt = con.prepareStatement(query);
			psmt.setString(1, dto.getR_category());
			psmt.setString(2, dto.getR_title());
			psmt.setString(3, dto.getContent());
			psmt.setString(4, dto.getNickname());
			psmt.setString(5, dto.getO_file());
			psmt.setString(6, dto.getN_file());
			result = psmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("게시물 입력 중 예외");
			e.printStackTrace();
		}
		return result;
	}

	public void updateVisitCount(Integer r_id) {
		String query = "UPDATE REVIEW_BOARD SET " + " visitcount=visitcount+1 " + " WHERE r_id=?";
		try {
			psmt = con.prepareStatement(query);
			psmt.setInt(1, r_id);
			psmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("게시물 조회수 증가 중 예외");
			e.printStackTrace();
		}
	}

	public reviewboardDTO selectView(Integer r_id) {
		reviewboardDTO dto = new reviewboardDTO();
		String query = "SELECT * FROM REVIEW_BOARD WHERE r_id=?";
		try {
			psmt = con.prepareStatement(query);
			psmt.setInt(1, r_id);
			rs = psmt.executeQuery();

			if (rs.next()) {
				dto.setR_id(rs.getInt(1));
				dto.setR_category(rs.getString(2));
				dto.setR_title(rs.getString(3));
				dto.setContent(rs.getString(4));
				dto.setNickname(rs.getString(5));
				dto.setR_postdate(rs.getDate(6));
				dto.setVisitcount(rs.getInt(7));
				dto.setO_file(rs.getString(8));
				dto.setN_file(rs.getString(9));
			}
		} catch (Exception e) {
			System.out.println("게시물 상세보기 중 예외");
			e.printStackTrace();
		}
		return dto;
	}

	public int deletePost(int r_id) {
		int result = 0;
		try {
			String query = "DELETE FROM REVIEW_BOARD WHERE r_id=?";
			psmt = con.prepareStatement(query);
			psmt.setInt(1, r_id);
			result = psmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("게시물 삭제 중 예외");
			e.printStackTrace();
		}
		return result;
	}

	public int updatePost(reviewboardDTO dto) {
		int result = 0;
		try {
			String query = "UPDATE REVIEW_BOARD"
						+ " SET r_title=?, content=?, o_file=?, n_file=? "
						+ "WHERE r_id=?";
			psmt = con.prepareStatement(query);
			psmt.setString(1, dto.getR_title());
		
			psmt.setString(2, dto.getContent());
			psmt.setString(3, dto.getO_file());
			psmt.setString(4, dto.getN_file());
			psmt.setInt(5, dto.getR_id());

			result = psmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("게시물 수정 중 예외");
			e.printStackTrace();
		}
		return result;
	}
	
	public ArrayList<CommentDTO> listComment(String r_id) {
		
		try {
			System.out.println(r_id);
			// 부모글 번호를 조건으로 받기
			String query = "select c.*, m.nickname "
					+ "from comment_board c, member m, review_board r "
					+ "where board_no = r_id and m.id = cmt_id "
					+ "and board_no = ? "
					+ "order by cmt_no asc";
			
			psmt = con.prepareStatement(query);
			psmt.setString(1, r_id);
			
			rs = psmt.executeQuery();
			
			ArrayList<CommentDTO> commentLists = new ArrayList<CommentDTO>();
			
			while ( rs.next() ) {
				
				CommentDTO dto = new CommentDTO();
				
				dto.setCmt_no(rs.getString("cmt_no"));
				System.out.println(dto.getCmt_no());
				dto.setCmt_content(rs.getString("cmt_content"));
				dto.setCmt_id(rs.getString("cmt_id"));
				dto.setCmt_regdate(rs.getString("cmt_regdate"));
				dto.setBoard_no(rs.getString("board_no"));
				dto.setNickname(rs.getString("nickname"));
				
				commentLists.add(dto);
				
			}
			
			return commentLists;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public int addComment(CommentDTO dto) {
		int result = 0;
		try {
			
			String sql = "insert into comment_board (cmt_no, cmt_id, cmt_content, cmt_regdate, board_no)"
					+ " values (seq_board_num.NEXTVAL, ?, ?, default, ?)";
			
			psmt = con.prepareStatement(sql);
			
			psmt.setString(1, dto.getCmt_id());
			psmt.setString(2, dto.getCmt_content());
			psmt.setString(3, dto.getBoard_no());
			
			result = psmt.executeUpdate(); // 성공시 1 실패시 0
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public int delComment(String cmt_no) {
		int result = 0;
		try {
			String query = "delete from comment_board where cmt_no = ?";
			psmt = con.prepareStatement(query);
			psmt.setString(1, cmt_no);
			result = psmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public void delAllComment(String r_id) {
		try {
			String query = "delete from comment_board where board_no=?";
			psmt = con.prepareStatement(query);
			psmt.setString(1, r_id);
			psmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
}
