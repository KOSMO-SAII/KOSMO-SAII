package saii.domain;

import java.util.List;
import java.util.Vector;

import javax.servlet.annotation.WebServlet;

import saii.controller.JDBConnect;
import saii.dto.mainCommentsDTO;

public class mainCommentsDAO extends JDBConnect {
	public mainCommentsDAO() {
		super();
	}
	
	public List<mainCommentsDTO> selectComments(String m_id){
		List<mainCommentsDTO> bl = new Vector<mainCommentsDTO>();
		String sql = "select * from (select rownum as pnum, c.* from (select * from main_comments) c "
				   + "where m_id = ? "
				   + "order by c_id desc)";
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1, m_id);
			rs = psmt.executeQuery();
			while(rs.next()) {
				mainCommentsDTO dto = new mainCommentsDTO();
				dto.setC_id(rs.getString("c_id"));
				dto.setNickname(rs.getString("nickname"));
				dto.setComments(rs.getString("comments"));
				dto.setC_postdate(rs.getDate("c_postdate"));
				dto.setM_id(rs.getString("m_id"));
				dto.setN_profile_img(rs.getString("n_profile_img"));
				
				bl.add(dto);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return bl;
	}
	
	public int writeComments(mainCommentsDTO dto) {
		int rs = 0;
		String sql = "insert into main_comments(c_id, nickname, comments, m_id, n_profile_img) "
				   + "values(seq_mcomments_num.nextval, ?, ?, ?, ?)";
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1, dto.getNickname());
			psmt.setString(2, dto.getComments());
			psmt.setString(3, dto.getM_id());
			psmt.setString(4, dto.getN_profile_img());
			rs = psmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return rs;
	}
	
	public int deleteComments(String c_id) {
		int result = 0;
		try {
			String sql = "delete from main_comments where c_id = ?";
			psmt = con.prepareStatement(sql);
			psmt.setString(1, c_id);
			result = psmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
