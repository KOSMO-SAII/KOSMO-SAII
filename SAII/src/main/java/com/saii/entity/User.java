package com.saii.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;

import java.sql.Date;
import java.time.LocalDateTime;

@Entity
@Getter
public class User extends BaseTime{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;

    @Column(nullable = false, unique = true)
    private String login_id;

    @Column(nullable = false)
    private String login_pw;

    @Column(nullable = false, unique = true)
    private String nickname;

    @Column(nullable = false)
    private String name;

    private Date birthday;

    private String gender;

    private String phonenumber;

    @Column(nullable = false, unique = true)
    private String email;

    private String address;

    private String o_profile_img;

    private String n_profile_img;

}
