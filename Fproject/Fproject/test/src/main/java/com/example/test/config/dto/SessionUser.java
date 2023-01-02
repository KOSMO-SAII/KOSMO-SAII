package com.example.test.config.dto;

import com.example.test.entity.Member;
import lombok.Getter;

@Getter
public class SessionUser {
    private String name;
    private String email;
    private String picture;

    private String password;

    public SessionUser(Member member) {
        this.name = member.getName();
        this.email = member.getEmail();
        this.picture = member.getPicture();
        this.password = member.getLoginPw();
    }
}