package saii.controller.mypage;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

import saii.domain.courseDAO;
import saii.domain.mainboardDAO;
import saii.domain.memberDAO;
import saii.dto.mainboardDTO;
import saii.dto.memberDTO;

@WebServlet("/upload")
public class uploadController extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			String saveDirectory = req.getServletContext().getRealPath("./Storage");
			ServletContext application = getServletContext();
			int maxPostSize = Integer.parseInt(application.getInitParameter("maxPostSize"));
			String encoding = "UTF-8";
			MultipartRequest mr = new MultipartRequest(req, saveDirectory, maxPostSize, encoding);
			String fileName = mr.getFilesystemName("o_profile_img");
			String ext = fileName.substring(fileName.lastIndexOf("."));
			String date = "file" + System.currentTimeMillis();
			String newfileName = date + ext;
			File oldFile = new File(saveDirectory + File.separator + fileName);
			File newFile = new File(saveDirectory + File.separator + newfileName);
			oldFile.renameTo(newFile);
			memberDTO dto = new memberDTO();

			dto.setId(mr.getParameter("id"));
			dto.setO_profile_img(fileName);
			dto.setN_profile_img(newfileName);

			memberDAO dao = new memberDAO();
			int result = dao.updateFile(dto);
			if (result != 1) {
				System.out.println("파일업로드 실패");
			}
			mainboardDAO mdao = new mainboardDAO();
			HttpSession session =req.getSession();
			String nick = (String) session.getAttribute("nickname");
			
			ArrayList<mainboardDTO> mdtos = mdao.getmylist(nick);
			courseDAO cdao = new courseDAO();
			List<Map<String,String>> list = new Vector<Map<String, String>>();
			for(mainboardDTO mdto : mdtos) {
				DateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd");
				
				HashMap<String, String> hmap = new HashMap<>();
				hmap.put("m_title", mdto.getM_title());
				hmap.put("m_id", mdto.getM_id());
				hmap.put("region", mdto.getRegion());
				hmap.put("p_name", cdao.getPlaceNames(mdto.getCourse_id()));
				hmap.put("course_id", mdto.getCourse_id());
				hmap.put("nickname", mdto.getNickname());
				hmap.put("m_postdate", sdFormat.format(mdto.getM_postdate()));
				hmap.put("count", Integer.toString(cdao.mylistcount(Integer.parseInt(mdto.getCourse_id()))));
			
				
				// 각 정보 속성값에 저장하기
				list.add(hmap);
			}
			
			ArrayList<mainboardDTO> fdtos = mdao.myfavolist(nick);
			List<Map<String,Object>> flist = new Vector<Map<String, Object>>();
			for(mainboardDTO fdto : fdtos) {
				HashMap<String, Object> fmap = new HashMap<>();
				DateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd");
				
				fmap.put("m_id", fdto.getM_id());
				fmap.put("m_title", fdto.getM_title());
				fmap.put("region", fdto.getRegion());
				fmap.put("course_id", fdto.getCourse_id());
				fmap.put("nickname", fdto.getNickname());
				fmap.put("m_postdate", fdto.getM_postdate());
				fmap.put("visitcount", fdto.getVisitcount());
				fmap.put("profile", dao.favoprofile(fdto.getM_id()));
				fmap.put("pname", cdao.getPlaceNames(fdto.getCourse_id()));
				fmap.put("count", Integer.toString(cdao.mylistcount(Integer.parseInt(fdto.getCourse_id()))));
				
				flist.add(fmap);
			}			
			
			req.setAttribute("list", list);
			req.setAttribute("dto", dao.userinfo(dto.getId()));	
			req.setAttribute("flist", flist);			
			req.getRequestDispatcher("saii/mypage/myPage.jsp").forward(req, resp);
		} catch (Exception e) {
			System.out.println("파일올리다가 실패");
			e.printStackTrace();
		}

	}

}
