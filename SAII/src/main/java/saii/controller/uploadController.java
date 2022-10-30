package saii.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;

import saii.domain.memberDAO;
import saii.dto.memberDTO;

@WebServlet("/upload")
public class uploadController extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			String saveDirectory = req.getServletContext().getRealPath("/Storage");
			ServletContext application = getServletContext();
			int maxPostSize = Integer.parseInt(application.getInitParameter("maxPostSize"));
			String encoding = "UTF-8";
			MultipartRequest mr = new MultipartRequest(req, saveDirectory, maxPostSize, encoding);
			String fileName = mr.getFilesystemName("o_profile_img");
			String ext = fileName.substring(fileName.lastIndexOf("."));
			String date = "file" + System.currentTimeMillis();
			String newfileName = date + ext;
			System.out.println(mr.getParameter("id") + " " + date);
			File oldFile = new File(saveDirectory + File.separator + fileName);
			File newFile = new File(saveDirectory + File.separator + newfileName);
			oldFile.renameTo(newFile);
			System.out.println(saveDirectory);
			memberDTO dto = new memberDTO();

			dto.setId(mr.getParameter("id"));
			dto.setO_profile_img(fileName);
			dto.setN_profile_img(newfileName);

			memberDAO dao = new memberDAO();
			int result = dao.updateFile(dto);
			System.out.println(dto.getN_profile_img());
			if (result == 1) {
				System.out.println("파일이 드러가썽");
			} else {
				System.out.println("파일 실패ㅜ");
			}
			
			req.setAttribute("dto", dao.userinfo(dto.getId()));	
			
			req.getRequestDispatcher("saii/myPage.jsp").forward(req, resp);
		} catch (Exception e) {
			System.out.println("파일올리다가 실패");
			e.printStackTrace();
		}

	}

}
