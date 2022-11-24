package com.japc.controller;

import com.japc.entity.Memo;
import com.japc.service.MemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MemoController {

    @Autowired
    public MemoService memoService;
    
    @RequestMapping(value="/")
    public String root(){

        Memo memo = new Memo();
        memo.setMemo_text("Adfadsfaf");
        memoService.saveMemo(memo);
                
        return "메모오";
    }

}
