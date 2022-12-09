package com.example.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

@Controller
public class Maincontroller extends HttpServlet {

    @RequestMapping("/test3")
    public String test3()
    {return "test3";}
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

    @GetMapping("/test2")
    public String test2(Model model, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        model.addAttribute("title",req.getParameter("title"));
        model.addAttribute("region",req.getParameter("region"));

        return "test2";
    }

    @GetMapping("/test")
    public String get(Model model, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Map<String,String >> list =new Vector<Map<String,String >>();
        for(int i=0;i<2;i++){
        Map<String,String> map =new HashMap<String, String>();
        map.put("name","name");
        map.put("age","age");
        list.add(map);
        }
        //req.setAttribute("list",list);
        model.addAttribute("data","get");
        return "test";
    }

    @PostMapping("/test")
    public String post(Model model){
        model.addAttribute("data","post");
        return "test";
    }
}
