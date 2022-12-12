package com.example.test.controller;

//import com.example.test.config.SessionMember;
//import com.example.test.service.MemberService;
import com.example.test.config.SessionMember;
import com.example.test.domain.MemberDTO;
import com.example.test.entity.Member;
import com.example.test.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final PasswordEncoder passwordEncoder;
    private final MemberService memberService;
    private final HttpSession httpSession;
//    @Autowired
//    private final Member member;

    //private final MemberRepository memberRepository;

    @GetMapping("/new")
    public String memberForm(Model model) {
        model.addAttribute("memberFormDto", new MemberDTO());
        return "/signup/signup";
    }

    @PostMapping("/signup")
    public String newMember(@Validated MemberDTO memberFormDto, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            return "/signup/signup";
        }
        try {
            Member member = Member.createMember(memberFormDto, passwordEncoder);
            httpSession.setAttribute("user", new SessionMember(member));
            memberService.saveMember(member);
        }catch(IllegalStateException e){
            model.addAttribute("errorMessage",e.getMessage());
            return "member/memberForm";
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


        return "/logins";
    }
    @GetMapping("/loginF")
    public String loginf(Model model){
        model.addAttribute("loginErrorMsg","뭐가 됐든 제대로 입력해보셈");

        return "login/loginPage";
    }

    @PostMapping("/update")
    public String updateMember(Model model,Principal principal){
        SessionMember sessionMember = memberService.memdto(principal.getName());
        model.addAttribute("info",sessionMember);
        System.out.println(sessionMember.toString());
        System.out.println(principal.getName());
        model.addAttribute("sessionMember",sessionMember);

        return "memberUpdate";
    }

    @PostMapping("/updateS")
    public String updateS(Model model, SessionMember sessionMember, Principal principal){
        System.out.println("어어");

        ModelMapper mapper = new ModelMapper();
        Member member = mapper.map(sessionMember, Member.class);
        memberService.saveMember1(member);
        model.addAttribute("info", member);
        SessionMember sessionMember1 = memberService.memdto(principal.getName());
        model.addAttribute("info",sessionMember1);

        return "redirect:/members/logins";
    }




}
