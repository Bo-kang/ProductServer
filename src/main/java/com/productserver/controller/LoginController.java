package com.productserver.controller;

import com.productserver.domain.Member;
import com.productserver.domain.Product;
import com.productserver.domain.ProductInfo;
import com.productserver.service.MemberServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpSession;

/*
* 실패하는 경우에 대한 예외처리 추가 필요
* */


@RestController
@RequiredArgsConstructor
@SessionAttributes( names = {"member", "language", "status"})
public class LoginController {
    private final MemberServiceImpl memberService;


    @PostMapping("/login")
    public String login(@RequestBody Member member, Model model){
        System.out.println(member);
        Member ret = memberService.loginMember(member);
        if(ret == null){
            // 로그인 실패 오류 처리
            return null;
        }
        else{
            model.addAttribute("member" , ret);
            model.addAttribute("language" , Product.Language.KOR);
            model.addAttribute("status" , ProductInfo.Status.SELLING);
            return "OK";
        }

    }

    @PostMapping("/member")
    public String registerMember( Member member, Model model){

        Member ret = memberService.registerMember(member);
        if(ret == null){
            // 회원가입 실패 오류 처리
            return null;
        }
        else{
            return "OK";
        }

    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.setAttribute("UserType" , null);
        session.setAttribute("UserId" , null);

        return "OK";
    }

}
