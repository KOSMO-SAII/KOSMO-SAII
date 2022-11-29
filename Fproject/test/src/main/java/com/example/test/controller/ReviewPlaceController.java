package com.example.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ReviewPlaceController {

    @RequestMapping("/review")
    public  String review(){
        return "review/reviewPlace";
    }


}
