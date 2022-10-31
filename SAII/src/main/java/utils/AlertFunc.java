package utils;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspWriter;


public class AlertFunc {
	public static void alertLocation(String msg, String url, JspWriter out){ //out 객체: JSP 페이지가 생성한 결과를 웹 브라우저에 전송해 주는 출력 스트림, JSP 페이지가 웹 브라우저에게 보내는 모든 정보는 out 객체를 통해서 전송됨.
		try {
			String script = "<script>"
						  + "    alert('" + msg + "');"
						  + "	 location.href='" + url + "';" //페이지 이동  location.href = "이동할 페이지 주소";
						  + "</script>";
			out.println(script);
		}
		catch(Exception e) {}
	}
	
	public static void alertBack(String msg, JspWriter out) {
		try {
			String script = "<script>"
						  + "    alert('" + msg + "');"
						  + "	 history.back();"
						  + "</script>";
			out.println(script);
		}
		catch(Exception e) {}
	}
	
	public static void alertLocation(HttpServletResponse resp, String msg, String url){
		try {
			resp.setContentType("text/html;charset=UTF-8");
			PrintWriter writer = resp.getWriter();
			String script = "<script>"
						  + "    alert('" + msg + "');"
						  + "	 location.href='" + url + "';"
						  + "</script>";
			writer.println(script);
		}
		catch(Exception e) {}
	}
	
	public static void alertBack(HttpServletResponse resp, String msg) {
		try {
			resp.setContentType("text/html;charset=UTF-8");	
			PrintWriter writer = resp.getWriter();	//HttpServletResponse 인터페이스의 상위 인터페이스 ServletResponse의 getWriter() 메소드를 호출.
													//getWriter() 메소드는 java.io의 PrintWriter 클래스를 리턴 타입으로 한다.
			String script = "<script>"
						  + "    alert('" + msg + "');"
						  + "	 history.back();"
						  + "</script>";
			writer.println(script);
		}
		catch(Exception e) {}
	}
	
	
}
