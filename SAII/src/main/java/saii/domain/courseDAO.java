package saii.domain;

import java.sql.SQLException;
import java.util.ArrayList;

import saii.controller.JDBConnect;
import saii.dto.courseDTO;

public class courseDAO extends JDBConnect {

	public courseDAO() {
		super();
	}

	public courseDTO toCDTO(int order, String str) {

		courseDTO cdto = new courseDTO();
		String[] data = str.split("\\|");

		for (String s : data) {
			System.out.println(s + "\t");
		}

		cdto.setOrder(order);
		cdto.setCategory(data[0]);
		cdto.setAddress_id(data[1]);
		cdto.setAddress_name(data[2]);
		cdto.setRoad_address_name(data[3]);
		cdto.setPhone_number(data[4]);
		cdto.setPlace_name(data[5]);
		cdto.setPlace_url(data[6]);
		cdto.setX(data[7]);
		cdto.setY(data[8]);
		cdto.setMemo(data[9]);

		return cdto;
	}
	// 1 2 3 4 5 6 7 8 9 10 11
	// 코스아이디,코스순서,카테고리,주소아이디,주소,도로명주소,전화번호,장소이름,홈페이지,x,y,메모

	public void insertCourse(ArrayList<courseDTO> cdtos) {

		String front = "INSERT INTO COURSE_DATA(COURSE_ID, COURSE_ORDER, CATEGORY, ADDRESS_ID, ADDRESS_NAME, ROAD_ADDRESS_NAME, PHONENUMBER, PLACE_NAME, PLACE_URL, X, Y, MEMO)"
					+ " VALUES(";
		String start = "COURSE_SEQ.NEXTVAL,?,?,?,?,?,?,?,?,?,?,?)";
		String end = "COURSE_SEQ.CURRVAL,?,?,?,?,?,?,?,?,?,?,?)";
		
		String query = front+start;

		for (courseDTO cdto : cdtos) {
			insertData(query,cdto);
			query = front+end;
		}
	}

	public void insertData(String query,courseDTO cdto) {

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
}
