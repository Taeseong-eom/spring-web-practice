package com.eom.shop.member;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/register")
    String register(){
        return "register.html";
    }

    @PostMapping("/member")
    String addMember(String username,
                     String password,
                     String displayName
    ){
        Member member = new Member();

        member.setUsername(username);
        String hash = passwordEncoder.encode(password);
        member.setPassword(hash);
        member.setDisplayName(displayName);

        memberRepository.save(member);
        return "redirect:/list";
    }

    @GetMapping("/login")
    public String login(){
        return "login.html";
    }

    @GetMapping("/my-page")
    String myPage(Authentication auth){
        var result = (CustomUser) auth.getPrincipal();
        System.out.println(auth.getPrincipal());
        return "mypage.html";
    }
}
