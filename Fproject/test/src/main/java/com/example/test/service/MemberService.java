package com.example.test.service;

import com.example.test.config.SessionMember;
import com.example.test.domain.MemberDTO;
import com.example.test.entity.Member;
import com.example.test.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.security.Principal;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {

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

//    public void pictureUse(Member member) {
//
//        if(picture == null) {
//
//        }
//    }

    public SessionMember memdto (String loginId){
        Member member = memberRepository.findByLoginId(loginId);
        ModelMapper modelMapper = new ModelMapper();
        SessionMember employeeEntity = modelMapper.map(member, SessionMember.class);
        System.out.println(employeeEntity+"여기 memdto");
        return employeeEntity;
    }

    public void passSave(String loginId1,String loginPw, PasswordEncoder passwordEncoder){
        Member member = memberRepository.findByLoginId(loginId1);
        String pw = passwordEncoder.encode(loginPw);
        member.setLoginPw(pw);
        memberRepository.save(member);
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

    public Member saveMember1(MemberDTO memberDTO)throws Exception {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println("여기는 엡데이트 서비스");
        Member user1 = new Member();
        user1 = user1.update1(memberDTO,passwordEncoder);
        return memberRepository.save(user1);
    }

    public Member saveMember2(MemberDTO memberDTO, Principal principal)throws Exception {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println("여기는 엡데이트 서비스");
        SessionMember sessionMember = memdto(principal.getName());
        String pw = sessionMember.getLoginPw();
        System.out.println(pw+"여기는 비밀번호  없을때");
        Member user1 = new Member();
        user1 = user1.update2(memberDTO,pw);
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

    public Member getMember(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Member member = memberRepository.findByLoginId(authentication.getName());
        return member;
    }

    public Member getMember(long id){
        Member member = new Member();
        if(id != -1){
            member = memberRepository.findById(id).orElseThrow();
        }
        return member;
    }

    public String findId(String name, String email){
        Member member = new Member();
        memberRepository.existsByEmailAndName(email,name);

        if(memberRepository.existsByEmailAndName(email,name)){
            member = memberRepository.findByEmailAndName(email,name).orElseThrow();
            String id = member.getLoginId();
            id = id.substring(0,id.length()-3);
            id = id+"***";
            return id;
        }else{
            String loginId = "아이디를 찾을 수 없습니다.";
            return loginId;
        }
    }

    public String findPassword(String name,String email,String loginId){
        if(memberRepository.existsByEmailAndNameAndLoginId(email,name,loginId)){
            return "있음";
        }else{
            return "없음";
        }
    }

}