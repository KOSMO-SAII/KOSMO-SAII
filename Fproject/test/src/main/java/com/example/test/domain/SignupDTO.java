package com.example.test.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.util.Date;

@Getter
@Setter
@ToString
public class SignupDTO {
    @NotEmpty(message = "아이디는 필수 입력 값입니다.")
    @Pattern(regexp="^[a-zA-Z0-9]{6,15}$", message="영어와 숫자 6~15를 입력해주세요")
    @Size(min=6, max=15, message = "아이디는 6~15로 입력해주세요")
    private String id;

    @NotEmpty(message = "비밀번호는 필수 입력 값입니다.")
    @Size(min=8, max=16, message = "비밀번호는 8~16자 이하로 입력해주세요")
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}", message="숫자, 특수문자가 각각 최소 1개이상을 포함한 8~16자를 입력해주세요.")
    private String password;

    @NotBlank(message = "이름은 필수 입력 값입니다.")
    @Size(min=2, message = "이름은 2자 이상으로 입력해주세요")
    private String name;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

    @NotEmpty(message = "이메일은 필수 입력 값입니다.")
    @Email(message = "@을 포함하여 이메일 형식으로 입력해주세요.")
    private String email;

    @NotEmpty(message = "닉네임은 필수 입력 값입니다.")
    @Size(min=2, max=10, message = "닉네임은 2~10자 이하로 입력해주세요")
    private String nickname;

    @NotEmpty(message = "주소는 필수 입력 값입니다.")
    private String address;

    private String nProfileImg;

    private String oProfileImg;

    @Pattern(regexp = "^[0-9]{2,3}-[0-9]{3,4}-[0-9]{4}$", message = "하이픈(-)을 포함하여 입력해주세요.")
    private String phoneNumber;

    private String gender;
}
