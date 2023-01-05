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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Member{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="memberId")
    private Long memberId;

    private LocalDateTime createDate;

    private LocalDateTime updateDate;
    private String address;
    private String address1;
    @DateTimeFormat(pattern="yyyy-MM-dd")
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
    @Column
    private String picture;

    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "member")
    private List<ReviewCourse> ReviewCourseList = new ArrayList<>();

    @OneToMany(mappedBy = "member",cascade = CascadeType.REMOVE)
    private List<QnABoardReply> replyList;

    @OneToMany(mappedBy = "member",cascade = CascadeType.REMOVE)
    private List<QnABoard> qnABoards;

    @Builder
    public Member(Long memberId, String address,LocalDateTime createDate, LocalDateTime updateDate,
                  Date birthday, String email, String gender, String loginId, String loginPw,
                  String nProfileImg, String name, String nickname, String oProfileImg,
                  String phoneNumber, String picture,Role role){
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
        this.picture=picture;
        this.role=role;

    }

    public static Member createMember(MemberDTO memberDTO, PasswordEncoder passwordEncoder) {
        Member member = new Member();

        member.setLoginId(memberDTO.getLoginId());
        member.setLoginPw(memberDTO.getLoginPw());
        String pw = passwordEncoder.encode(memberDTO.getLoginPw());
        member.setLoginPw(pw);
        member.setName(memberDTO.getName());
        member.setNickname(memberDTO.getNickname());
//        member.setAddress(memberDTO.getAddress().concat(" "+memberDTO.getAddress1()));
        member.setAddress(memberDTO.getAddress());
        member.setAddress1(memberDTO.getAddress1());
        member.setBirthday(memberDTO.getBirthday());
        member.setEmail(memberDTO.getEmail());
        member.setBirthday(memberDTO.getBirthday());
        member.setGender(memberDTO.getGender());
        member.setPhoneNumber(memberDTO.getPhoneNumber());
        member.setRole(Role.USER);
        member.setCreateDate(LocalDateTime.now());
        member.setPicture("/img/profile/saii.png");
        return member;
    }

    public Member createMember2(String name, String password, String email, PasswordEncoder passwordEncoder,String loginId) {
        Member member = new Member();
        member.setLoginId(loginId);
        System.out.println(loginId+" "+member.getLoginId());
        member.setName(name);
        member.setEmail(email);
        member.setAddress("입력없음");
        String pw = passwordEncoder.encode(password);
        member.setNickname(LocalDateTime.now()+"간편로그인="+name);
        member.setLoginPw(pw);
        member.setRole(Role.USER);
        System.out.println(member.getEmail()+"이이이");
        return member;
    }

    public static Member update1(MemberDTO memberDTO, PasswordEncoder passwordEncoder)throws Exception{
        System.out.println("여기는 엔티티 멤버 업데이트");
        Member member1 = new Member();
        member1.setMemberId(memberDTO.getMemberId());
        member1.setAddress(memberDTO.getAddress());
        member1.setAddress1(memberDTO.getAddress1());
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
        member1.setPicture(memberDTO.getPicture());
        member1.setQnABoards(null);

        System.out.println(member1+"여ㅣ가 업데이트 엔티티");

        return member1;
    }
    public static Member update2(MemberDTO memberDTO,String pw) throws Exception{
        System.out.println("여기는 엔티티 멤버 업데이트");
        Member member1 = new Member();
        member1.setMemberId(memberDTO.getMemberId());
        member1.setAddress(memberDTO.getAddress());
        member1.setAddress1(memberDTO.getAddress1());
        member1.setBirthday(memberDTO.getBirthday());
        member1.setEmail(memberDTO.getEmail());
        member1.setGender(memberDTO.getGender());
        member1.setLoginId(memberDTO.getLoginId());
        member1.setName(memberDTO.getName());
        member1.setNickname(memberDTO.getNickname());
        member1.setPhoneNumber(memberDTO.getPhoneNumber());
        member1.setLoginPw(pw);
        member1.setRole(memberDTO.getRole());
        member1.setCreateDate(memberDTO.getCreateDate());
        member1.setUpdateDate(LocalDateTime.now());
        member1.setPicture(memberDTO.getPicture());
        member1.setQnABoards(null);

        System.out.println(member1+"여ㅣ가 업데이트 엔티티");

        return member1;
    }

    public static Member profileup(Member member,MultipartFile multipartFile) throws IOException {
        Member member1 = new Member();
        member1.setMemberId(member.getMemberId());
        member1.setAddress(member.getAddress());
        member1.setAddress1(member.getAddress1());
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
        String projectPath = System.getProperty("user.dir")+"\\src\\main\\resources\\static\\img\\profile";
        UUID uuid = UUID.randomUUID();
        String fileName = uuid+"-"+multipartFile.getOriginalFilename();
        System.out.println(multipartFile.getOriginalFilename());
        System.out.println(member.getPicture());
        File saveFile = new File(projectPath,fileName);
        multipartFile.transferTo(saveFile);
        member1.setPicture("/img/profile/"+fileName);
        member1.setQnABoards(null);
//        member1.setPicture(member.getOProfileImg());

        return member1;

    }
    public Member update(String name, String password, String address, PasswordEncoder passwordEncoder) {
        this.name = name;
        String pw = passwordEncoder.encode(password);
        this.loginPw = pw;
        this.address = "입력없음";
        return this;

    }

    public String getRoleKey() {
        return this.role.getKey();
    }

    public MemberDTO toDto(){
        MemberDTO dto = new MemberDTO();

        dto.setMemberId(memberId);
        dto.setAddress(address);
        dto.setBirthday(birthday);
        dto.setEmail(email);
        dto.setGender(gender);
        dto.setName(name);
        dto.setNickname(nickname);
        dto.setCreateDate(createDate);
        dto.setPhoneNumber(phoneNumber);
//        dto.setUpdateDate(updateDate);
        dto.setLoginId(loginId);
        dto.setLoginPw(loginPw);
        dto.setNProfileImg(nProfileImg);
        dto.setOProfileImg(oProfileImg);
        dto.setPicture(picture);
        dto.setRole(role);

        return dto;
    }



}
