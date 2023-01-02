package com.example.test.controller;

//import com.example.test.config.SessionMember;
//import com.example.test.service.MemberService;
import com.example.test.config.SessionMember;
import com.example.test.config.SignUpFormValidator;
import com.example.test.domain.MemberDTO;
import com.example.test.entity.Member;
import com.example.test.repository.MemberRepository;
import com.example.test.service.CourseService;
import com.example.test.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.format.annotation.DateTimeFormat;
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
import java.net.URL;
import java.security.Principal;
import java.util.*;

@Controller
@RequestMapping("/members")
@RequiredArgsConstructor
public class

MemberController {

    @Autowired
    private CourseService courseService;
    private final PasswordEncoder passwordEncoder;
    private final MemberService memberService;
    private final HttpSession httpSession;
    private final MemberRepository memberRepository;
//    @Autowired
//    private final Member member;

    //private final MemberRepository memberRepository;

    @GetMapping("/new")
    public String memberForm(Model model) {
        model.addAttribute("memberDTO", new MemberDTO());
        return "/signup/signup";
    }

    @PostMapping("/signup")
    public String newMember(@Validated @DateTimeFormat(pattern="yyyy-MM-dd") MemberDTO memberDTO, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            return "/signup/signup";
        }
        SignUpFormValidator signUpFormValidator = new SignUpFormValidator(memberRepository);
        signUpFormValidator.validate(memberDTO,bindingResult);
        if(bindingResult.hasErrors()) {
            return "/signup/signup";
        }

        try {
            Member member = Member.createMember(memberDTO, passwordEncoder);
            httpSession.setAttribute("user", new SessionMember(member));
            memberService.saveMember(member);
        }catch(IllegalStateException e){
            model.addAttribute("errorMessage",e.getMessage());
            return "/signup/signup";
        }
        return "redirect:/";
    }

    @GetMapping("/use")
    public String useTo() {
        return "/howtoUse";
    }

    @GetMapping("/login")
    public String loginMember() {

        return "/login/loginPage";
    }

    @GetMapping("/logins")
    public String loginSuccess(Model model, Principal principal, Optional<Integer> page){
        SessionMember sessionMember =(SessionMember)httpSession.getAttribute("user");
        if(principal!= null && sessionMember == null) {
            model.addAttribute("name",principal.getName());
        }else if(principal != null && sessionMember != null ) {
            model.addAttribute("name",sessionMember.getLoginId());
            model.addAttribute("nickname",sessionMember.getNickname());
            SessionMember sessionUser = memberService.memdto(sessionMember.getLoginId());
            model.addAttribute("info",sessionUser);
            return "/mypage/mypage";

        }
        URL r = this.getClass().getResource("");
        System.out.println(r);
        System.out.println(principal.getName());
        SessionMember sessionUser = memberService.memdto(principal.getName());
        model.addAttribute("info",sessionUser);
        System.out.println(sessionUser.toString());
        System.out.println(principal.getName()+"여기는 컨트롤 피린");


        PageRequest pageRequest = PageRequest.of(page.isPresent() ? page.get() : 0,3);

        model.addAttribute("lists", courseService.getList(pageRequest));
        model.addAttribute("pages", courseService.getPage(pageRequest));
        model.addAttribute("maxPage", 5);
        model.addAttribute("list", courseService.myList(principal));

        return "/mypage/mypage";
    }
    @GetMapping("/loginF")
    public String loginf(Model model){
        model.addAttribute("loginErrorMsg","아이디 혹은 비밀번호를 확인해주세요.");

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
    public String updateS(Model model, MemberDTO memberDTO, Principal principal)throws Exception{
        System.out.println("어어");
        System.out.println(memberDTO.toString());
        System.out.println(memberDTO.getLoginPw()+"정보정보수정");
        if(memberDTO.getLoginPw()!="") {
            memberService.saveMember1(memberDTO);
        }else{
            memberService.saveMember2(memberDTO,principal);
        }
        model.addAttribute("info", memberDTO);
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
    public String profileupdate(Model model, @RequestParam("picture") MultipartFile multipartFile, Principal principal) throws IOException {
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
        System.out.println(principal.getName());
        SessionMember member = memberService.memdto(principal.getName());
        System.out.println(member.toString());
        model.addAttribute("member",member);
        return "/mypage/pwcheck";
    }
    @RequestMapping("check")
    public String passwordCheck(Authentication auth, @RequestParam("loginPw1") String loginPw1, RedirectAttributes abc, SessionMember sessionMember){
//        Member user = (Member) auth.getPrincipal();
//        String userpw = user.getLoginPw();
        System.out.println(sessionMember.getLoginId());
        SessionMember member = memberService.memdto(sessionMember.getLoginId());
        System.out.println(member.toString());
        String pw = member.getLoginPw();
        System.out.println(pw+"기존패스워드");
        if(passwordEncoder.matches(loginPw1, pw)) {
            System.out.println(loginPw1+"확인패스워드");
            System.out.println("pw 재확인 완료..");
            return "redirect:/members/update";
        }
        else {
            System.out.println("비밀번호 틀린듯?");
            abc.addFlashAttribute("msg", "비밀번호를 다시 확인해 주세요.");
            return "redirect:/members/checkpath";
        }
    }

    @PostMapping("/findId/{email}/{name}")
    @ResponseBody
    public String idFind(@PathVariable String email,@PathVariable String name,Model model){
        System.out.println(name+email+"여기는 리스폰스바디 아이디");
        String id = memberService.findId(name,email);
        model.addAttribute("id",id);
        System.out.println("찍혀라 제발");
        System.out.println(id+"컨트롤러 아이디");
        return id;
    }

    @RequestMapping("idFind")
    public String idFind(){
        return "/login/idFind";
    }

    @RequestMapping("snow")
    public String snow(){
        return "mypage/snow";
    }

    @RequestMapping("/passwordFind")
    public String passwordFind(){
        return "/login/passwordFind";
    }

    @PostMapping("/findPassword/{email}/{name}/{loginId}")
    @ResponseBody
    public String passwordFind(@PathVariable String email,@PathVariable String name,@PathVariable String loginId,Model model){
        System.out.println(name+email+"여기는 리스폰스바디 아이디");
        String passwordCheck = memberService.findPassword(name,email,loginId);
        model.addAttribute("passwordCheck",passwordCheck);
        System.out.println(passwordCheck+"컨트롤러 패스워드");
        return passwordCheck;
    }

    @PostMapping("/passFind")
    public String passFind (String loginId1,String loginPw){
        System.out.println(loginId1+loginPw+"여기 passFInd");
        memberService.passSave(loginId1,loginPw);
        return "redirect:/members/login";
    }

}