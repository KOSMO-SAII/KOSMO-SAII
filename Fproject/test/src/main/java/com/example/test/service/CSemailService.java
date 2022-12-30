package com.example.test.service;

import com.example.test.config.EmailDTO;
import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor

public class CSemailService {
    private JavaMailSender emailSender;

    public void sendSimpleMessage(EmailDTO mailDto) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(mailDto.getAddress());
        message.setTo("hewn0505@gmail.com");
        message.setSubject(mailDto.getTitle());
        message.setText(mailDto.getContent());
        emailSender.send(message);

    }
}
