package com.example.test.controller;

//import com.example.test.config.SessionMember;
//import com.example.test.service.MemberService;
import com.example.test.config.SessionMember;
import com.example.test.domain.MemberDTO;
import com.example.test.entity.Member;
import com.example.test.repository.MemberRepository;
import com.example.test.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.Principal;
import java.util.HashMap;
import java.util.Locale;

@Controller
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final PasswordEncoder passwordEncoder;
    private final MemberService memberService;
    private final HttpSession httpSession;
    private final MemberRepository memberRepository;
//    @Autowired
//    private final Member member;

    //private final MemberRepository memberRepository;

    @GetMapping("/new")
    public String memberForm(Model model) {
        model.addAttribute("memberFormDto", new MemberDTO());
        return "/signup/signup";
    }

    @PostMapping("/signup")
    public String newMember(@Validated MemberDTO memberFormDto, @NotNull BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            model.addAttribute("memberFormDto", memberFormDto);
            return "/signup/signup";
        }
        try {
            Member member = Member.createMember(memberFormDto, passwordEncoder);
            httpSession.setAttribute("user", new SessionMember(member));
            memberService.saveMember(member);
        }catch(IllegalStateException e){
            model.addAttribute("errorMessage",e.getMessage());
            return "/signup/signup";
        }
        return "redirect:/";
    }

    @GetMapping("/login")
    public String loginMember() {

        return "/login/loginPage";
    }

    @GetMapping("/logins")
    public String loginSuccess(Model model, Principal principal){
        SessionMember sessionMember =(SessionMember)httpSession.getAttribute("user");
        if(principal!= null && sessionMember == null) {
            model.addAttribute("name",principal.getName());
        }else if(principal != null && sessionMember != null ) {
            model.addAttribute("name",sessionMember.getLoginId());
            model.addAttribute("nickname",sessionMember.getNickname());
        }
        SessionMember sessionUser = memberService.memdto(principal.getName());
        model.addAttribute("info",sessionUser);
        System.out.println(sessionUser.toString());
        System.out.println(principal.getName()+"여기는 컨트롤 피린");


        return "/mypage/mypage";
    }
    @GetMapping("/loginF")
    public String loginf(Model model){
        model.addAttribute("loginErrorMsg","뭐가 됐든 제대로 입력해보셈");

        return "login/loginPage";
    }

    @RequestMapping("/update")
    public String updateMember(Model model,Principal principal){
        System.out.println("정보수정하러가요");
        SessionMember sessionMember = memberService.memdto(principal.getName());
        model.addAttribute("info",sessionMember);
        System.out.println(sessionMember.toString());
        System.out.println(principal.getName());
        model.addAttribute("sessionMember",sessionMember);

        return "/mypage/memberUpdate";
    }

    @PostMapping("/updateS")
    public String updateS(Model model, SessionMember sessionMember, Principal principal)throws Exception{
        System.out.println("어어");

        ModelMapper mapper = new ModelMapper();
        Member member = mapper.map(sessionMember, Member.class);
        memberService.saveMember1(member);
        model.addAttribute("info", member);
        SessionMember sessionMember1 = memberService.memdto(principal.getName());
        model.addAttribute("info",sessionMember1);

        return "redirect:/members/logins";
    }

    @GetMapping("delete")
    public String delete(Model model, Principal principal){
        System.out.println("회원탈퇴하는곳");
        memberService.deleteById(principal.getName());
        return "redirect:/";
    }


    @PostMapping("profile")
    public String profileupdate(Model model, @RequestParam("oProfileImg") MultipartFile multipartFile, Principal principal) throws IOException {
        System.out.println("프로필 업데이트");
        String st = multipartFile.getOriginalFilename();
        SessionMember member = memberService.memdto(principal.getName());
        ModelMapper modelMapper = new ModelMapper();
        Member member2 = modelMapper.map(member,Member.class);
        Member member1 = Member.profileup(member2,multipartFile);
        memberRepository.save(member1);

        return "redirect:/members/logins";
    }

    @RequestMapping("checkpath")
    public String password(Model model,Principal principal){
        SessionMember member = memberService.memdto(principal.getName());
        model.addAttribute("member",member);
        return "/mypage/pwcheck";
    }
    @RequestMapping("check")
    public String passwordCheck(Authentication auth, @RequestParam("loginPw1") String loginPw1, RedirectAttributes rttr, Principal principal){
//        Member user = (Member) auth.getPrincipal();
//        String userpw = user.getLoginPw();
        SessionMember member = memberService.memdto(principal.getName());
        String pw = member.getLoginPw();
        if(passwordEncoder.matches(loginPw1, pw)) {
            System.out.println("pw 재확인 완료..");
            return "redirect:/members/update";
        }
        else {
            rttr.addFlashAttribute("msg", "비밀번호를 다시 확인해 주세요.");
            System.out.println("비밀번호 틀린듯?");
            return "redirect:/members/checkpath";
        }
    }




}
