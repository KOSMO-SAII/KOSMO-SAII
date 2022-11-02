package saii.domain;

import java.lang.reflect.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import saii.controller.JDBConnect;
import saii.dto.courseDTO;
import saii.dto.mainboardDTO;

public class courseDAO extends JDBConnect {

	public courseDAO() {
		super();
	}

	public ArrayList<courseDTO> toCDTO(String[] str) {

		ArrayList<courseDTO> cdtos = new ArrayList<>();
		courseDTO cdto = new courseDTO();
		int order = 1;
		System.out.println(str);
		
		for (String s : str) {
			String[] data = s.split("\\|");
			System.out.println("tocdto " + s + "\t");
//			System.out.println(Arrays.toString(data));
//			System.out.println(data[0]);
//			
//			if(data.length<10) {
//				System.out.println("9");
//			}else if(data.length==10) {
//				System.out.println("10");
//			}
			cdto.setOrder(order++);
			cdto.setCategory(data[0]);
			cdto.setAddress_id(data[1]);
			cdto.setAddress_name(data[2]);
			cdto.setRoad_address_name(data[3]);
			cdto.setPhone_number(data[4]);
			cdto.setPlace_name(data[5]);
			cdto.setPlace_url(data[6]);
			cdto.setX(data[7]);
			cdto.setY(data[8]);
			if(data.length==10) {
				cdto.setMemo(data[9]);
				
			}
			
			cdtos.add(cdto);
			
			cdto = new courseDTO();
		}

		return cdtos;
	}
	// 1 2 3 4 5 6 7 8 9 10 11
	// 코스아이디,코스순서,카테고리,주소아이디,주소,도로명주소,전화번호,장소이름,홈페이지,x,y,메모

	public void insertCourse(ArrayList<courseDTO> cdtos) {
		
		String front = "INSERT INTO COURSE_DATA(COURSE_ID, COURSE_ORDER, CATEGORY, ADDRESS_ID, ADDRESS_NAME, ROAD_ADDRESS_NAME, PHONENUMBER, PLACE_NAME, PLACE_URL, X, Y, MEMO)"
				+ " VALUES(";
		String start = "COURSE_SEQ.NEXTVAL,?,?,?,?,?,?,?,?,?,?,?)";
		String end = "COURSE_SEQ.CURRVAL,?,?,?,?,?,?,?,?,?,?,?)";

		String query = front + start;

		for (courseDTO cdto : cdtos) {
			insertData(query, cdto);
			query = front + end;
		}
		
	}

	public void insertData(String query, courseDTO cdto) {
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setInt(1, cdto.getOrder());
			psmt.setString(2, cdto.getCategory());
			psmt.setString(3, cdto.getAddress_id());
			psmt.setString(4, cdto.getAddress_name());
			psmt.setString(5, cdto.getRoad_address_name());
			psmt.setString(6, cdto.getPhone_number());
			psmt.setString(7, cdto.getPlace_name());
			psmt.setString(8, cdto.getPlace_url());
			psmt.setString(9, cdto.getX());
			psmt.setString(10, cdto.getY());
			psmt.setString(11, cdto.getMemo());

			rs = psmt.executeQuery();
			System.out.println("insert data end");

		} catch (SQLException e) {
			System.out.println("insert Data err");
			e.printStackTrace();
		}

	}
	
	public ArrayList<courseDTO> getCourse(int course_id){
		
		ArrayList<courseDTO> cdtos = new ArrayList<>();
		String query = "SELECT * FROM COURSE_DATA WHERE COURSE_ID = ?";
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setInt(1, course_id);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				courseDTO cdto = new courseDTO();

				cdto.setCourse_id(Integer.toString(course_id));
				cdto.setOrder(rs.getInt(2));
				cdto.setCategory(rs.getString(3));
				cdto.setAddress_id(rs.getString(4));
				cdto.setAddress_name(rs.getString(5));
				cdto.setRoad_address_name(rs.getString(6));
				cdto.setPhone_number(rs.getString(7));
				cdto.setPlace_name(rs.getString(8));
				cdto.setPlace_url(rs.getString(9));
				cdto.setX(rs.getString(10));
				cdto.setY(rs.getString(11));
				cdto.setMemo(rs.getString(12));

				cdtos.add(cdto);
			}
			
		} catch (SQLException e) {
			System.out.println("getcourse err");
			e.printStackTrace();
		}		
		return cdtos;
	}
	
	public int getCurrentCourseId() {
		
		String query = "SELECT LAST_NUMBER FROM USER_SEQUENCES WHERE SEQUENCE_NAME = 'COURSE_SEQ'";
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			rs.next();
			return rs.getInt(1)-1;
		} catch (SQLException e) {
			System.out.println("get course id err");
			e.printStackTrace();
		}
		return 0;
	}
	
	
public String getPlaceNames(String course_id) {
		
		ArrayList<String> names = new ArrayList<>();
		String result = "";
		String sql = "SELECT PLACE_NAME FROM COURSE_DATA WHERE COURSE_ID = ? ORDER BY COURSE_ORDER";
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1, course_id);
			
			rs = psmt.executeQuery();
			while(rs.next()) {
				names.add(rs.getString(1));			
			}  //값이 names에 순서대로 저장됨.
						
			for(String name : names) {
				result += name + ",";
			}			
			
		} catch (SQLException e) {
			System.out.println("getPlaceName err");
			e.printStackTrace();
		}
		System.out.println(result);
		return result;
	}
}
