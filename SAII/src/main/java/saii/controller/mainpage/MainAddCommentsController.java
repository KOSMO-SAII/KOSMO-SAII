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
import utils.AlertFunc;

@WebServlet("/addMainComments")
public class MainAddCommentsController extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		String m_id = req.getParameter("m_id");
		String comments = req.getParameter("comments");
		
		memberDAO memdao = new memberDAO();
		memberDTO memdto = memdao.userinfo(req.getSession().getAttribute("UserId").toString());
		
		mainCommentsDTO mcdto = new mainCommentsDTO();
		mcdto.setNickname(memdto.getNickname());
		mcdto.setM_id(m_id);
		mcdto.setComments(comments);
		mcdto.setN_profile_img(memdto.getN_profile_img());
		
		mainCommentsDAO mcdao = new mainCommentsDAO();
		int result = mcdao.writeComments(mcdto);
		
		memdao.close();
		mcdao.close();
		
		if(result == 1) {
			resp.sendRedirect("http://localhost:8081/SAII/view?m_id=" + m_id);
		}else {
			AlertFunc.alertBack(resp,"댓글 쓰기 실패");
		}
	}
}
