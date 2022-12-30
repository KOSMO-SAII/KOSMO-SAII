package com.example.test.controller;

import com.example.test.config.EmailDTO;
import com.example.test.service.CSemailService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CSemailController {
    private final CSemailService emailService;

    public CSemailController(CSemailService emailService) {
        this.emailService = emailService;
    }

    @GetMapping("/mail/send")
    public String main() {
        return "CSEmail/SendMail";
    }

    @PostMapping("/mail/send")
    public String sendMail(EmailDTO mailDto) {
        emailService.sendSimpleMessage(mailDto);
        System.out.println("메일 전송 완료");
        return "AfterMail.html";
    }
}
