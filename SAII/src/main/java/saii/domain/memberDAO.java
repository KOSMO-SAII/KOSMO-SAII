package saii.domain;

import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import saii.controller.JDBConnect;
import saii.dto.memberDTO;

public class memberDAO extends JDBConnect {

	public int insertFile(memberDTO dto) {
		int applyResult = 0;
		try {
			String query = "INSERT INTO MEMBER( ID, PW, NICKNAME, NAME, BIRTHDAY,SEX, PHONE,EMAIL,ADDRESS, O_PROFILE_IMG, N_PROFILE_IMG)"
					+ "		VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
			psmt = con.prepareStatement(query);
			psmt.setString(1, dto.getId());
			psmt.setString(2, dto.getPw());
			psmt.setString(3, dto.getNickname());
			psmt.setString(4, dto.getName());
			psmt.setDate(5, dto.getBirthday());
			psmt.setString(6, dto.getSex());
			psmt.setString(7, dto.getPhone());
			psmt.setString(8, dto.getEmail());
			psmt.setString(9, dto.getAddress());
			psmt.setString(10, dto.getO_profile_img());
			psmt.setString(11, dto.getN_profile_img());
			
			applyResult = psmt.executeUpdate();			
		} catch (Exception e) {
			System.out.println("INSERT 중 예외 발생");
			e.printStackTrace();
		}
		return applyResult;
	}
	
	
	public boolean checkId(String id) {
		boolean result = false;
		try {
			String query = "SELECT ID FROM MEMBER WHERE ID=?";
			psmt = con.prepareStatement(query);
			psmt.setString(1,id);
			rs = psmt.executeQuery();
			
			if(!(rs.next())) {
				result = true;  //중복아닌경우
				
				rs.close();
				psmt.close();
				con.close();
				
			}
		} catch (Exception e) {
			System.out.println("아이디 중복체크 중 오류");
			e.printStackTrace();
		}
		return result;		
	}
	
	public boolean checkNick(String nickname) {
		boolean result = false;
		try {
			String query = "SELECT NICKNAME FROM MEMBER WHERE NICKNAME=?";
			psmt = con.prepareStatement(query);
			psmt.setString(1,nickname);
			rs = psmt.executeQuery();
			
			if(!(rs.next())) {
				result = true;  //중복아닌경우
				
				rs.close();
				psmt.close();
				con.close();
				
			}
		} catch (Exception e) {
			System.out.println("닉네임 중복체크 중 오류");
			e.printStackTrace();
		}
		return result;		
	}
	
	public boolean checkPassword(String id, String pw) {
		boolean result = false;
		String query = "SELECT * FROM MEMBER WHERE ID=? AND PW=?";
		
		try {
			psmt=con.prepareStatement(query);
			psmt.setString(1, id);
			psmt.setString(2, pw);
			rs = psmt.executeQuery();
			
			if((rs.next())) {
				result = true;
			}
			
		} catch (SQLException e) {
			System.out.println("checkpassword err");
			e.printStackTrace();
		}
		
		return result;
		
	}
	
	public memberDTO getmemberDTO(String uid, String upw) {
		memberDTO dto=new memberDTO();
		String query="SELECT * FROM MEMBER WHERE id=? AND pw=?";
		
		try {
			psmt=con.prepareStatement(query);
			psmt.setString(1, uid);
			psmt.setString(2, upw);
			rs=psmt.executeQuery();
			
			if(rs.next()) {
				dto.setId(rs.getString("id"));
				dto.setPw(rs.getString("pw"));
				dto.setName(rs.getString("name"));
				dto.setNickname(rs.getString("nickname"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return dto;
	}
	
	public memberDTO pass(String id, String pw) {
		memberDTO dto = new memberDTO();
		String query = "SELECT * FROM member WHERE id=? AND pw=?";
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, id);
			psmt.setString(2, pw);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				dto.setId(rs.getString("id"));
				dto.setPw(rs.getString("pw"));
				dto.setNickname(rs.getString("nickname"));
				dto.setName(rs.getString("name"));
				dto.setBirthday(rs.getDate("birthday"));
				dto.setSex(rs.getString("sex"));
				dto.setPhone(rs.getString("phone"));
				dto.setEmail(rs.getString("email"));
				dto.setAddress(rs.getString("address"));
				dto.setO_profile_img(rs.getString("o_profile_img"));
				dto.setN_profile_img(rs.getString("n_profile_img"));
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}
	
	public memberDTO userinfo(String id) {
		memberDTO dto = new memberDTO();
		String query = "SELECT * FROM MEMBER WHERE id=?";
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			if(rs.next()) {
				dto.setId(rs.getString("id"));
				dto.setPw(rs.getString("pw"));
				dto.setNickname(rs.getString("nickname"));
				dto.setName(rs.getString("name"));
				dto.setBirthday(rs.getDate("birthday"));
				dto.setSex(rs.getString("sex"));
				dto.setPhone(rs.getString("phone"));
				dto.setEmail(rs.getString("email"));
				dto.setAddress(rs.getString("address"));
				dto.setO_profile_img(rs.getString("o_profile_img"));
				dto.setN_profile_img(rs.getString("n_profile_img"));
				
			}
		} catch (Exception e) {
			System.out.println("멤버정보 가져오기 중 에러");
			e.printStackTrace();
		}
		return dto;
	}
	
	public int update(memberDTO dto) {
		int result = 0;
		try {
			String query = "UPDATE MEMBER SET PW=?,NICKNAME=?,PHONE=?,EMAIL=?,ADDRESS=?"
					+ " WHERE ID=?";
			psmt = con.prepareStatement(query);
			psmt.setString(1, dto.getPw());
			psmt.setString(2, dto.getNickname());
			psmt.setString(3, dto.getPhone());
			psmt.setString(4, dto.getEmail());
			psmt.setString(5, dto.getAddress());
			psmt.setString(6, dto.getId());
			result = psmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("회원정보 수정중 에러");
			e.printStackTrace();
		}
		return result;

	}
	
	public int updateFile(memberDTO dto) {
		int result = 0;
		try {
			String query = "UPDATE MEMBER SET O_PROFILE_IMG=?,N_PROFILE_IMG=?"
					+ " WHERE ID=?";
			psmt = con.prepareStatement(query);
			psmt.setString(1, dto.getO_profile_img());
			psmt.setString(2, dto.getN_profile_img());
			psmt.setString(3, dto.getId());
			result = psmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("파일업로드 중 에러");
			e.printStackTrace();
		}
		return result;
	}
	
	public memberDTO findId(String member_name, String member_phone) {
	     memberDTO dto = new memberDTO();
		try {
			String query = "SELECT * FROM MEMBER WHERE NAME=? and PHONE=?";
			
			psmt=con.prepareStatement(query);
			psmt.setString(1, member_name);
			psmt.setString(2, member_phone);
			
			rs=psmt.executeQuery();
			if(rs.next()) {
				dto.setId(rs.getString("id"));
				dto.setPw(rs.getString("pw"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return dto;
	}
	
	public String findPw(String member_id, String member_phone) {
	     memberDTO dto = new memberDTO();
		try {
			String query = "SELECT * FROM MEMBER WHERE ID=? and PHONE=?";
			
			psmt=con.prepareStatement(query);
			psmt.setString(1, member_id);
			psmt.setString(2, member_phone);
			
			rs=psmt.executeQuery();
			if(rs.next()) {
				dto.setId(rs.getString("id"));
				dto.setPw(rs.getString("pw"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return dto.getPw();
	}
	
	public int delete(String id) {
		int result=0;
		
		try {
			String query = "DELETE FROM MEMBER"
					+ " WHERE ID=?";
			
			psmt = con.prepareStatement(query);
			psmt.setString(1, id);
			result = psmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("회원탈퇴 실패");
			e.printStackTrace();
		}
		
		return result;
		
	}
	
	public String favoprofile(String m_id) {
		String result="";
		String sql = "SELECT N_PROFILE_IMG FROM MEMBER M, MAIN_BOARD B WHERE M.NICKNAME=B.NICKNAME AND B.M_ID=?";
		try {
			psmt=con.prepareStatement(sql);
			psmt.setString(1, m_id);
			rs=psmt.executeQuery();
			rs.next();
			result=rs.getString(1);
		} catch (Exception e) {
			System.out.println("favoprofile err");
			e.printStackTrace();
		}
		return result;
	}
}
