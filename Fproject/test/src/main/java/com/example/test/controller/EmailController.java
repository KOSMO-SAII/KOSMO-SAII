package com.example.test.controller;

import com.example.test.domain.Account;
import com.example.test.repository.AccountRepository;
import com.example.test.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.connector.Request;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.mail.Message;
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

    @RequestMapping("/email")
    public String sendEmail(String email, Model model, RedirectAttributes attributes){

        email = "goddlsdurgkf@naver.com";
        Account account = accountRepository.findByEmail(email);

        if(account == null){
            model.addAttribute("error", "유효한 이메일 주소가 아닙니다.");
            return "/";
        }

//        if(!account.canSend()){
//            model.addAttribute("error","로그인한시간");
//            return "/";
//        }

        emailService.sendLoginLink(account);
        attributes.addFlashAttribute("message", "이메일 인증 메일을 발송했습니다.");

        return "/";
    }

    @RequestMapping("/m")
    public String mailConfirm() throws MessagingException, UnsupportedEncodingException {

        authCode = emailService.sendEmail("goddlsdurgkf@naver.com");
        return "mailCheck";
    }

    @RequestMapping("/confirm")
    public @ResponseBody String codeCheck(HttpServletRequest req){
        String code = req.getParameter("code");
        return code.equals(authCode) ? "true" : "false";
    }
}
