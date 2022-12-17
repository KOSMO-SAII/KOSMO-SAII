package com.example.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ReviewPlaceController {

    @RequestMapping("/Place")
    public String reviewP(){
        return "reviews/reviewPlace";
    }

    @RequestMapping("/PlaceView")
    public String reviewPv(){
        return "reviews/reviewPlaceView";
    }

    @RequestMapping("/placeMore")
    public String placeMore(){
        return "reviews/placeMore";
    }

}
