package com.example.test.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
    USER("Role_User","유저")
    , ADMIN("Role_Admin","관리자");

    private final String key;
    private final String title;
}
