package saii.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import saii.domain.courseDAO;
import saii.domain.mainboardDAO;
import saii.domain.memberDAO;
import saii.domain.reviewboardDAO;

@WebServlet("/home")
public class startPageController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("dfdfd");
		req.getRequestDispatcher("/saii/startPage.jsp").forward(req, resp);
	}
}
