package saii.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import saii.domain.goodDAO;
import saii.domain.mainboardDAO;
import saii.dto.mainboardDTO;

@WebServlet("/good")
public class GoodController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		String m_id = req.getParameter("m_id");
		String nickname = req.getParameter("nickname");
		
		JSONObject jobj = new JSONObject();
		goodDAO gdao = new goodDAO();
		
		if(gdao.goodWhether(m_id, nickname)) {
			gdao.cancelGood(m_id, nickname);
			jobj.put("heart", "cancel");
		}else {
			gdao.pushGood(m_id, nickname);
			jobj.put("heart", "push");
		}

		mainboardDAO mdao = new mainboardDAO();
		mainboardDTO mdto = mdao.selectView(m_id);
		
		jobj.put("goodcount", mdto.getGoodcount());
		
		gdao.close();
		mdao.close();
		
		resp.setContentType("application/x-json; charset=utf-8");
		resp.getWriter().print(jobj);
	}
}
