package com.example.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ReviewPlaceController {

    @RequestMapping("/reviewP")
    public String reviewP(){
        return "review/reviewPlace";
    }

    @RequestMapping("/reviewPv")
    public String reviewPv(){
        return "review/reviewPlaceView";
    }


}
