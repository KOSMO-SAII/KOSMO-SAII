package com.example.test.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.example.test.domain.MainBoardDTO;
import com.example.test.repository.MainBoardDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
public class MainBoardController {
	@Autowired
	MainBoardDAO mainBoardDAO;
	
	@GetMapping(value="/mainboard_list")
	public String mainBoardList(Model model) {
		System.out.println("list");
		model.addAttribute("list", mainBoardDAO.listDao());
		return "MainBoardList";
	}
	
	@GetMapping(value="/mainboard_view")
	public String mainBoardView(HttpServletRequest req, Model model) {
		System.out.println("view");
		mainBoardDAO.plusviewcountDao(req.getParameter("post_id"));
		model.addAttribute("view", mainBoardDAO.viewDao(req.getParameter("post_id")));
		return "MainBoardView";
	}
	
	/* 코스게시판 글 작성
	@GetMapping(value="/mainboard_write_form")
	public String mainboardWrite(@ModelAttribute("mainBoardDTO") MainBoardDTO mainBoardDTO) {
		System.out.println("writeform");
		return "MainBoardWriteForm";
	}
	
	@PostMapping(value="/mainboard_write")
	public String mainBoardWriteForm(@Valid MainBoardDTO mainBoardDTO, HttpServletRequest req, Model model) {
		System.out.println("write");
		String course_id = mainBoardDTO.getCourse_id();
		String user_id = "25";
		String title = mainBoardDTO.getTitle();
		String region = mainBoardDTO.getRegion();
		mainBoardDAO.writeDao(course_id, user_id, title, region);
		return "redirect:/mainboard_list";
	}
	*/
}
