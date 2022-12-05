package com.example.test.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.sql.Date;

@Getter
@Setter
public class UserDTO {

    private String login_id;

    private String login_pw;

    private String nickname;

    private String name;

    private Date birthday;

    private String gender;

    private String phonenumber;

    private String email;

    private String address;

    private String o_profile_img;

    private String n_profile_img;
}
