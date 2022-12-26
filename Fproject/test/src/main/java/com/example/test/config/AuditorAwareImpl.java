package com.example.test.config;

import java.security.Principal;
import java.util.Optional;

import com.example.test.entity.Member;
import com.example.test.repository.MemberRepository;
import com.example.test.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.http.HttpSession;

public class AuditorAwareImpl implements AuditorAware<String> {

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public Optional<String> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = "익명";
        if(authentication != null){
            userId = authentication.getName();
        }
        return Optional.of(userId);
    }


}