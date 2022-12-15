package com.example.test.service;

import com.example.test.config.SessionMember;
import com.example.test.entity.Member;
import com.example.test.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {
    @Autowired
    PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;

    public Member saveMember(Member member) {
        validateDuplicateMember(member);
        return memberRepository.save(member);
    }

    private void validateDuplicateMember(Member member) {
        Member findMember = memberRepository.findByLoginId(member.getLoginId());
        if(findMember != null) {
            throw new IllegalStateException("이미 가입된 회원입니다.");
        }
    }

    public SessionMember memdto (String loginId){
        Member member = memberRepository.findByLoginId(loginId);
        System.out.println(member.toString());
        ModelMapper modelMapper = new ModelMapper();
        SessionMember employeeEntity = modelMapper.map(member, SessionMember.class);
        System.out.println(employeeEntity+"여기 memdto");
        return employeeEntity;
    }

    @Override
    public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
        Member users = memberRepository.findByLoginId(loginId);

        if(users == null) {
            throw new UsernameNotFoundException(loginId);
        }

        return User.builder()
                .username(users.getLoginId())
                .password(users.getLoginPw())
                .roles(users.getRole().toString())
                .build();
    }

    public Member saveMember1(Member member, MultipartFile multipartFile){
        System.out.println("여기는 엡데이트 서비스");
        Member user1 = member.update1(member,passwordEncoder, multipartFile);
        return memberRepository.save(user1);
    }

    public void deleteById(String id) {
        Member mem = memberRepository.findByLoginId(id);
        memberRepository.delete(mem);
        SecurityContextHolder.clearContext();
    }

//    @Transactional
//    public void modify(String email) {
//        Member user = memberRepository.findByEmail(email).orElseThrow(() ->
//                new IllegalArgumentException("해당 회원이 존재하지 않습니다."));
//
//        String encPassword = encoder.encode(dto.getPassword());
//        user.modify(dto.getNickname(), encPassword);
//    }
}