package com.example.test.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Getter
@Setter
public class MemberDTO {

    @NotEmpty(message = "주소를 입력해주세요.")
    private String address;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

    @NotEmpty(message = "이메일을 입력해주세요.")
    @Email(message = "이메일 형식으로 입력해주세요.")
    private String email;

    private String gender;

    @NotEmpty(message = "아이디를 입력해주세요.")
    @Pattern(regexp="[a-zA-Z]+")
    private String loginId;

    @NotEmpty(message = "비밀번호를 입력해주세요.")
    @Length(min = 8, max=16, message="비밀번호는 8자이상, 16자 이하로 입력해주세요")
    private String loginPw;

    private String nProfileImg;

    @NotEmpty(message = "이름을 입력해주세요")
    private String name;

    @NotEmpty(message = "닉네임을 입력해주세요")
    private String nickname;

    private String oProfileImg;

    private String phoneNumber;
}
