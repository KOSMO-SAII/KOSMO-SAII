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
		String sql = "select * from main_comments where m_id = ? order by c_id desc";
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
				
				bl.add(dto);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
				
		return bl;
	}
	
	public int writeComments(mainCommentsDTO dto) {
		int rs = 0;
		String sql = "insert into main_comments(c_id, nickname, comments, m_id) "
				   + "values(seq_memcomments_num.nextval(), ?, ?, ?)";
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1, dto.getNickname());
			psmt.setString(2, dto.getComments());
			psmt.setString(3, dto.getM_id());
			rs = psmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return rs;
	}
}
