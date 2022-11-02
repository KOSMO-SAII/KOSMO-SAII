package saii.domain;

import saii.controller.JDBConnect;
import saii.dto.goodDTO;
import saii.dto.mainboardDTO;

public class goodDAO extends JDBConnect {
	public goodDAO() {
		super();
	}
	
	public boolean goodWhether(String m_id, String nickname) {
		boolean result = true;
		String sql = "select count(*) from good where (m_id = ?) and (nickname = ?)";
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1, m_id);
			psmt.setString(2, nickname);
			rs = psmt.executeQuery();
			if(rs.next()) {
				if(rs.getInt(1) == 0) {
					result = false;
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public int pushGood(String m_id, String nickname) {
		int rs = 0;
		String sql = "insert into good values(?, ?)";
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1, m_id);
			psmt.setString(2, nickname);
			rs = psmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	public int cancelGood(String m_id, String nickname) {
		int rs = 0;
		String sql = "delete from good where (m_id = ?) and (nickname = ?)";
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1, m_id);
			psmt.setString(2, nickname);
			rs = psmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return rs;
	}
}
