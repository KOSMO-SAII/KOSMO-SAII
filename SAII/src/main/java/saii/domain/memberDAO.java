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
	
	//파일 목록을 반환합니다.
	public List<memberDTO> memList() {
		List<memberDTO> fileList = new Vector<memberDTO>();
		//쿼리문 작성
		String query = "SELECT * FROM myfile ORDER BY id DESC";
		try {
			psmt = con.prepareStatement(query); //쿼리 준비
			rs = psmt.executeQuery();
			while(rs.next()) {   //목록 안의 파일 수만큼 반복
				//DTO에 저장
				memberDTO dto = new memberDTO();
				dto.setId(rs.getString(1));
				dto.setPw(rs.getString(2));
				dto.setNickname(rs.getString(3));
				dto.setName(rs.getString(4));
				dto.setBirthday(rs.getDate(5));
				dto.setSex(rs.getString(6));
				dto.setPhone(rs.getString(7));
				dto.setEmail(rs.getString(8));
				dto.setAddress(rs.getString(9));
				dto.setO_profile_img(rs.getString(10));
				dto.setN_profile_img(rs.getString(11));
				
				fileList.add(dto);   //목록에 추가
				
			}
		}catch(Exception e) {
				System.out.println("SELECT 시 예외 발생");
				e.printStackTrace();
		}
		return fileList;
				
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
			System.out.println(dto.getId());
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
			System.out.println(dto.getO_profile_img());
			result = psmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("파일업로드 중 에러");
			e.printStackTrace();
		}
		return result;
	}
}
