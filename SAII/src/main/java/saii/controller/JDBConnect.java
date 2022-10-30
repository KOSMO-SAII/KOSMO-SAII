package saii.controller;

import java.sql.*;

import javax.servlet.ServletContext;

public class JDBConnect {
	public Connection con;
	public Statement stmt;
	public PreparedStatement psmt;
	public ResultSet rs;
	
	public JDBConnect() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");			
			String url = "jdbc:oracle:thin:@localhost:1521:xe";			
			String id = "saii";
			String pw = "saii";			
			con = DriverManager.getConnection(url, id, pw);
			System.out.println("DB 연결 성공(기본)");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public JDBConnect(String driver, String url, String id, String pw) {
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, id, pw);
			System.out.println("DB 연결 성공(인자)");			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public JDBConnect(ServletContext application) {
		try {
			String driver = application.getInitParameter("OracleDriver");
			Class.forName(driver);
			
			String url = application.getInitParameter("OracleUrl");
			String id = application.getInitParameter("OracleId");
			String pw = application.getInitParameter("OraclePw");
			con = DriverManager.getConnection(url, id, pw);
			System.out.println("DB 연결 성공(초기화 매개 변수)");			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void close() {
		try {
			if(rs!=null) rs.close();
			if(stmt!=null) stmt.close();
			if(psmt!=null) psmt.close();
			if(con!=null) con.close();
			System.out.println("DB 연결 해제");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
