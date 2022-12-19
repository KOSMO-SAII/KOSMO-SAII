package com.example.test.controller;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.python.core.PyFunction;
import org.python.core.PyInteger;
import org.python.core.PyObject;
import org.python.util.PythonInterpreter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/")
    public String MainPage(){
        return "Mainpage";
    }
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

    @RequestMapping("/crawl")
    public String crawl(String str){
        WebDriver webDriver;
        System.setProperty("webdriver.chrome.driver", "src/main/resources/py/chromedriver.exe");
        webDriver = new ChromeDriver();

        webDriver.get("https://www.google.co.kr/imghp?hl=ko&tab=wi&authuser=0&ogbl");
        WebElement element = webDriver.findElement(By.name("q"));
        element.sendKeys(str);
        element.sendKeys(Keys.RETURN);

        List<WebElement> elements = webDriver.findElements(By.className("rg_i Q4LuWd"));
        int count = 1;
        for(WebElement e : elements){
            List<WebElement> img = webDriver.findElements(By.className("n3VNCb KAlRDb"));


        }


        return null;
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

    @RequestMapping("/festivalview/{id}")
    public String festivalview(@PathVariable int id, Model model){
        model.addAttribute("id",id);
        return "board_festival/festival_view";
    }

    @RequestMapping("/festival")
    public  String festival(){
        return "board_festival/festival";
    }
}
