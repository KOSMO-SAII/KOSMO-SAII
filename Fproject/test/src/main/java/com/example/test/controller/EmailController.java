package com.example.test.controller;

import com.example.test.entity.Account;
import com.example.test.repository.AccountRepository;
import com.example.test.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

@Controller
@RequiredArgsConstructor
public class EmailController extends HttpServlet {

    private final EmailService emailService;
    private final AccountRepository accountRepository;
    String authCode = "";

    @RequestMapping("/m")
    public String mailConfirm() throws MessagingException, UnsupportedEncodingException {

        authCode = emailService.sendEmail("goddlsdurgkf@naver.com");
        Account account = new Account();
        accountRepository.save(account);
        return "mailCheck";
    }

    @RequestMapping("/confirm")
    public @ResponseBody String codeCheck(HttpServletRequest req){
        String code = req.getParameter("code");
        return code.equals(authCode) ? "true" : "false";
    }

    @RequestMapping("/{code}")
    public @ResponseBody String code(@PathVariable String code){
        String str = code.concat(authCode);
        return str;
    }
}
