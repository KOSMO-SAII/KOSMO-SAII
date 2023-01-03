package com.example.test.controller;

import com.example.test.service.CourseService;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@Controller
public class Maincontroller extends HttpServlet {

    @Autowired
    private CourseService courseService;

    @GetMapping("/")
    public String MainPage(Model model){
        PageRequest pageRequest = PageRequest.of(0,3);
        model.addAttribute("lists",courseService.getCard());
        return "Mainpage";
    }

    @RequestMapping("/option")
    public String option(){
        return "courseOption";
    }

    @RequestMapping("/tour")
    public  String tour(){
        return "tour";
    }

    @RequestMapping("/pop")
    public String pop(){
        return "popup";
    }

    @RequestMapping("/slider")
    public  String slider(){
        return "slider";
    }

    @RequestMapping("/festivalview/{id}")
    public String festivalview(@PathVariable int id, Model model){
        model.addAttribute("id",id);
        return "board_festival/festival_view";
    }


    @RequestMapping("/festival")
    public String list(Optional<Integer> page, Model model){

        model.addAttribute("start",1);
        model.addAttribute("end",10);

        return "board_festival/festival";
    }


}
