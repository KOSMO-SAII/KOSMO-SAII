package com.example.test.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
public class SignupDTO {
    @NotBlank(message = "이름은 필수 입력 값입니다.")
    @Size(min=6, max=15, message = "이름은 6자 이상, 15자 이하로 입력해주세요")
    private String name;

    @NotEmpty(message = "비밀번호는 필수 입력 값입니다.")
    @Size(min=8, max=16, message = "비밀번호는 8자 이상, 16자 이하로 입력해주세요")
    private String password;

    @NotEmpty(message = "이메일은 필수 입력 값입니다.")
    @Email(message = "이메일 형식으로 입력해주세요.")
    private String email;

    @NotEmpty(message = "닉네임은 필수 입력 값입니다.")
    @Size(min=2, max=10, message = "닉네임은 2자 이상, 10자 이하로 입력해주세요")
    private String nickname;

    @NotEmpty(message = "주소는 필수 입력 값입니다.")
    private String address;
}
