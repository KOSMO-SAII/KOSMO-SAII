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

<<<<<<< HEAD
import static com.example.test.TestApplication.I;
=======
import javax.servlet.http.HttpSession;
>>>>>>> a62852322e8b2460a40d0670727fb427a0902448

public class AuditorAwareImpl implements AuditorAware<String> {

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public Optional<String> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = "익명";
        if(authentication != null){
<<<<<<< HEAD
            String name = authentication.getName();
            System.out.println(name);
            Member member = memberRepository.findByLoginId(name);
            System.out.println("member" + member);
            userId = member.getMemberId().toString();
//          userId = authentication.getName();
=======
            userId = authentication.getName();
>>>>>>> a62852322e8b2460a40d0670727fb427a0902448
        }
        return Optional.of(userId);
    }


}