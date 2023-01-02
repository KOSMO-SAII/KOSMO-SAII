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
    public @ResponseBody String crawl(){
        WebDriver webDriver;
        System.setProperty("webdriver.chrome.driver", "src/main/resources/py/chromedriver.exe");
        webDriver = new ChromeDriver();

        webDriver.get("https://www.google.co.kr/imghp?hl=ko&tab=wi&authuser=0&ogbl");
        WebElement element = webDriver.findElement(By.name("q"));
        element.sendKeys("nell");
        element.sendKeys(Keys.RETURN);

        List<WebElement> elements = webDriver.findElements(By.className("rg_i"));
        System.out.println(elements.size());
        int count = 0;
        for(WebElement e : elements){
            e.click();
            List<WebElement> img = webDriver.findElements(By.className("n3VNCb KAlRDb"));
            System.out.println(img.get(0).getAttribute("src"));

            count++;
            if(count > 3)
                break;
        }

        return "crawled";
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
    public String list(Optional<Integer> page, Model model){
        System.out.println("controller");

        model.addAttribute("maxPage", 5);

        return "board_festival/festival";
    }


}
