package com.example.test.config;

import com.example.test.domain.MemberDTO;
import com.example.test.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class SignUpFormValidator implements Validator {
    private final MemberRepository memberRepository;
    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.isAssignableFrom(MemberDTO.class);
    }

    @Override
    public void validate(Object object, Errors errors) {
        MemberDTO memberDTO = (MemberDTO)object;
        if(memberRepository.existsByEmail(memberDTO.getEmail())){
            errors.rejectValue("email", "invalid.email",
                    new Object[]{memberDTO.getEmail()}, "이미 사용중인 이메일 입니다.");
        }

        if(memberRepository.existsByNickname(memberDTO.getNickname())){
            errors.rejectValue("nickname", "invalid.nickname",
                    new Object[]{memberDTO.getNickname()}, "이미 사용중인 닉네임 입니다.");
        }

        if(memberRepository.existsByLoginId(memberDTO.getLoginId())){
            errors.rejectValue("LoginId", "invalid.LoginId",
                    new Object[]{memberDTO.getLoginId()}, "이미 사용중인 아이디 입니다.");
        }
        if(memberDTO.getLoginPw()!=memberDTO.getCheckPw()){
            errors.rejectValue("checkPw", "invalid.checkPw",
                    new Object[]{memberDTO.getCheckPw()}, "비밀번호와 동일하지 않습니다.");
        }
    }
}