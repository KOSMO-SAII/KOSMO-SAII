package com.example.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CSBoardController {
    @RequestMapping("/CSBoard")
    public  String cs(){
        return "board_customer/customerboard";
    }

    @RequestMapping("/festival")
    public  String festival(){
        return "board_festival/timeleaf";
    }

}
