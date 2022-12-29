package com.example.test.config;

import java.security.Principal;
import java.util.Optional;

import com.example.test.repository.MemberRepository;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

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