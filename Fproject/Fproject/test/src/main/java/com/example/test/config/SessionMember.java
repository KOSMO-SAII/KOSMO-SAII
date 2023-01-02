package com.example.test.config;

import com.example.test.constant.Role;
import com.example.test.entity.Member;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@RequiredArgsConstructor
@ToString
public class SessionMember implements Serializable {

    private Long memberId;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    private String address;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;
    private String email;
    private String gender;
    private String loginId;
    private String loginPw;
    private String nProfileImg;
    private String name;
    private String nickname;
    private String oProfileImg;
    private String phoneNumber;
    private String picture;
    private Role role;

    public SessionMember(Member member) {
        this.memberId = member.getMemberId();
        this.createDate=member.getCreateDate();
        this.updateDate=member.getUpdateDate();
        this.address=member.getAddress();
        this.birthday=member.getBirthday();
        this.email=member.getEmail();
        this.gender=member.getGender();
        this.loginId=member.getLoginId();
        this.loginPw=member.getLoginPw();
        this.nProfileImg=member.getNProfileImg();
        this.name=member.getName();
        this.nickname=member.getNickname();
        this.oProfileImg=member.getOProfileImg();
        this.phoneNumber=member.getPhoneNumber();
        this.role = member.getRole();
        this.picture=member.getPicture();

    }
}