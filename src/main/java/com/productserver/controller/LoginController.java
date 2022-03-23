package com.productserver.controller;

import com.productserver.domain.Member;
import com.productserver.service.MemberServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/*
* 실패하는 경우에 대한 예외처리 추가 필요
* */


@Controller
@RequiredArgsConstructor
@SessionAttributes(types = Member.class, names = "member")
public class LoginController {
    private final MemberServiceImpl memberService;

    @GetMapping("/login")
    public void loginView(){

    }
    @GetMapping("/register")
    public void registerView(){

    }

    @PostMapping("/login")
    public String login( Member member, Model model){
        Member ret = memberService.loginMember(member);
        if(ret == null){
            // 로그인 실패 오류 처리
            return null;
        }
        else{
            model.addAttribute("member", ret);
            System.out.println(ret);
            return "redirect:productList";
        }

    }

    @PostMapping("/member")
    public String registerMember( Member member, Model model){

        Member ret = memberService.registerMember(member);
        model.addAttribute(ret);
        System.out.println(ret);

        return "login";
    }
}
