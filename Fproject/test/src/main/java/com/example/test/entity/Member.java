package com.example.test.entity;

import com.example.test.constant.Role;
import com.example.test.domain.MemberDTO;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Member{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    private LocalDateTime createDate;

    private LocalDateTime updateDate;
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



    @Builder
    public Member(Long memberId, String address,LocalDateTime createDate, LocalDateTime updateDate,
                  Date birthday, String email, String gender, String loginId, String loginPw,
                  String nProfileImg, String name, String nickname, String oProfileImg,
                  String phoneNumber){
        this.memberId=memberId;
        this.createDate=createDate;
        this.updateDate=updateDate;
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
        member.setAddress(memberDTO.getAddress().concat(" "+memberDTO.getAddress1()));
        member.setBirthday(memberDTO.getBirthday());
        member.setEmail(memberDTO.getEmail());
        member.setBirthday(memberDTO.getBirthday());
        member.setGender(memberDTO.getGender());
        member.setPhoneNumber(memberDTO.getPhoneNumber());
        member.setRole(Role.USER);
        member.setCreateDate(LocalDateTime.now());
        return member;
    }

    public static Member update1(MemberDTO memberDTO, PasswordEncoder passwordEncoder)throws Exception{
        System.out.println("여기는 엔티티 멤버 업데이트");
        Member member1 = new Member();
        member1.setMemberId(memberDTO.getMemberId());
        member1.setAddress(memberDTO.getAddress().concat(" "+memberDTO.getAddress1()));
        member1.setBirthday(memberDTO.getBirthday());
        member1.setEmail(memberDTO.getEmail());
        member1.setGender(memberDTO.getGender());
        member1.setLoginId(memberDTO.getLoginId());
        member1.setName(memberDTO.getName());
        member1.setNickname(memberDTO.getNickname());
        member1.setPhoneNumber(memberDTO.getPhoneNumber());
        String pw = passwordEncoder.encode(memberDTO.getLoginPw());
        member1.setLoginPw(pw);
        member1.setRole(memberDTO.getRole());
        member1.setCreateDate(memberDTO.getCreateDate());
        member1.setUpdateDate(LocalDateTime.now());

        System.out.println(member1+"여ㅣ가 업데이트 엔티티");

        return member1;
    }
    public static Member profileup(Member member,MultipartFile multipartFile) throws IOException {
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
        member1.setLoginPw(member.getLoginPw());
        member1.setRole(member.getRole());
        member1.setCreateDate(member.getCreateDate());
        member1.setUpdateDate(LocalDateTime.now());
        String projectPath = System.getProperty("user.dir")+"\\test\\src\\main\\resources\\static\\img\\profile";
        UUID uuid = UUID.randomUUID();
        String fileName = uuid+"-"+multipartFile.getOriginalFilename();
        File saveFile = new File(projectPath,fileName);
        multipartFile.transferTo(saveFile);
        member.setOProfileImg("/img/profile/"+fileName);
        member1.setOProfileImg(member.getOProfileImg());

        return member1;

    }





}
