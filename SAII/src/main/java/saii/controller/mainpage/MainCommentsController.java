package saii.controller.mainpage;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import saii.domain.mainCommentsDAO;
import saii.domain.memberDAO;
import saii.dto.mainCommentsDTO;
import saii.dto.memberDTO;

@WebServlet("/maincomments")
public class MainCommentsController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		String comments = req.getParameter("comments");
		String m_id = req.getParameter("m_id");
		JSONObject jobj = new JSONObject();
		
		memberDAO memdao = new memberDAO();
		memberDTO memdto = memdao.userinfo(req.getSession().getAttribute("UserId").toString());
		
		mainCommentsDTO mcdto = new mainCommentsDTO();
		mcdto.setNickname(memdto.getNickname());
		mcdto.setComments(comments);
		mcdto.setM_id(m_id);
		
		mainCommentsDAO mcdao = new mainCommentsDAO();
		mcdao.writeComments(mcdto);
		
		List<mainCommentsDTO> mainCommentsLists = mcdao.selectComments(m_id);
		jobj.put("mainCommentsLists", mainCommentsLists);
		
		memdao.close();
		mcdao.close();
		
		resp.setContentType("application/x-json; charset=utf-8");
		resp.getWriter().print(jobj);
	}
}
