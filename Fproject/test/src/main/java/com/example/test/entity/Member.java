package com.example.test.entity;

import com.example.test.constant.Role;
import com.example.test.domain.MemberDTO;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Member extends BaseTime{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;
//    @CreatedDate
//    private LocalDateTime createDate;
//    @LastModifiedDate
//    private LocalDateTime updateDate;
    private String address;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;
    @Column(unique = true, nullable = false)
    private String email;
    private String gender;
    @Column(unique = true,nullable = false)
    private String loginId;
    @Column(nullable = false)
    private String loginPw;
    private String nProfileImg;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false, unique = true)
    private String nickname;
    private String oProfileImg;
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private Role role;

    private LocalDateTime createDate;

    @Builder
    public Member(Long memberId, String address,
                  Date birthday, String email, String gender, String loginId, String loginPw,
                  String nProfileImg, String name, String nickname, String oProfileImg,
                  String phoneNumber){
        this.memberId=memberId;
//        this.createDate=createDate;
//        this.updateDate=updateDate;
        this.address=address;
        this.birthday=birthday;
        this.email=email;
        this.gender=gender;
        this.loginId=loginId;
        this.loginPw=loginPw;
        this.nProfileImg=nProfileImg;
        this.name=name;
        this.nickname=nickname;
        this.oProfileImg=oProfileImg;
        this.phoneNumber=phoneNumber;
    }

    public static Member createMember(MemberDTO memberDTO, PasswordEncoder passwordEncoder) {
        Member member = new Member();

        member.setLoginId(memberDTO.getLoginId());
        member.setLoginPw(memberDTO.getLoginPw());
        String pw = passwordEncoder.encode(memberDTO.getLoginPw());
        member.setLoginPw(pw);
        member.setName(memberDTO.getName());
        member.setNickname(memberDTO.getNickname());
        member.setAddress(memberDTO.getAddress());
        member.setBirthday(memberDTO.getBirthday());
        member.setEmail(memberDTO.getEmail());
        member.setBirthday(memberDTO.getBirthday());
        member.setGender(memberDTO.getGender());
        member.setRole(Role.USER);
//        member.setCreateDate(LocalDateTime.now());
        return member;
    }

    public static Member update1(Member member, PasswordEncoder passwordEncoder){
        System.out.println("여기는 엔티티 멤버 업데이트");
        Member member1 = new Member();
        member1.setMemberId(member.getMemberId());
        member1.setAddress(member.getAddress());
        member1.setBirthday(member.getBirthday());
        member1.setEmail(member.getEmail());
        member1.setGender(member.getGender());
        member1.setLoginId(member.getLoginId());
        member1.setName(member.getName());
        member1.setNickname(member.getNickname());
        member1.setPhoneNumber(member.getPhoneNumber());
        String pw = passwordEncoder.encode(member.loginPw);
        member1.setLoginPw(pw);
        member1.setRole(member.getRole());
//        member1.setCreateDate(member.getCreateDate());
//        member1.setUpdateDate(LocalDateTime.now());

        return member1;
    }





}
