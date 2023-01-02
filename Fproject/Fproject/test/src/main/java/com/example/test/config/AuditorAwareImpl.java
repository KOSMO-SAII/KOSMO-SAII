package com.example.test.config;

import java.security.Principal;
import java.util.Optional;

import com.example.test.entity.Member;
import com.example.test.repository.MemberRepository;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import static com.example.test.TestApplication.I;

public class AuditorAwareImpl implements AuditorAware<String> {

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public Optional<String> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String userId = "-1";
        if(authentication != null){
            String name = authentication.getName();
            System.out.println(name);
            Member member = memberRepository.findByLoginId(name);
            System.out.println("member" + member);
            userId = member.getMemberId().toString();
//          userId = authentication.getName();
        }
        return Optional.of(userId);
    }

}