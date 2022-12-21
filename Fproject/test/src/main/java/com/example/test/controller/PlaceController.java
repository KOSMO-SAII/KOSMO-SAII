package com.example.test.controller;

import com.example.test.service.PlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class PlaceController {

    @Autowired
    PlaceService placeService;

    @RequestMapping("place/list")
    public String list(Model model){
        model.addAttribute("list",placeService);

        return "";
    }
}
