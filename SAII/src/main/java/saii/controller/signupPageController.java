package saii.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;

import saii.domain.memberDAO;
import saii.dto.memberDTO;

@WebServlet("/signup")
public class signupPageController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		System.out.println("/signup doGet");
		req.getRequestDispatcher("/saii/signupPage.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			String saveDirectory = req.getServletContext().getRealPath("/Storage");// 저장할 디렉토리
			int maxPostSize = 1024 * 1000;
			String encoding = "UTF-8";
			// 1.MultipartRequest 객체 생성
			MultipartRequest mr = new MultipartRequest(req, saveDirectory, maxPostSize, encoding);
			String fileName = mr.getFilesystemName("pick");
			String ext = fileName.substring(fileName.lastIndexOf("."));
			String date = mr.getParameter("yy") + "-" + mr.getParameter("mm") + "-" + mr.getParameter("dd");
			String newFileName = date + ext;// 새로운 파일 이름("업로드 일시.확장자")

			System.out.println(mr.getParameter("id") + "   " + date);
			File oldFile = new File(saveDirectory + File.separator + fileName);
			File newFile = new File(saveDirectory + File.separator + newFileName);
			oldFile.renameTo(newFile);

			memberDTO dto = new memberDTO();

			dto.setId(mr.getParameter("id"));
			dto.setPw(mr.getParameter("pw"));
			dto.setNickname(mr.getParameter("nickname"));
			dto.setName(mr.getParameter("name"));
			dto.setSex(mr.getParameter("sex"));
			dto.setBirthday(Date.valueOf(date));
			dto.setO_profile_img(fileName);
			dto.setN_profile_img(newFileName);
			dto.setAddress(mr.getParameter("address"));
			dto.setEmail(mr.getParameter("email"));

			memberDAO dao = new memberDAO();
			int result = dao.insertFile(dto);
			dao.close();

			if (result == 1) {
				System.out.println("와 파일이 드렁갔당");
			} else {
				System.out.println(" 저런 안들어갔당");
			}

			// 회원가입 끝, 로그인 페이지로 이동
			resp.setContentType("text/html; charset=UTF-8");
			PrintWriter out = resp.getWriter();
			out.println(
					"<script language='javascript'>alert('회원가입을 축하합니다!!!'); location.href='http://localhost:8081/SAII/saii/loginPage.jsp';</script>");
			out.flush();
			req.getRequestDispatcher("/saii/loginPage.jsp").include(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("저런");
		}
	}
}
